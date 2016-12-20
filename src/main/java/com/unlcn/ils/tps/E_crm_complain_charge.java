package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * crm分供方异常明细信息关联情况表
 * @author   
 * @generated 
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_crm_complain_charge", table = "tps_crm_complain_charge", ds = "ilsdb")
public class E_crm_complain_charge implements Serializable {
	private static final long serialVersionUID = 1757055260803072L;
	/**
	 *
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *客诉主键序号
	 */
	@Column(id = "complain_charge_lineid", datatype = "string32")
	private java.lang.String complainChargeLineid;

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
	@Column(id = "charge_time", datatype = "string64")
	private java.lang.String chargeTime;

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
	 *关联标志，0：未关联，1：已关联
	 */
	@Column(id = "realate_flag", datatype = "int")
	private java.lang.Integer realateFlag;

	/**
	 *关联考核单主键
	 */
	@Column(id = "realate_lineid", datatype = "string32")
	private java.lang.String realateLineid;

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
	@Column(id = "active_user", datatype = "string64")
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
	 *类型关联标志,0:未关联，1:已关联
	 */
	@Column(id = "complain_type_relate_flag", datatype = "tinyint")
	private java.lang.Integer complainTypeRelateFlag;

	/**
	 *关联类型主键序号
	 */
	@Column(id = "complain_type_realate_lineid", datatype = "int")
	private java.lang.Integer complainTypeRealateLineid;

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
	 * 设置客诉主键序号
	 */
	public void setComplainChargeLineid(java.lang.String complainChargeLineid) {
		this.complainChargeLineid = complainChargeLineid;
	}

	/**
	 * 获取客诉主键序号
	 */
	public java.lang.String getComplainChargeLineid() {
		return complainChargeLineid;
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
	public void setChargeTime(java.lang.String chargeTime) {
		this.chargeTime = chargeTime;
	}

	/**
	 * 获取定责时间
	 */
	public java.lang.String getChargeTime() {
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

	/**
	 * 设置关联标志，0：未关联，1：已关联
	 */
	public void setRealateFlag(java.lang.Integer realateFlag) {
		this.realateFlag = realateFlag;
	}

	/**
	 * 获取关联标志，0：未关联，1：已关联
	 */
	public java.lang.Integer getRealateFlag() {
		return realateFlag;
	}

	/**
	 * 设置关联考核单主键
	 */
	public void setRealateLineid(java.lang.String realateLineid) {
		this.realateLineid = realateLineid;
	}

	/**
	 * 获取关联考核单主键
	 */
	public java.lang.String getRealateLineid() {
		return realateLineid;
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

	/**
	 * 设置类型关联标志,0:未关联，1:已关联
	 */
	public void setComplainTypeRelateFlag(
			java.lang.Integer complainTypeRelateFlag) {
		this.complainTypeRelateFlag = complainTypeRelateFlag;
	}

	/**
	 * 获取类型关联标志,0:未关联，1:已关联
	 */
	public java.lang.Integer getComplainTypeRelateFlag() {
		return complainTypeRelateFlag;
	}

	/**
	 * 设置关联类型主键序号
	 */
	public void setComplainTypeRealateLineid(
			java.lang.Integer complainTypeRealateLineid) {
		this.complainTypeRealateLineid = complainTypeRealateLineid;
	}

	/**
	 * 获取关联类型主键序号
	 */
	public java.lang.Integer getComplainTypeRealateLineid() {
		return complainTypeRealateLineid;
	}
}
