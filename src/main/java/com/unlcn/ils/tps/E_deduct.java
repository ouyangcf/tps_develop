package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 扣款单
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_deduct", table = "tps_deduct", ds = "ilsdb")
public class E_deduct implements Serializable {
	private static final long serialVersionUID = 1689232657367040L;
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
	 *
	 */
	@Column(id = "revise_lineid", datatype = "string32")
	private java.lang.String reviseLineid;

	/**
	 *扣款金额
	 */
	@Column(id = "deduct_money", datatype = "bigdecimal")
	private java.math.BigDecimal deductMoney;

	/**
	 *扣款原因
	 */
	@Column(id = "deduct_reson", datatype = "string256")
	private java.lang.String deductReson;

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
	@Column(id = "active", datatype = "int")
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
	 * 设置
	 */
	public void setReviseLineid(java.lang.String reviseLineid) {
		this.reviseLineid = reviseLineid;
	}

	/**
	 * 获取
	 */
	public java.lang.String getReviseLineid() {
		return reviseLineid;
	}

	/**
	 * 设置扣款金额
	 */
	public void setDeductMoney(java.math.BigDecimal deductMoney) {
		this.deductMoney = deductMoney;
	}

	/**
	 * 获取扣款金额
	 */
	public java.math.BigDecimal getDeductMoney() {
		return deductMoney;
	}

	/**
	 * 设置扣款原因
	 */
	public void setDeductReson(java.lang.String deductReson) {
		this.deductReson = deductReson;
	}

	/**
	 * 获取扣款原因
	 */
	public java.lang.String getDeductReson() {
		return deductReson;
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
