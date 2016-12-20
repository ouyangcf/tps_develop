package com.unlcn.ils.tps.services;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_argue;
import com.unlcn.ils.tps.E_checkdetail;
import com.unlcn.ils.tps.E_checkdocument;
import com.unlcn.ils.tps.E_checkhead;
import com.unlcn.ils.tps.E_deduct;
import com.unlcn.ils.tps.E_frozen_detail;
import com.unlcn.ils.tps.E_frozenhead;
import com.unlcn.ils.tps.E_revise;
import com.unlcn.ils.tps.E_substract;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
/**
 * 
 *@Title:
 *@Description:冻结单表头服务 
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class FrozenHeadService implements ArrayContentProvider{

	@SuppressWarnings({ "static-access", "unused" })
	@Override
	public Page<Map<String,String>> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_deduct> deductDao = DaoFactory.create(E_deduct.class) ;
		Dao<E_substract> sbustractDao = DaoFactory.create(E_substract.class) ;
		Dao<E_revise> dao = DaoFactory.create(E_revise.class) ;
		Dao<E_argue> argueDao=new DaoFactory().create(E_argue.class);
		Dao<E_checkhead> checkheadDao=new DaoFactory().create(E_checkhead.class);
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		Dao<E_checkdocument> checkdocDao=new DaoFactory().create(E_checkdocument.class);
		Dao<E_frozen_detail> frozendetailDao=new DaoFactory().create(E_frozen_detail.class);
		Dao<E_frozenhead> frozenheadDao=new DaoFactory().create(E_frozenhead.class);
		String getFromCheckdetail="com.unlcn.ils.tps.E_checkdetailMapper.selectByCheckDocLineid";
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		//获取所有的明细表
		Page<E_frozenhead> result=frozenheadDao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);	
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		List<E_frozenhead> lists=result.getContents();
		for(int i=0;i<lists.size();i++){
			Map<String, String> map=new HashMap<String, String>();
			E_frozenhead e_frozenhead=lists.get(i);
			//先获取冻结单对应的考核记录
			E_checkhead e_checkhead=checkheadDao.selectByID(lists.get(i).getCheckheadLineid());
			//map.put("checkdoc", e_checkdocuments.getLineid());
			//map.put("flag", e_checkdocuments.getFlag().toString());
			map.put("shippername", e_checkhead.getShipperName());
			//List<E_checkdetail> e_checkdetails=checkdetailDao.getSession().selectList(getFromCheckdetail, e_checkdocuments.getLineid());
			//E_checkhead e_checkhead=checkheadDao.selectByID(e_checkdetails.get(0).getCheckheadLineid());
			map.put("Countmonth", e_checkhead.getCountmonth());
			//map.put("ItemName", e_checkdocuments.getItemName());
			//map.put("OperateName", e_checkdocuments.getOperateName());
			if(lists.get(i).getFrozenStatus()!=null)
			map.put("FrozenStatus", lists.get(i).getFrozenStatus().toString());
			
			map.put("StartDate", e_frozenhead.getStartDate().toString());
			map.put("EndDate", e_frozenhead.getEndDate().toString());
			map.put("CheckMoney", lists.get(i).getFrozenvalue().toString());		
			if(e_frozenhead.getCancelfrozenvalue()!=null)
			map.put("Cancelfrozenvalue", e_frozenhead.getCancelfrozenvalue().toString());
			if(e_frozenhead.getFrozenvalue()!=null)
			map.put("Frozenvalue", e_frozenhead.getFrozenvalue().toString());			
			if(e_checkhead.getFactSubmoney()!=null)
			map.put("factsubstract", e_checkhead.getFactSubmoney().toString());
			updateList.add(map);
		}
		Page<Map<String,String>> result1=new Page<Map<String,String>>();
		result1.setPageIndex(result.getPageIndex());
		result1.setPageSize(result.getPageSize());
		result1.setTotal(result.getTotal());
		result1.setContents(updateList);
		return result1;
	}
 
	@SuppressWarnings("static-access")
	public List<String> getByShipper(String ShipperId){
		List<String> idList=new ArrayList<String>();
		E_checkhead condition=new E_checkhead();
		Dao<E_checkhead> checkDao=new DaoFactory().create(E_checkhead.class);
		condition.setShipperLineid(ShipperId);
		List<E_checkhead> result=checkDao.select(condition);
		for (E_checkhead e_checkhead : result) {
			idList.add(e_checkhead.getLineid());
		}
		return idList;
	}
}
