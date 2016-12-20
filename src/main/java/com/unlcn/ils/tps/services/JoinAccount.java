package com.unlcn.ils.tps.services;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
import com.unlcn.ils.tps.E_account;
import com.unlcn.ils.tps.E_join_intentline;

public class JoinAccount implements ArrayContentProvider{
	private static Logger logger=Logger.getLogger(Logger.class );
	@Override
	public Page<Map<String,String>> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_account> dao = DaoFactory.create(E_account.class); 
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_account> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		List<E_account> lists=result.getContents();
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		logger.info("开始了");
		logger.info("长度为"+lists.size());
		for(int i=0;i<lists.size();i++){
			E_account e_temp=lists.get(i);
			Map<String, String> map=new HashMap<String, String>();	
			map.put("id", e_temp.getId());
			map.put("ownerType", e_temp.getOwnerType());
			map.put("name", e_temp.getName());
			map.put("bank", e_temp.getBank());
			map.put("accountNumber", e_temp.getAccountNumber());
			map.put("bankOpenBranch", e_temp.getBankOpenBranch());
			map.put("ownerName", e_temp.getOwnerName());
			map.put("type", e_temp.getType());
			//map.put("name", e_temp.getName());
			if (e_temp.getDesc()!=null) {
				map.put("desc", e_temp.getDesc());
			}
			if (e_temp.getAccountFilepath()!=null) {
				map.put("accountFilepath", e_temp.getAccountFilepath());
			}
			map.put("customerId", e_temp.getCustomerId());
			map.put("flag", e_temp.getFlag().toString());
			updateList.add(map);
		}
		Page<Map<String,String>> result1=new Page<Map<String,String>>();
		result1.setPageIndex(result.getPageIndex());
		result1.setPageSize(result.getPageSize());
		result1.setTotal(result.getTotal());
		result1.setContents(updateList);
		return result1;
	}

}
