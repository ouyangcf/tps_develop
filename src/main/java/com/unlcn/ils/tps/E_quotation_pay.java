package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 支付方式表
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_quotation_pay", table = "tps_quotation_pay", ds = "ilsdb")
public class E_quotation_pay implements Serializable {
	private static final long serialVersionUID = 1732888503287808L;
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
	 *支付方式序号
	 */
	@Column(id = "payment_id", association = true)
	private E_payment_method paymentId;

	/**
	 *支付方式名称
	 */
	@Column(id = "payment_name", datatype = "string512")
	private java.lang.String paymentName;

	/**
	 *创建时间
	 */
	@Column(id = "create_time", datatype = "timestamp")
	private java.sql.Timestamp createTime;

	/**
	 *创建人1
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
	 *支付比例
	 */
	@Column(id = "scale", datatype = "bigdecimal")
	private java.math.BigDecimal scale;

	/**
	 *支付卡号
	 */
	@Column(id = "cardno", datatype = "string128")
	private java.lang.String cardno;

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
	 * 设置支付方式序号
	 */
	public void setPaymentId(E_payment_method paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * 获取支付方式序号
	 */
	public E_payment_method getPaymentId() {
		return paymentId;
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
	 * 设置创建人1
	 */
	public void setCreateUser(java.lang.String createUser) {
		this.createUser = createUser;
	}

	/**
	 * 获取创建人1
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

	/**
	 * 设置支付比例
	 */
	public void setScale(java.math.BigDecimal scale) {
		this.scale = scale;
	}

	/**
	 * 获取支付比例
	 */
	public java.math.BigDecimal getScale() {
		return scale;
	}

	/**
	 * 设置支付卡号
	 */
	public void setCardno(java.lang.String cardno) {
		this.cardno = cardno;
	}

	/**
	 * 获取支付卡号
	 */
	public java.lang.String getCardno() {
		return cardno;
	}
}
