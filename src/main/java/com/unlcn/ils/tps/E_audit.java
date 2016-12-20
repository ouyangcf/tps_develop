package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 分供方初审表
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_audit", table = "tps_audit", ds = "ilsdb")
public class E_audit implements Serializable {
	private static final long serialVersionUID = 1744311198334976L;
	/**
	 *
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *初审人
	 */
	@Column(id = "audit_user", datatype = "string32")
	private java.lang.String auditUser;

	/**
	 *初审时间
	 */
	@Column(id = "audit_time", datatype = "timestamp")
	private java.sql.Timestamp auditTime;

	/**
	 *初审意见
	 */
	@Column(id = "audit_memo", datatype = "string512")
	private java.lang.String auditMemo;

	/**
	 *申请id
	 */
	@Column(id = "join_id", datatype = "string32")
	private java.lang.String joinId;

	/**
	 *初审结果
	 */
	@Column(id = "audit_flag", datatype = "int")
	private java.lang.Integer auditFlag;

	/**
	 *初审沟通方式
	 */
	@Column(id = "audit_tool", datatype = "int")
	private java.lang.Integer auditTool;

	/**
	 *加盟/合作
	 */
	@Column(id = "join_flag", datatype = "int")
	private java.lang.Integer joinFlag;

	/**
	 *有效日期
	 */
	@Column(id = "begin_date", datatype = "date")
	private java.sql.Date beginDate;

	/**
	 *失效日期
	 */
	@Column(id = "end_date", datatype = "date")
	private java.sql.Date endDate;

	/**
	 *创建时间
	 */
	@Column(id = "create_time", datatype = "timestamp")
	private java.sql.Timestamp createTime;

	/**
	 *创建人
	 */
	@Column(id = "create_user", datatype = "string32")
	private java.lang.String createUser;

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
	 * 设置
	 */
	public void setLineid(java.lang.String lineid) {
		this.lineid = lineid;
	}

	/**
	 * 获取
	 */
	public java.lang.String getLineid() {
		return lineid;
	}

	/**
	 * 设置初审人
	 */
	public void setAuditUser(java.lang.String auditUser) {
		this.auditUser = auditUser;
	}

	/**
	 * 获取初审人
	 */
	public java.lang.String getAuditUser() {
		return auditUser;
	}

	/**
	 * 设置初审时间
	 */
	public void setAuditTime(java.sql.Timestamp auditTime) {
		this.auditTime = auditTime;
	}

	/**
	 * 获取初审时间
	 */
	public java.sql.Timestamp getAuditTime() {
		return auditTime;
	}

	/**
	 * 设置初审意见
	 */
	public void setAuditMemo(java.lang.String auditMemo) {
		this.auditMemo = auditMemo;
	}

	/**
	 * 获取初审意见
	 */
	public java.lang.String getAuditMemo() {
		return auditMemo;
	}

	/**
	 * 设置申请id
	 */
	public void setJoinId(java.lang.String joinId) {
		this.joinId = joinId;
	}

	/**
	 * 获取申请id
	 */
	public java.lang.String getJoinId() {
		return joinId;
	}

	/**
	 * 设置初审结果
	 */
	public void setAuditFlag(java.lang.Integer auditFlag) {
		this.auditFlag = auditFlag;
	}

	/**
	 * 获取初审结果
	 */
	public java.lang.Integer getAuditFlag() {
		return auditFlag;
	}

	/**
	 * 设置初审沟通方式
	 */
	public void setAuditTool(java.lang.Integer auditTool) {
		this.auditTool = auditTool;
	}

	/**
	 * 获取初审沟通方式
	 */
	public java.lang.Integer getAuditTool() {
		return auditTool;
	}

	/**
	 * 设置加盟/合作
	 */
	public void setJoinFlag(java.lang.Integer joinFlag) {
		this.joinFlag = joinFlag;
	}

	/**
	 * 获取加盟/合作
	 */
	public java.lang.Integer getJoinFlag() {
		return joinFlag;
	}

	/**
	 * 设置有效日期
	 */
	public void setBeginDate(java.sql.Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取有效日期
	 */
	public java.sql.Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置失效日期
	 */
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取失效日期
	 */
	public java.sql.Date getEndDate() {
		return endDate;
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
	 * 设置创建人
	 */
	public void setCreateUser(java.lang.String createUser) {
		this.createUser = createUser;
	}

	/**
	 * 获取创建人
	 */
	public java.lang.String getCreateUser() {
		return createUser;
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
