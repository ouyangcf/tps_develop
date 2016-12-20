package com.chinacreator.c2.runtime.logic.rule.impl;

import java.lang.reflect.Field;   
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.List; 
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.chinacreator.c2.context.ContextConstants;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.runtime.DataAdaptorChain;
import com.chinacreator.c2.runtime.exception.BusinessException;
import com.chinacreator.c2.runtime.exception.RuleExecuteException;
import com.chinacreator.c2.runtime.logic.ExecuteResult;
import com.chinacreator.c2.runtime.logic.ExecuteResultFactory;
import com.chinacreator.c2.runtime.logic.IStatus;
import com.chinacreator.c2.runtime.logic.Status;
import com.chinacreator.c2.runtime.logic.rule.AbstractRuleExecutor;
import com.chinacreator.c2.runtime.logic.rule.RuleOutput;
import com.chinacreator.c2.runtime.util.ArrayUtil;
import com.chinacreator.c2.runtime.util.PrimitiveUtil;
import com.chinacreator.c2.runtime.util.ValueExprParser;
import com.chinacreator.c2.sysmgr.AuthorizationProvider;
import com.chinacreator.c2.sysmgr.exception.AuthenticationException;
 
/** 
 * 自定义规则执行类
 *  
 * @author Vurt 
 */
public class CustomRuleExecutor extends AbstractRuleExecutor { 
	protected static final Logger LOGGER = LoggerFactory
			 .getLogger(CustomRuleExecutor.class);

	/**
	 * 方法所在类
	 */
	protected final String clazz;

	/**
	 * 对象实例
	 */
	protected volatile Object instance;

	/**
	 * 对象初始化类的实例
	 */
	protected volatile RuleInstanceCreator instanceCreator;

	/**
	 * 方法名称
	 */
	protected final String methodName;

	/**
	 * 方法实例
	 */
	protected volatile Method method;

	/**
	 * 输入参数类型字符串数组
	 */
	protected final String[] paramTypeStrs;

	/**
	 * 输入参数类型
	 */
	protected volatile Class<?>[] paramTypes;

	/**
	 * 输入参数从上下文中取值的取值表达式
	 */
	protected final String[] valueExprs;

	/**
	 * 输入参数名称
	 */
	protected Map<String,Boolean> requires;
	
	protected String res;

	/**
	 * 自定义规则
	 * 
	 * @param id
	 *            规则id
	 * @param clazz
	 *            调用的类名
	 * @param method
	 *            方法名
	 * @param paramTypeStrs
	 *            参数类型
	 * @param paramNames 参数名称
	 * @param valueExprs
	 *            参数取值表达式
	 * @param res 权限资源id
	 */
	public CustomRuleExecutor(String id, String clazz, String method,
			String[] paramTypeStrs, String[] valueExprs,Map<String,Boolean> requires,String res) {
		super(id);
		this.clazz = clazz;
		this.methodName = method;
		this.valueExprs = valueExprs;
		this.paramTypeStrs = paramTypeStrs;
		this.requires=requires;
		this.res=res;
	}

	public IStatus validate(Map<String, Object> context) {
		if(StringUtils.isNotEmpty(res)){
				AuthorizationProvider provider=ApplicationContextManager.getContext().getBean("AuthorizationProvider", AuthorizationProvider.class);
				try {
					if(!provider.isPermitted(res)){
						return Status.failed("您没有权限调用["+getId()+"]服务");
					}
				} catch (AuthenticationException e) {
					return Status.failed("调用["+getId()+"]服务必须要登陆");
				}
		}
		if (getParamTypes() == null) {
			return Status.failed("自定义规则输入参数类型配置不正确，定义的类型是"
					+ printArray(paramTypeStrs));
		}
		Method temp = getMethod();
		// 尝试初始化方法，未成功则证明参数不正确
		// 如果方法不是public的则配置也不正确
		if (temp == null) {
			return Status.failed(clazz + "." + methodName + "不是有效的方法");
		} else if (!Modifier.isPublic(temp.getModifiers())) {
			return Status.failed(clazz + "." + methodName + "不是public方法，无法调用");
		}
		//入参表必填验证
		@SuppressWarnings("unchecked")
		Map<String, Object> inputs=(Map<String, Object>)context.get(ContextConstants.INPUT_PREFIX);
		for(Map.Entry<String, Boolean> require:requires.entrySet()){
			if(require.getValue() && !inputs.containsKey(require.getKey())){
				return Status.failed("服务"+getId()+"的必填参数"+require.getKey()+"为空");
			}
		}
		return Status.succeed();
	}

	public ExecuteResult execute(Map<String, Object> context)
			throws RuleExecuteException {
		ExecuteResult executeResult = null;
		try {
			Object[] params = getParams(context);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("规则调用参数：" + printArray(params));
			}
			Object result = getMethod().invoke(getClassInstance(context),
					params);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("执行结果：" + String.valueOf(result));
			}
			executeResult = initExecutionResult(context, result);
		} catch (IllegalArgumentException e) {
			throw new RuleExecuteException("自定义规则" + getId() + "的Java方法调用参数无效",
					e);
		} catch (IllegalAccessException e) {
			throw new RuleExecuteException("自定义规则" + getId() + "关联的方法无法访问", e);
		} catch (InvocationTargetException e) {
			if (e.getTargetException() instanceof BusinessException) {
				throw (BusinessException) e.getTargetException();
			}
			throw new RuleExecuteException("自定义规则" + getId() + "调用时出现异常，异常信息："
					+ e.getTargetException().getMessage(), e);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("=====自定义规则" + getId() + "执行结束=====");
		}
		return executeResult;
	}

	public void setInstanceCreator(RuleInstanceCreator creator) {
		this.instanceCreator = creator;
	}

	/**
	 * 获取缓存的方法，没有则新建
	 * 
	 * @return
	 */
	protected Method getMethod() {
		if (method == null) {
			try {
				// printMethods(Class.forName(clazz));
				method = Class.forName(clazz).getMethod(methodName,
						getParamTypes());
			} catch (ClassNotFoundException e) {
				throw new RuleExecuteException(e.getMessage() + "类型不存在", e);
			} catch (SecurityException e) {
				throw new RuleExecuteException("获取" + e.getMessage()
						+ "方法时发生安全异常", e);
			} catch (NoSuchMethodException e) {
				throw new RuleExecuteException(e.getMessage() + "方法不存在", e);
			}
		}
		return method;
	}

	/**
	 * 获取参数类型
	 * 
	 * @return
	 */
	protected Class<?>[] getParamTypes() {
		if (paramTypes == null) {
			paramTypes = createTypes(paramTypeStrs);
		}
		return paramTypes;
	}

	/**
	 * 根据类型名称创建类型实例
	 * 
	 * @return
	 */
	protected static Class<?>[] createTypes(String[] typeNames) {
		Class<?>[] paramClasses = new Class<?>[typeNames.length];
		try {
			for (int i = 0; i < typeNames.length; i++) {
				if (PrimitiveUtil.isPrimitive(typeNames[i])) {
					paramClasses[i] = PrimitiveUtil
							.getPrimitiveClass(typeNames[i]);
				} else if (ArrayUtil.isArray(typeNames[i])) {
					paramClasses[i] = ArrayUtil.getArrayType(typeNames[i]);
				} else {
					paramClasses[i] = Class.forName(typeNames[i]);
				}
			}
		} catch (ClassNotFoundException e) {
			throw new RuleExecuteException("参数类型不存在，内部错误信息：" + e.getMessage(),
					e);
		}
		return paramClasses;
	}

	/**
	 * 获取执行方法的对象实例，如果方法是public static的则直接返回null
	 * 
	 * @return
	 */
	protected Object getClassInstance(Map<String, Object> context) {
		try {
			if (instanceCreator == null) {
				if (instance == null) {
					// 静态方法直接调用即可
					int modifier = getMethod().getModifiers();
					if (Modifier.isStatic(modifier)
							&& Modifier.isPublic(modifier)) {
						return null;
					}
					try {
						// 不是静态方法，但是有静态的INSTANCE属性
						Field field = getMethod().getDeclaringClass().getField(
								"INSTANCE");
						if (field != null) {
							modifier = field.getModifiers();
							if (Modifier.isStatic(modifier)
									&& Modifier.isPublic(modifier)) {
								return field.get(null);
							}
						}
					} catch (NoSuchFieldException e) {
					}
					try{
						//有静态的无参getInstance()方法
						Method method=getMethod().getDeclaringClass().getMethod("getInstance");
						if(method!=null){
							modifier=method.getModifiers();
							if (Modifier.isStatic(modifier)
									&& Modifier.isPublic(modifier)) {
								return method.invoke(null);
							}
						}
					}catch (NoSuchMethodException e) {
					}
					Class<?> clz = Class.forName(clazz);
					// 没有实例生成器则尝试查找Spring上下文
					try {
						Collection<?> beans = BeanFactoryUtils
								.beansOfTypeIncludingAncestors(
										ApplicationContextManager.getContext(),
										clz).values();
						if (!beans.isEmpty()) {
							instance = beans.iterator().next();
							if (beans.size() > 1) {
								LOGGER.warn(
										"在上下文中找到了多个规则[{}]的实现实例，默认使用找到的第一个，具体类型为{}",
										getId(), instance.getClass().getName());
							}
						}
					} catch (NoSuchBeanDefinitionException e) {
					}
					if (instance == null) {
						// 还是没有则直接调用无参构造函数
						instance = clz.newInstance();
					}
				}
			} else {
				instance = instanceCreator.getInstance(context);
			}
		} catch (Exception e) {
			e.printStackTrace() ;
			throw new RuleExecuteException("创建服务执行类实例的过程中发生了错误，无法找到可调用的["
					+ e.getMessage() + "]接口实例");
		}
		return instance;
	}

	/**
	 * 通过上下文和参数配置的取值表达式计算出真实的调用参数
	 * 
	 * @param context
	 *            上下文
	 * @return
	 */
	protected Object[] getParams(Map<String, Object> context) {
		Object[] params = new Object[valueExprs.length];
		try {
			for (int i = 0; i < valueExprs.length; i++) {
				Object value = ValueExprParser.getValue(context, valueExprs[i]);
				Class<?> paramType = getParamTypes()[i] ;
				
				//optimize:服务方法入参为带泛型List时无法正常适配 2015-06-18
				//TODO 是否只适配List？
				if(List.class.isAssignableFrom(paramType)){
					context.put(ContextConstants.GENERIC_TYPE, getMethod().getGenericParameterTypes()[i]) ;
				}
				
				// 如果计算出的数据类型与方法接受的参数类型不一致，则尝试做一下适配
				if (value != null && (!paramType.isInstance(value) || List.class.isAssignableFrom(paramType))) {
					Object temp = DataAdaptorChain.adapte(paramType,
							value);
					if (temp == null) {
						String message = "参数与声明的类型不匹配，也无法适配，声明类型："
								+ paramType + "，实际类型"
								+ value.getClass().getName();
						throw new RuleExecuteException(message);
					} else {
						value = temp;
					}
				}
				params[i] = value;
			}
		} catch (Exception e) {
			if (e instanceof RuleExecuteException) {
				throw (RuleExecuteException) e;
			} else {
				throw new RuleExecuteException("规则入参映射过程中发生异常，异常信息："
						+ e.getMessage(), e);
			}
		}
		return params;
	}

	/**
	 * 根据方法返回值构建规则执行结果
	 * 
	 * @param context
	 *            上下文
	 * @param result
	 *            方法调用返回值
	 * @return
	 */
	protected ExecuteResult initExecutionResult(Map<String, Object> context,
			Object result) {
		String key = ContextConstants.getMethodResultKey(getId());
		context.put(key, result);
		Map<String, Object> data = new HashMap<String, Object>();
		for (RuleOutput output : getOutputs()) {
			data.put(output.getId(),
					ValueExprParser.getValue(context, output.getValueExpr()));
		}
		// 方法返回值的作用域只有当前规则内部，所以在这里要删除掉
		context.remove(key);
		return ExecuteResultFactory.createSuccess(data);
	}

	protected static String printArray(Object[] objects) {
		if (objects == null) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (Object object : objects) {
			if (object != null) {
				if (object instanceof Map) {
					builder.append("某Map").append(" ");
				} else {
					builder.append(object.toString()).append(", ");
				}
			}
		}
		return builder.toString();
	}
}
