package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 支付方式基础表
 * @author   
 * @generated
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_payment_method", table = "tps_payment_method", ds = "ilsdb")
public class E_payment_method implements Serializable {
	private static final long serialVersionUID = 1732887925243904L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.increment, datatype = "int")
	private java.lang.Integer lineid;

	/**
	 *支付方式编号
	 */
	@Column(id = "payment_no", datatype = "string64")
	private java.lang.String paymentNo;

	/**
	 *支付方式名称
	 */
	@Column(id = "payment_name", datatype = "string512")
	private java.lang.String paymentName;

	/**
	 *支付方式类型(现金/支票/卡)
	 */
	@Column(id = "payment_type", datatype = "int")
	private java.lang.Integer paymentType;

	/**
	 *创建时间
	 */
	@Column(id = "create_time", datatype = "timestamp")
	private java.sql.Timestamp createTime;

	/**
	 *
	 */
	@Column(id = "create_user", datatype = "string64")
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
	 * 设置支付方式编号
	 */
	public void setPaymentNo(java.lang.String paymentNo) {
		this.paymentNo = paymentNo;
	}

	/**
	 * 获取支付方式编号
	 */
	public java.lang.String getPaymentNo() {
		return paymentNo;
	}

	/**
	 * 设置支付方式名称
	 */
	public void setPaymentName(java.lang.String paymentName) {
		this.paymentName = paymentName;
	}

	/**
	 * 获取支付方式名称
	 */
	public java.lang.String getPaymentName() {
		return paymentName;
	}

	/**
	 * 设置支付方式类型(现金/支票/卡)
	 */
	public void setPaymentType(java.lang.Integer paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * 获取支付方式类型(现金/支票/卡)
	 */
	public java.lang.Integer getPaymentType() {
		return paymentType;
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
