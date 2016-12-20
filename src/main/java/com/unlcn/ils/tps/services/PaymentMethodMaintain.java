package com.unlcn.ils.tps.services;

import java.util.Date; 
import java.util.List;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;

import com.unlcn.ils.tps.E_payment_method;

public class PaymentMethodMaintain {
	/**
	 * @param data
	 * @return
	 * @Description:新增支付方式字典
	 */
	public E_payment_method add(E_payment_method data){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		data.setPaymentNo("xxx");
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
	 * @Description:支付方式编辑
	 */
	@SuppressWarnings("static-access")
	public boolean paymentMethodEdit(Integer idList,E_payment_method data ){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_payment_method> reasonDao=new DaoFactory().create(E_payment_method.class);
			E_payment_method payment=reasonDao.selectByID(idList);
			java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
			payment.setCreateTime(currdate);
			payment.setCreateUser(username);
			if(data.getPaymentName()!=null)
				payment.setPaymentName(data.getPaymentName());
			if(data.getPaymentType()!=null)
				payment.setPaymentType(data.getPaymentType());
			reasonDao.update(payment);
		
			return false;
			
	}
	/**
	 * 
	 * @param idList
	 * @param data
	 * @return
	 * @Description:支付方式维护禁用
	 */
	@SuppressWarnings("static-access")
	public boolean paymentProhibit(List<Integer> idList ,String data ){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_payment_method> reasonDao=new DaoFactory().create(E_payment_method.class);
		for(int i=0;i<idList.size();i++){
			E_payment_method payment=reasonDao.selectByID(idList.get(i));
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
	 * @Description:支付方式维护启用
	 */
	@SuppressWarnings("static-access")
	public boolean paymentOpen(List<Integer> idList ,String data ){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_payment_method> reasonDao=new DaoFactory().create(E_payment_method.class);
		for(int i=0;i<idList.size();i++){
			E_payment_method payment=reasonDao.selectByID(idList.get(i));
			payment.setActive(1);
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
	 * @return
	 * @Description:支付方式维护删除
	 */
	@SuppressWarnings("static-access")
	public boolean paymentDelete(Integer idList){
		
		Dao<E_payment_method> dao=new DaoFactory().create(E_payment_method.class);
		E_payment_method listl=dao.selectByID(idList);
		dao.delete(listl);
				
			return true;
		}
}
