package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 报价单审核规则设置表
 * @author  
 * @generated 
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_quotation_audit_set", table = "tps_quotation_audit_set", ds = "ilsdb")
public class E_quotation_audit_set implements Serializable {
	private static final long serialVersionUID = 1771433376514048L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.increment, datatype = "int")
	private java.lang.Integer lineid;

	/**
	 *报价类型
	 */
	@Column(id = "type", datatype = "int")
	private java.lang.Integer type;

	/**
	 *审核序号
	 */
	@Column(id = "flowid", datatype = "int")
	private java.lang.Integer flowid;

	/**
	 *溢价比例起点
	 */
	@Column(id = "overflow_down", datatype = "bigdecimal")
	private java.math.BigDecimal overflowDown;

	/**
	 *溢价比例终点
	 */
	@Column(id = "overflow_up", datatype = "bigdecimal")
	private java.math.BigDecimal overflowUp;

	/**
	 *审核人1
	 */
	@Column(id = "audit_user", datatype = "string32")
	private java.lang.String auditUser;

	/**
	 *不通过可指定人
	 */
	@Column(id = "assignuser", datatype = "tinyint")
	private java.lang.Integer assignuser;

	/**
	 *同一序号审核规则
	 */
	@Column(id = "audit_rule", datatype = "int")
	private java.lang.Integer auditRule;

	/**
	 *创建人
	 */
	@Column(id = "create_user", datatype = "string32")
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
	public void setLineid(java.lang.Integer lineid) {
		this.lineid = lineid;
	}

	/**
	 * 获取序号
	 */
	public java.lang.Integer getLineid() {
		return lineid;
	}

	/**
	 * 设置报价类型
	 */
	public void setType(java.lang.Integer type) {
		this.type = type;
	}

	/**
	 * 获取报价类型
	 */
	public java.lang.Integer getType() {
		return type;
	}

	/**
	 * 设置审核序号
	 */
	public void setFlowid(java.lang.Integer flowid) {
		this.flowid = flowid;
	}

	/**
	 * 获取审核序号
	 */
	public java.lang.Integer getFlowid() {
		return flowid;
	}

	/**
	 * 设置溢价比例起点
	 */
	public void setOverflowDown(java.math.BigDecimal overflowDown) {
		this.overflowDown = overflowDown;
	}

	/**
	 * 获取溢价比例起点
	 */
	public java.math.BigDecimal getOverflowDown() {
		return overflowDown;
	}

	/**
	 * 设置溢价比例终点
	 */
	public void setOverflowUp(java.math.BigDecimal overflowUp) {
		this.overflowUp = overflowUp;
	}

	/**
	 * 获取溢价比例终点
	 */
	public java.math.BigDecimal getOverflowUp() {
		return overflowUp;
	}

	/**
	 * 设置审核人1
	 */
	public void setAuditUser(java.lang.String auditUser) {
		this.auditUser = auditUser;
	}

	/**
	 * 获取审核人1
	 */
	public java.lang.String getAuditUser() {
		return auditUser;
	}

	/**
	 * 设置不通过可指定人
	 */
	public void setAssignuser(java.lang.Integer assignuser) {
		this.assignuser = assignuser;
	}

	/**
	 * 获取不通过可指定人
	 */
	public java.lang.Integer getAssignuser() {
		return assignuser;
	}

	/**
	 * 设置同一序号审核规则
	 */
	public void setAuditRule(java.lang.Integer auditRule) {
		this.auditRule = auditRule;
	}

	/**
	 * 获取同一序号审核规则
	 */
	public java.lang.Integer getAuditRule() {
		return auditRule;
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
