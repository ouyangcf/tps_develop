package com.unlcn.ils.tps.services;

import java.util.Map; 

import com.unlcn.ils.tps.E_temp_return_status_order;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

public class TempReturnDataService implements ArrayContentProvider{

	@Override
	public Page<E_temp_return_status_order> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_temp_return_status_order> dao = DaoFactory.create(E_temp_return_status_order.class);
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_temp_return_status_order> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		return result;
	}

}
