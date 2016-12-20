package com.unlcn.ils.tps.services;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
import com.unlcn.ils.tps.E_linkman;

public class JoinLinkman implements ArrayContentProvider{

	@Override
	public Page<Map<String,String>> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_linkman> dao = DaoFactory.create(E_linkman.class); 
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_linkman> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		List<E_linkman> lists=result.getContents();
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		//logger.info("开始了");
		//logger.info("长度为"+lists.size());
		for(int i=0;i<lists.size();i++){
			E_linkman e_temp=lists.get(i);
			Map<String, String> map=new HashMap<String, String>();	
			map.put("id", e_temp.getId());
			
			if (e_temp.getDepartment()!=null) {
				map.put("department", e_temp.getDepartment());
			}
			if (e_temp.getJob()!=null) {
				map.put("job", e_temp.getJob());
			}
			if (e_temp.getName()!=null) {
				map.put("name", e_temp.getName());
			}
			if (e_temp.getPhone()!=null) {
				map.put("phone", e_temp.getPhone());
			}
			if (e_temp.getTel()!=null) {
				map.put("tel", e_temp.getTel());
			}
			if (e_temp.getEmail()!=null) {
				map.put("email", e_temp.getEmail());
			}
			if (e_temp.getQq()!=null) {
				map.put("qq", e_temp.getQq());
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
