package com.unlcn.ils.tps.services; 

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_checkrule;
import com.unlcn.ils.tps.E_item;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
/**
 * 
 *@Title:
 *@Description:考核规范服务
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0 
 */
public class CheckRuleService implements ArrayContentProvider{

	@Override  
	public Page<E_checkrule> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_checkrule> dao = DaoFactory.create(E_checkrule.class) ;
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON) 
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_checkrule> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		return result;
	}
	/**
	 * 
	 * @param data
	 * @return
	 * @Description:处理考核规范
	 */
	public E_checkrule possess(E_checkrule data){
		Dao<E_item> dao=DaoFactory.create(E_item.class);
		Dao<E_checkrule> checkruleDao=DaoFactory.create(E_checkrule.class);
		data.setItemName(dao.selectByID(data.getItemLineid()).getItemName());
		String getMaxIdFullPath2="com.unlcn.ils.tps.E_checkruleMapper.getMaxId";
		List<E_checkrule> maxIdList2=checkruleDao.getSession().selectList(getMaxIdFullPath2);
		int maxCheckRuleId=1;
		if(maxIdList2.size()!=0){
			maxCheckRuleId=maxIdList2.get(0).getLineid();
			maxCheckRuleId++;
		}
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		data.setLineid(maxCheckRuleId);
		data.setCreateTime(timestampnow);
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		data.setCreateUsername(username);
		data.setActive(0);
		return data;
	}
	/**
	 * 
	 * @param data
	 * @return
	 * @Description:更新考核规范
	 */
	public E_checkrule updateCheckrule(E_checkrule data){
		Dao<E_checkrule> checkruleDao=DaoFactory.create(E_checkrule.class);
		E_checkrule tempCheckrule=checkruleDao.selectByID(data.getLineid());
		data.setCreateUsername(tempCheckrule.getCreateUsername());
		data.setCreateTime(tempCheckrule.getCreateTime());
		data.setActive(0);
		checkruleDao.update(data);
		return data;
	}
	
}
