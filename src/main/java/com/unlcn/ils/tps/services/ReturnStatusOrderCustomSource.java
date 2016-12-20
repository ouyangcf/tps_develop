package com.unlcn.ils.tps.services;

import java.util.ArrayList;  
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Rule;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

public class ReturnStatusOrderCustomSource implements ArrayContentProvider{

	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public Page<?> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		ErmDetailService detailService=new ErmDetailService();
		Map<String, Object> conditions = context.getCondition();
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		List<Rule> rules = condition.getRules();
		String lineid=new String();
		for ( int i=0;i<rules.size();i++)
		{//得到查询条件
			Rule rule = rules.get(i);
			String field = rule.getField();
			String data = (String) rule.getData();
			String op = rule.getOp();
			if ( field.equals("lineid")){
				if(data==null){
				}
				else{
				}
				lineid=data;
			}
		}
		Map getDatas=detailService.getComPlainDetails(lineid); 
		List<Map<String, String>> datas=detailService.getReturnStatusOrder(lineid);
		int pagesize = context.getPageable().getPageSize();
		int pageindex = context.getPageable().getPageIndex();
		Page<Map<String,String>> resultdata = new Page<Map<String,String>>();
		resultdata.setPageIndex(pageindex);
		resultdata.setPageSize(pagesize);
		resultdata.setTotal(datas.size());
		List<Map<String, String>>  resultList = getDataByPage(pageindex, pagesize, datas);
		resultdata.setContents(resultList);
		return resultdata;
	}
	private List<Map<String, String>> getDataByPage(int page,int size,List<Map<String, String>> datas){
		List<Map<String, String>> returnList=new ArrayList<Map<String, String>>();
		int min=page*size<=datas.size()?page*size:datas.size();
		for(int i=(page-1)*size;i<min;i++){
			returnList.add(datas.get(i));
		}
		return returnList;
	}
}
