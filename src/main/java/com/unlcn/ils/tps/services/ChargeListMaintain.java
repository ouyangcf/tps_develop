package com.unlcn.ils.tps.services;

import java.util.Date; 
import java.util.List;

import org.apache.log4j.Logger;

import com.unlcn.ils.tps.E_charge_list;
import com.unlcn.ils.tps.ininterface.SqlInterface;


import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;

public class ChargeListMaintain {
	private static Logger logger=Logger.getLogger(Logger.class);
	/**
	 * @param data 
	 * @return
	 * @Description:新增支付周期字典 
	 */
	public E_charge_list add(E_charge_list data){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		SqlInterface sqlInterface=new SqlInterface();
		String chargeListLineid=sqlInterface.getNextVal("SEQ_TPS_TPS_CHARGE_LIST");
		logger.info("获取的lineid为："+chargeListLineid);
		data.setLineid(chargeListLineid);
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
	 * @Description:支付周期编辑
	 */
	@SuppressWarnings("static-access")
	public boolean payPeriodEdit(Integer idList,E_charge_list data ){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_charge_list> reasonDao=new DaoFactory().create(E_charge_list.class);
		E_charge_list payment=reasonDao.selectByID(idList);
			java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
			payment.setCreateTime(currdate);
			payment.setCreateUser(username);
			if(data.getChargeName()!=null)
				payment.setChargeName(data.getChargeName());
			reasonDao.update(payment);
		
			return false;
			
	}
	/**
	 * 
	 * @param idList
	 * @param data
	 * @return
	 * @Description:支付周期维护禁用
	 */
	@SuppressWarnings("static-access")
	public boolean chargeListProhibit(List<Integer> idList ,String data ){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_charge_list> reasonDao=new DaoFactory().create(E_charge_list.class);
		for(int i=0;i<idList.size();i++){
			E_charge_list payment=reasonDao.selectByID(idList.get(i));
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
	public boolean chargeListOpen(List<Integer> idList ,String data ){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_charge_list> reasonDao=new DaoFactory().create(E_charge_list.class);
		for(int i=0;i<idList.size();i++){
			E_charge_list payment=reasonDao.selectByID(idList.get(i));
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
	public boolean chargeListDelete(Integer idList){
		
		Dao<E_charge_list> dao=new DaoFactory().create(E_charge_list.class);
		E_charge_list listl=dao.selectByID(idList);
		dao.delete(listl);
				
			return true;
		}

}
