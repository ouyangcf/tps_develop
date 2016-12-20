package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 考核冻结单表头
 * @author  
 * @generated 
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_frozenhead", table = "tps_frozen_head", ds = "ilsdb")
public class E_frozenhead implements Serializable {
	private static final long serialVersionUID = 1683630541144064L;
	/**
	 *
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *
	 */
	@Column(id = "checkhead_lineid", datatype = "string32")
	private java.lang.String checkheadLineid;

	/**
	 *冻结金额
	 */
	@Column(id = "frozenvalue", datatype = "bigdecimal")
	private java.math.BigDecimal frozenvalue;

	/**
	 *冻结状态(0：已冻结，1：已取消)
	 */
	@Column(id = "frozen_status", datatype = "int")
	private java.lang.Integer frozenStatus;

	/**
	 *取消冻结金额
	 */
	@Column(id = "cancelfrozenvalue", datatype = "bigdecimal")
	private java.math.BigDecimal cancelfrozenvalue;

	/**
	 *冻结开始日期
	 */
	@Column(id = "start_date", datatype = "timestamp")
	private java.sql.Timestamp startDate;

	/**
	 *冻结结束日期
	 */
	@Column(id = "end_date", datatype = "timestamp")
	private java.sql.Timestamp endDate;

	/**
	 *创建人
	 */
	@Column(id = "create_username", datatype = "string64")
	private java.lang.String createUsername;

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
	 * 设置
	 */
	public void setCheckheadLineid(java.lang.String checkheadLineid) {
		this.checkheadLineid = checkheadLineid;
	}

	/**
	 * 获取
	 */
	public java.lang.String getCheckheadLineid() {
		return checkheadLineid;
	}

	/**
	 * 设置冻结金额
	 */
	public void setFrozenvalue(java.math.BigDecimal frozenvalue) {
		this.frozenvalue = frozenvalue;
	}

	/**
	 * 获取冻结金额
	 */
	public java.math.BigDecimal getFrozenvalue() {
		return frozenvalue;
	}

	/**
	 * 设置冻结状态(0：已冻结，1：已取消)
	 */
	public void setFrozenStatus(java.lang.Integer frozenStatus) {
		this.frozenStatus = frozenStatus;
	}

	/**
	 * 获取冻结状态(0：已冻结，1：已取消)
	 */
	public java.lang.Integer getFrozenStatus() {
		return frozenStatus;
	}

	/**
	 * 设置取消冻结金额
	 */
	public void setCancelfrozenvalue(java.math.BigDecimal cancelfrozenvalue) {
		this.cancelfrozenvalue = cancelfrozenvalue;
	}

	/**
	 * 获取取消冻结金额
	 */
	public java.math.BigDecimal getCancelfrozenvalue() {
		return cancelfrozenvalue;
	}

	/**
	 * 设置冻结开始日期
	 */
	public void setStartDate(java.sql.Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * 获取冻结开始日期
	 */
	public java.sql.Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * 设置冻结结束日期
	 */
	public void setEndDate(java.sql.Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取冻结结束日期
	 */
	public java.sql.Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * 设置创建人
	 */
	public void setCreateUsername(java.lang.String createUsername) {
		this.createUsername = createUsername;
	}

	/**
	 * 获取创建人
	 */
	public java.lang.String getCreateUsername() {
		return createUsername;
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
