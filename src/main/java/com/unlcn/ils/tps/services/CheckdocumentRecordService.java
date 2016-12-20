package com.unlcn.ils.tps.services; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_checkdocument;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
/**
 * 
 *@Title:
 *@Description:
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0 
 */
public class CheckdocumentRecordService implements ArrayContentProvider{

	@Override
	public Page<Map<String,String>> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_checkdocument> dao = DaoFactory.create(E_checkdocument.class) ;
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_checkdocument> result=dao.selectPageByCondition(conditions,
				condition,context.getPageable() ,context.getSortable(), true);
		List<E_checkdocument> lists=result.getContents();
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		for(int i=0;i<lists.size();i++)
		{
			E_checkdocument temp=lists.get(i);
			
			Map<String, String> map=new HashMap<String, String>();
			
				map.put("lineid", temp.getLineid().toString());
				map.put("flag", temp.getFlag().toString());
				map.put("shipperName", temp.getShipperName());
				map.put("creatTime", temp.getCreateTime().toString());
				map.put("itemName", temp.getItemName());
				map.put("OperateName", temp.getOperateName());
				map.put("Check_Memo", temp.getCheckMemo());
				map.put("Start_date", temp.getStartDate().toString());
				map.put("CheckMoney", temp.getCheckMoney().toString());
				map.put("CheckScoreStandard", temp.getCheckScoreStandard().toString());
				map.put("CheckScore", temp.getCheckScore().toString());
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
