package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 分供方异常明细表
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_complain_charge", table = "td_complain_charge", ds = "ilsdb")
public class E_complain_charge implements Serializable {
	private static final long serialVersionUID = 1757056816070656L;
	/**
	 *
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *客诉异常ID
	 */
	@Column(id = "complain_id", datatype = "string64")
	private java.lang.String complainId;

	/**
	 *客户订单号
	 */
	@Column(id = "customer_order", datatype = "string64")
	private java.lang.String customerOrder;

	/**
	 *客诉异常类型
	 */
	@Column(id = "category", datatype = "string64")
	private java.lang.String category;

	/**
	 *分类ID
	 */
	@Column(id = "category_id", datatype = "string64")
	private java.lang.String categoryId;

	/**
	 *分类名称
	 */
	@Column(id = "category_name", datatype = "string64")
	private java.lang.String categoryName;

	/**
	 *责任人类型，如分供方、货主等
	 */
	@Column(id = "charger_type", datatype = "string64")
	private java.lang.String chargerType;

	/**
	 *责任人
	 */
	@Column(id = "charger_id", datatype = "string64")
	private java.lang.String chargerId;

	/**
	 *责任人名称
	 */
	@Column(id = "charger_name", datatype = "string64")
	private java.lang.String chargerName;

	/**
	 *责任百分比
	 */
	@Column(id = "percent", datatype = "string64")
	private java.lang.String percent;

	/**
	 *备注
	 */
	@Column(id = "charge_memo", datatype = "string256")
	private java.lang.String chargeMemo;

	/**
	 *定责时间
	 */
	@Column(id = "charge_time", datatype = "timestamp")
	private java.sql.Timestamp chargeTime;

	/**
	 *添加人
	 */
	@Column(id = "create_user", datatype = "string64")
	private java.lang.String createUser;

	/**
	 *添加时间
	 */
	@Column(id = "createtime", datatype = "timestamp")
	private java.sql.Timestamp createtime;

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
	 * 设置客诉异常ID
	 */
	public void setComplainId(java.lang.String complainId) {
		this.complainId = complainId;
	}

	/**
	 * 获取客诉异常ID
	 */
	public java.lang.String getComplainId() {
		return complainId;
	}

	/**
	 * 设置客户订单号
	 */
	public void setCustomerOrder(java.lang.String customerOrder) {
		this.customerOrder = customerOrder;
	}

	/**
	 * 获取客户订单号
	 */
	public java.lang.String getCustomerOrder() {
		return customerOrder;
	}

	/**
	 * 设置客诉异常类型
	 */
	public void setCategory(java.lang.String category) {
		this.category = category;
	}

	/**
	 * 获取客诉异常类型
	 */
	public java.lang.String getCategory() {
		return category;
	}

	/**
	 * 设置分类ID
	 */
	public void setCategoryId(java.lang.String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 获取分类ID
	 */
	public java.lang.String getCategoryId() {
		return categoryId;
	}

	/**
	 * 设置分类名称
	 */
	public void setCategoryName(java.lang.String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 获取分类名称
	 */
	public java.lang.String getCategoryName() {
		return categoryName;
	}

	/**
	 * 设置责任人类型，如分供方、货主等
	 */
	public void setChargerType(java.lang.String chargerType) {
		this.chargerType = chargerType;
	}

	/**
	 * 获取责任人类型，如分供方、货主等
	 */
	public java.lang.String getChargerType() {
		return chargerType;
	}

	/**
	 * 设置责任人
	 */
	public void setChargerId(java.lang.String chargerId) {
		this.chargerId = chargerId;
	}

	/**
	 * 获取责任人
	 */
	public java.lang.String getChargerId() {
		return chargerId;
	}

	/**
	 * 设置责任人名称
	 */
	public void setChargerName(java.lang.String chargerName) {
		this.chargerName = chargerName;
	}

	/**
	 * 获取责任人名称
	 */
	public java.lang.String getChargerName() {
		return chargerName;
	}

	/**
	 * 设置责任百分比
	 */
	public void setPercent(java.lang.String percent) {
		this.percent = percent;
	}

	/**
	 * 获取责任百分比
	 */
	public java.lang.String getPercent() {
		return percent;
	}

	/**
	 * 设置备注
	 */
	public void setChargeMemo(java.lang.String chargeMemo) {
		this.chargeMemo = chargeMemo;
	}

	/**
	 * 获取备注
	 */
	public java.lang.String getChargeMemo() {
		return chargeMemo;
	}

	/**
	 * 设置定责时间
	 */
	public void setChargeTime(java.sql.Timestamp chargeTime) {
		this.chargeTime = chargeTime;
	}

	/**
	 * 获取定责时间
	 */
	public java.sql.Timestamp getChargeTime() {
		return chargeTime;
	}

	/**
	 * 设置添加人
	 */
	public void setCreateUser(java.lang.String createUser) {
		this.createUser = createUser;
	}

	/**
	 * 获取添加人
	 */
	public java.lang.String getCreateUser() {
		return createUser;
	}

	/**
	 * 设置添加时间
	 */
	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}

	/**
	 * 获取添加时间
	 */
	public java.sql.Timestamp getCreatetime() {
		return createtime;
	}
}
