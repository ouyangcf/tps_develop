package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 沟通方式表
 * @author 
 * @generated
 */ 
@Entity(id = "entity:com.unlcn.ils.tps.e_audit_tool", table = "tps_audit_tool", ds = "ilsdb")
public class E_audit_tool implements Serializable {
	private static final long serialVersionUID = 1786490056982528L;
	/**
	 *主键
	 */
	@Column(id = "lineid", type = ColumnType.increment, datatype = "int")
	private java.lang.Integer lineid;

	/**
	 *沟通方式
	 */
	@Column(id = "audit_tool_name", datatype = "string64")
	private java.lang.String auditToolName;

	/**
	 *创建人
	 */
	@Column(id = "create_user", datatype = "string64")
	private java.lang.String createUser;

	/**
	 *创建时间
	 */
	@Column(id = "create_time", datatype = "timestamp")
	private java.sql.Timestamp createTime;

	/**
	 *禁用标志
	 */
	@Column(id = "active", datatype = "tinyint")
	private java.lang.Integer active;

	/**
	 *禁用时间
	 */
	@Column(id = "active_time", datatype = "timestamp")
	private java.sql.Timestamp activeTime;

	/**
	 *禁用人
	 */
	@Column(id = "active_user", datatype = "string64")
	private java.lang.String activeUser;

	/**
	 * 设置主键
	 */
	public void setLineid(java.lang.Integer lineid) {
		this.lineid = lineid;
	}

	/**
	 * 获取主键
	 */
	public java.lang.Integer getLineid() {
		return lineid;
	}

	/**
	 * 设置沟通方式
	 */
	public void setAuditToolName(java.lang.String auditToolName) {
		this.auditToolName = auditToolName;
	}

	/**
	 * 获取沟通方式
	 */
	public java.lang.String getAuditToolName() {
		return auditToolName;
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
	 * 设置禁用标志
	 */
	public void setActive(java.lang.Integer active) {
		this.active = active;
	}

	/**
	 * 获取禁用标志
	 */
	public java.lang.Integer getActive() {
		return active;
	}

	/**
	 * 设置禁用时间
	 */
	public void setActiveTime(java.sql.Timestamp activeTime) {
		this.activeTime = activeTime;
	}

	/**
	 * 获取禁用时间
	 */
	public java.sql.Timestamp getActiveTime() {
		return activeTime;
	}

	/**
	 * 设置禁用人
	 */
	public void setActiveUser(java.lang.String activeUser) {
		this.activeUser = activeUser;
	}

	/**
	 * 获取禁用人
	 */
	public java.lang.String getActiveUser() {
		return activeUser;
	}
}
