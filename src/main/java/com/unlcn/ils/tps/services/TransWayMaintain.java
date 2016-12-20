package com.unlcn.ils.tps.services;

import java.util.Date; 
import java.util.List;

import com.unlcn.ils.tps.E_transway;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;

public class TransWayMaintain {
	/**
	 * @param data
	 * @return
	 * @Description:新增运输方式字典
	 */
	public E_transway add(E_transway data){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		data.setActive(1);
		data.setActiveMemo("");
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		data.setActiveTime(currdate);
		data.setActiveUser("");
		data.setCreateTime(currdate);
		data.setCreateUser(username); 
		
		return data;
	}
	/**
	 * 
	 * @param idList
	 * @param data
	 * @return
	 * @Description:运输方式编辑
	 */
	@SuppressWarnings("static-access")
	public boolean transwayEdit(Integer idList,E_transway data ){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_transway> reasonDao=new DaoFactory().create(E_transway.class);
		E_transway payment=reasonDao.selectByID(idList);
			java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
			payment.setCreateTime(currdate);
			payment.setCreateUser(username);
			if(data.getTransName()!=null)
				payment.setTransName(data.getTransName());
			reasonDao.update(payment);
		
			return false;
			
	}
	/**
	 * 
	 * @param idList
	 * @param data
	 * @return
	 * @Description:运输方式禁用
	 */
	@SuppressWarnings("static-access")
	public boolean transwayProhibit(List<Integer> idList ,String data ){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_transway> reasonDao=new DaoFactory().create(E_transway.class);
		for(int i=0;i<idList.size();i++){
			E_transway payment=reasonDao.selectByID(idList.get(i));
			payment.setActive(0);
			java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
			payment.setActiveTime(currdate);
			payment.setActiveMemo(data);
			payment.setActiveUser(username);
			reasonDao.update(payment);
		}
			return false;
			
	}
	
	/**
	 * 
	 * @param idList
	 * @param data
	 * @return
	 * @Description:运输方式启用
	 */
	@SuppressWarnings("static-access")
	public boolean transwayOpen(List<Integer> idList ,String data ){ 
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_transway> reasonDao=new DaoFactory().create(E_transway.class);
		for(int i=0;i<idList.size();i++){
			E_transway payment=reasonDao.selectByID(idList.get(i));
			payment.setActive(1);
			java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
			payment.setActiveTime(currdate);
			payment.setActiveMemo(data);
			payment.setActiveUser(username);
			reasonDao.update(payment);
		}
			return false;
			
	}
}
