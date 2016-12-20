package com.unlcn.ils.tps.services; 

import java.util.Date;
import java.util.List;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.unlcn.ils.tps.E_audit_tool;


public class AuditToolMaintain {
	/**
	 * @param data
	 * @return
	 * @Description:新增沟通方式字典
	 */
	public E_audit_tool add(E_audit_tool data){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		data.setActive(1);
		//data.setActiveMemo("");
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
	public boolean auditToolEdit(Integer idList,E_audit_tool data ){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_audit_tool> reasonDao=new DaoFactory().create(E_audit_tool.class);
		E_audit_tool payment=reasonDao.selectByID(idList);
			java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
			payment.setCreateTime(currdate);
			payment.setCreateUser(username);
			if(data.getAuditToolName()!=null)
				payment.setAuditToolName(data.getAuditToolName());
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
	public boolean auditToolProhibit(List<Integer> idList){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_audit_tool> reasonDao=new DaoFactory().create(E_audit_tool.class);
		for(int i=0;i<idList.size();i++){
			E_audit_tool payment=reasonDao.selectByID(idList.get(i));
			payment.setActive(0);
			java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
			payment.setActiveTime(currdate);
			//payment.setActiveMemo(data);
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
	 * @Description:支付周期维护启用
	 */
	@SuppressWarnings("static-access")
	public boolean auditToolOpen(List<Integer> idList){ 
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_audit_tool> reasonDao=new DaoFactory().create(E_audit_tool.class);
		for(int i=0;i<idList.size();i++){
			E_audit_tool payment=reasonDao.selectByID(idList.get(i));
			payment.setActive(1);
			java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
			payment.setActiveTime(currdate);
			//payment.setActiveMemo(data);
			payment.setActiveUser(username);
			reasonDao.update(payment);
		}
			return false;
			
	}
	/**
	 * 
	 * @param idList
	 * @return
	 * @Description:支付周期维护删除
	 */
	/*
	public boolean payPeriodDelete(Integer idList){
		
		Dao<E_pay_period> dao=new DaoFactory().create(E_pay_period.class);
		E_pay_period listl=dao.selectByID(idList);
		dao.delete(listl);
				
			return true;
		}
*/

}
