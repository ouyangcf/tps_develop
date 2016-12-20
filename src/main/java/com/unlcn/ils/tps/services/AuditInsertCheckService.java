package com.unlcn.ils.tps.services; 

import java.util.Date;

import com.unlcn.ils.tps.E_audit;
/**
 * 
 *@Title:
 *@Description:认证检测服务  
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class AuditInsertCheckService {
	/**
	 * 
	 * @param data
	 * @return
	 * @Description:检测
	 */
	public E_audit checkdata(E_audit data)
	{
		
		//检查有效期
		if ( data.getBeginDate().after(data.getEndDate()) )
		{
			throw new RuntimeException("错误的有效期，请检查数据");
		}
		
		//加载创建日期和取消标志
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		data.setCreateTime(currdate);
		data.setAuditTime(currdate);
		data.setActive(1);
		data.setCreateUser("领导");
		data.setAuditUser("领导");
		
		
		return data;
	}
}
