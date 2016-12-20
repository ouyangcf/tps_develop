package com.unlcn.ils.tps.services;

import java.sql.Timestamp; 
import java.util.Map;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
import com.unlcn.ils.tps.E_deduct;
import com.unlcn.ils.tps.E_revise;
import com.unlcn.ils.tps.ininterface.SqlInterface;
/**
 * 
 *@Title:
 *@Description:扣款单服务
 *@Author:Administrator 
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class DeductService implements ArrayContentProvider{
	/**
	 * 
	 * @param data
	 * @return
	 * @Description:扣款单处理
	 */
	@SuppressWarnings({ "static-access", "unused" })
	public E_deduct pocessDeduct(E_deduct data){
		Dao<E_revise> reviseDao=new DaoFactory().create(E_revise.class);
		Dao<E_deduct> deductDao=new DaoFactory().create(E_deduct.class);
		E_revise e_revise=reviseDao.selectByID(data.getReviseLineid());
		data.setCheckheadLineid(e_revise.getCheckheadLineid());
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		data.setCreateTime(timestampnow);
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		data.setCreateUser(username);
		SqlInterface sqlInterface=new SqlInterface();
		data.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_DEDUCT"));
		return data;
	}

	@Override
	public Page<E_deduct> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_deduct> dao = DaoFactory.create(E_deduct.class) ;
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_deduct> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		
		return result;
	}
	
}
