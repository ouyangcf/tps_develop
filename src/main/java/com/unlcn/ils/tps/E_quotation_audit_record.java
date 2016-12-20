package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 报价单审核记录
 * @author   
 * @generated
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_quotation_audit_record", table = "tps_quotation_audit_record", ds = "ilsdb")
public class E_quotation_audit_record implements Serializable {
	private static final long serialVersionUID = 1773654154231808L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *报价单序号
	 */
	@Column(id = "quotation_id", association = true)
	private E_quotation_head quotationId;

	/**
	 *审核人
	 */
	@Column(id = "audit_user", datatype = "string64")
	private java.lang.String auditUser;

	/**
	 *审核时间
	 */
	@Column(id = "audit_datetime", datatype = "timestamp")
	private java.sql.Timestamp auditDatetime;

	/**
	 *审核结果状态
	 */
	@Column(id = "audit_flag", datatype = "int")
	private java.lang.Integer auditFlag;

	/**
	 *审核说明
	 */
	@Column(id = "audit_memo", datatype = "string512")
	private java.lang.String auditMemo;

	/**
	 *审核的步骤
	 */
	@Column(id = "audit_flowid", datatype = "int")
	private java.lang.Integer auditFlowid;

	/**
	 *不通过转回规则
	 */
	@Column(id = "return_flag", datatype = "int")
	private java.lang.Integer returnFlag;

	/**
	 *不通过指定重审人
	 */
	@Column(id = "return_audit_user", datatype = "string32")
	private java.lang.String returnAuditUser;

	/**
	 *
	 */
	@Column(id = "create_user", datatype = "string64")
	private java.lang.String createUser;

	/**
	 *创建时间
	 */
	@Column(id = "create_time", datatype = "timestamp")
	private java.sql.Timestamp createTime;

	/**
	 *取消标志
	 */
	@Column(id = "active", datatype = "tinyint")
	private java.lang.Integer active;

	/**
	 *取消操作员
	 */
	@Column(id = "active_user", datatype = "string32")
	private java.lang.String activeUser;

	/**
	 *取消操作时间
	 */
	@Column(id = "active_time", datatype = "timestamp")
	private java.sql.Timestamp activeTime;

	/**
	 *取消说明
	 */
	@Column(id = "active_memo", datatype = "string256")
	private java.lang.String activeMemo;

	/**
	 * 设置序号
	 */
	public void setLineid(java.lang.String lineid) {
		this.lineid = lineid;
	}

	/**
	 * 获取序号
	 */
	public java.lang.String getLineid() {
		return lineid;
	}

	/**
	 * 设置报价单序号
	 */
	public void setQuotationId(E_quotation_head quotationId) {
		this.quotationId = quotationId;
	}

	/**
	 * 获取报价单序号
	 */
	public E_quotation_head getQuotationId() {
		return quotationId;
	}

	/**
	 * 设置审核人
	 */
	public void setAuditUser(java.lang.String auditUser) {
		this.auditUser = auditUser;
	}

	/**
	 * 获取审核人
	 */
	public java.lang.String getAuditUser() {
		return auditUser;
	}

	/**
	 * 设置审核时间
	 */
	public void setAuditDatetime(java.sql.Timestamp auditDatetime) {
		this.auditDatetime = auditDatetime;
	}

	/**
	 * 获取审核时间
	 */
	public java.sql.Timestamp getAuditDatetime() {
		return auditDatetime;
	}

	/**
	 * 设置审核结果状态
	 */
	public void setAuditFlag(java.lang.Integer auditFlag) {
		this.auditFlag = auditFlag;
	}

	/**
	 * 获取审核结果状态
	 */
	public java.lang.Integer getAuditFlag() {
		return auditFlag;
	}

	/**
	 * 设置审核说明
	 */
	public void setAuditMemo(java.lang.String auditMemo) {
		this.auditMemo = auditMemo;
	}

	/**
	 * 获取审核说明
	 */
	public java.lang.String getAuditMemo() {
		return auditMemo;
	}

	/**
	 * 设置审核的步骤
	 */
	public void setAuditFlowid(java.lang.Integer auditFlowid) {
		this.auditFlowid = auditFlowid;
	}

	/**
	 * 获取审核的步骤
	 */
	public java.lang.Integer getAuditFlowid() {
		return auditFlowid;
	}

	/**
	 * 设置不通过转回规则
	 */
	public void setReturnFlag(java.lang.Integer returnFlag) {
		this.returnFlag = returnFlag;
	}

	/**
	 * 获取不通过转回规则
	 */
	public java.lang.Integer getReturnFlag() {
		return returnFlag;
	}

	/**
	 * 设置不通过指定重审人
	 */
	public void setReturnAuditUser(java.lang.String returnAuditUser) {
		this.returnAuditUser = returnAuditUser;
	}

	/**
	 * 获取不通过指定重审人
	 */
	public java.lang.String getReturnAuditUser() {
		return returnAuditUser;
	}

	/**
	 * 设置
	 */
	public void setCreateUser(java.lang.String createUser) {
		this.createUser = createUser;
	}

	/**
	 * 获取
	 */
	public java.lang.String getCreateUser() {
		return createUser;
	}

	/**
	 * 设置创建时间
	 */
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取创建时间
	 */
	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * 设置取消标志
	 */
	public void setActive(java.lang.Integer active) {
		this.active = active;
	}

	/**
	 * 获取取消标志
	 */
	public java.lang.Integer getActive() {
		return active;
	}

	/**
	 * 设置取消操作员
	 */
	public void setActiveUser(java.lang.String activeUser) {
		this.activeUser = activeUser;
	}

	/**
	 * 获取取消操作员
	 */
	public java.lang.String getActiveUser() {
		return activeUser;
	}

	/**
	 * 设置取消操作时间
	 */
	public void setActiveTime(java.sql.Timestamp activeTime) {
		this.activeTime = activeTime;
	}

	/**
	 * 获取取消操作时间
	 */
	public java.sql.Timestamp getActiveTime() {
		return activeTime;
	}

	/**
	 * 设置取消说明
	 */
	public void setActiveMemo(java.lang.String activeMemo) {
		this.activeMemo = activeMemo;
	}

	/**
	 * 获取取消说明
	 */
	public java.lang.String getActiveMemo() {
		return activeMemo;
	}
}
