package com.unlcn.ils.tps.services;

import java.sql.Timestamp;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_checkrule;
import com.unlcn.ils.tps.E_configure_detail;
import com.unlcn.ils.tps.E_item;
import com.unlcn.ils.tps.E_method;
import com.unlcn.ils.tps.ininterface.SqlInterface;

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
 *@Description:考核配置明细服务
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0 
 */
public class ConfigureDetailService implements ArrayContentProvider{
 
	@SuppressWarnings("unused")
	@Override 
	public Page<Map<String,String>> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_configure_detail> dao = DaoFactory.create(E_configure_detail.class) ;
		Dao<E_checkrule> checkruleDao = DaoFactory.create(E_checkrule.class) ;
		Dao<E_item> itemDao = DaoFactory.create(E_item.class) ;
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_configure_detail> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
	
		List<E_configure_detail> lists=result.getContents();
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		for(int i=0;i<lists.size();i++){
			Map<String, String> map=new HashMap<String, String>();
			E_configure_detail tempConfigure_detail=lists.get(i);
			map.put("lineid", tempConfigure_detail.getLineid().toString());
			map.put("configureLineid", tempConfigure_detail.getConfigureLineid().toString());
			map.put("itemLineid", tempConfigure_detail.getItemName().toString());
			E_item e_item=itemDao.selectByID(tempConfigure_detail.getItemName());
			map.put("itemName", e_item.getItemName());
			map.put("opearateName", tempConfigure_detail.getOpearateName());
			map.put("description", tempConfigure_detail.getDescription());
			map.put("weight", tempConfigure_detail.getWeight().toString());
			map.put("requirements", tempConfigure_detail.getRequirements());
			map.put("totalvalue", tempConfigure_detail.getTotalvalue().toString());
			map.put("subvalue", tempConfigure_detail.getSubvalue().toString());
			map.put("lowvalue", tempConfigure_detail.getLowvalue().toString());
			map.put("calDescription", tempConfigure_detail.getCalDescription());
			//map.put("checkvalue", tempConfigure_detail.getCheckvalue().toString());
			if(tempConfigure_detail.getStartDate()!=null){	
				map.put("startDate", tempConfigure_detail.getStartDate().toString());	
			}
			if(tempConfigure_detail.getEndDate()!=null){
				map.put("endDate", tempConfigure_detail.getEndDate().toString());
			}			
			updateList.add(map);
		}
		Page<Map<String,String>> result1=new Page<Map<String,String>>();
		result1.setPageIndex(result.getPageIndex());
		result1.setPageSize(result.getPageSize());
		result1.setTotal(result.getTotal());
		result1.setContents(updateList);
		return result1;	
	}
	/**
	 * 
	 * @param data
	 * @return
	 * @Description:考核配置表明细处理
	 */
	@SuppressWarnings({ "unused" })
	public E_configure_detail posess(E_configure_detail data){
		/*Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
		E_checkrule temp=checkruleDao.selectByID(Integer.parseInt(data.getOpearateName()));
		data.setOpearateName(temp.getOperateName());*/
		Dao<E_method> methodDao=DaoFactory.create(E_method.class);
		E_method e_method=new E_method();
		e_method=methodDao.selectByID(Integer.valueOf(data.getCalDescription()));
		data.setCalDescription(e_method.getMethod());
		Dao<E_configure_detail> dao = DaoFactory.create(E_configure_detail.class) ;
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		data.setCreateUsername(username);
		data.setCreateTime(timestampnow);
		if(data.getLineid()==null){
			SqlInterface sqlInterface=new SqlInterface();
			data.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_CONFIGURE_DETAIL"));
		}
		return data;
	}
	/**
	 * 
	 * @param data
	 * @return
	 * @Description:考核配置表明细处理
	 */
	@SuppressWarnings("static-access")
	public E_configure_detail posessn(E_configure_detail data){
		Dao<E_configure_detail> dao = DaoFactory.create(E_configure_detail.class) ;
		Dao<E_item> itemDao=new DaoFactory().create(E_item.class);
		Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
		E_item newItem=itemDao.selectByID(data.getItemName());
		newItem.setItemName(data.getDescription());
		itemDao.update(newItem);
		String pathString="com.unlcn.ils.tps.E_checkruleMapper.selectByItemLineID";
		List<E_checkrule> list=checkruleDao.getSession().selectList(pathString, data.getItemName());
		for(int i=0;i<list.size();i++){
			E_checkrule tempCheckrule=list.get(i);
			tempCheckrule.setItemName(data.getDescription());
			checkruleDao.update(tempCheckrule);
		}
		dao.update(data);
		return data;
	}
}
