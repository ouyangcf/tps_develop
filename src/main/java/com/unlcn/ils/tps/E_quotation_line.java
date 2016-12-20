package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 报价单明细表
 * @author  
 * @generated 
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_quotation_line", table = "tps_quotation_line", ds = "ilsdb")
public class E_quotation_line implements Serializable {
	private static final long serialVersionUID = 1744296101888000L;
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
	 *客户id
	 */
	@Column(id = "customer_id", datatype = "string32")
	private java.lang.String customerId;

	/**
	 *起运城市id
	 */
	@Column(id = "start_city_id", datatype = "int")
	private java.lang.Integer startCityId;

	/**
	 *起运城市
	 */
	@Column(id = "start_city", datatype = "string32")
	private java.lang.String startCity;

	/**
	 *目的城市id
	 */
	@Column(id = "dest_city_id", datatype = "int")
	private java.lang.Integer destCityId;

	/**
	 *目的城市
	 */
	@Column(id = "dest_city", datatype = "string32")
	private java.lang.String destCity;

	/**
	 *货物型号
	 */
	@Column(id = "item_id", datatype = "string32")
	private java.lang.String itemId;

	/**
	 *数量
	 */
	@Column(id = "qty", datatype = "bigdecimal")
	private java.math.BigDecimal qty;

	/**
	 *标准公里数
	 */
	@Column(id = "standard_kilometer", datatype = "bigdecimal")
	private java.math.BigDecimal standardKilometer;

	/**
	 *标准单价
	 */
	@Column(id = "standard_price", datatype = "bigdecimal")
	private java.math.BigDecimal standardPrice;

	/**
	 *报价单价
	 */
	@Column(id = "quotation_price", datatype = "bigdecimal")
	private java.math.BigDecimal quotationPrice;

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
	 *取消说明1
	 */
	@Column(id = "active_memo", datatype = "string256")
	private java.lang.String activeMemo;

	/**
	 *结算数量
	 */
	@Column(id = "account_qty", datatype = "bigdecimal")
	private java.math.BigDecimal accountQty;

	/**
	 *溢价比
	 */
	@Column(id = "overflow", datatype = "bigdecimal")
	private java.math.BigDecimal overflow;

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
	 * 设置客户id
	 */
	public void setCustomerId(java.lang.String customerId) {
		this.customerId = customerId;
	}

	/**
	 * 获取客户id
	 */
	public java.lang.String getCustomerId() {
		return customerId;
	}

	/**
	 * 设置起运城市id
	 */
	public void setStartCityId(java.lang.Integer startCityId) {
		this.startCityId = startCityId;
	}

	/**
	 * 获取起运城市id
	 */
	public java.lang.Integer getStartCityId() {
		return startCityId;
	}

	/**
	 * 设置起运城市
	 */
	public void setStartCity(java.lang.String startCity) {
		this.startCity = startCity;
	}

	/**
	 * 获取起运城市
	 */
	public java.lang.String getStartCity() {
		return startCity;
	}

	/**
	 * 设置目的城市id
	 */
	public void setDestCityId(java.lang.Integer destCityId) {
		this.destCityId = destCityId;
	}

	/**
	 * 获取目的城市id
	 */
	public java.lang.Integer getDestCityId() {
		return destCityId;
	}

	/**
	 * 设置目的城市
	 */
	public void setDestCity(java.lang.String destCity) {
		this.destCity = destCity;
	}

	/**
	 * 获取目的城市
	 */
	public java.lang.String getDestCity() {
		return destCity;
	}

	/**
	 * 设置货物型号
	 */
	public void setItemId(java.lang.String itemId) {
		this.itemId = itemId;
	}

	/**
	 * 获取货物型号
	 */
	public java.lang.String getItemId() {
		return itemId;
	}

	/**
	 * 设置数量
	 */
	public void setQty(java.math.BigDecimal qty) {
		this.qty = qty;
	}

	/**
	 * 获取数量
	 */
	public java.math.BigDecimal getQty() {
		return qty;
	}

	/**
	 * 设置标准公里数
	 */
	public void setStandardKilometer(java.math.BigDecimal standardKilometer) {
		this.standardKilometer = standardKilometer;
	}

	/**
	 * 获取标准公里数
	 */
	public java.math.BigDecimal getStandardKilometer() {
		return standardKilometer;
	}

	/**
	 * 设置标准单价
	 */
	public void setStandardPrice(java.math.BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}

	/**
	 * 获取标准单价
	 */
	public java.math.BigDecimal getStandardPrice() {
		return standardPrice;
	}

	/**
	 * 设置报价单价
	 */
	public void setQuotationPrice(java.math.BigDecimal quotationPrice) {
		this.quotationPrice = quotationPrice;
	}

	/**
	 * 获取报价单价
	 */
	public java.math.BigDecimal getQuotationPrice() {
		return quotationPrice;
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
	 * 设置取消说明1
	 */
	public void setActiveMemo(java.lang.String activeMemo) {
		this.activeMemo = activeMemo;
	}

	/**
	 * 获取取消说明1
	 */
	public java.lang.String getActiveMemo() {
		return activeMemo;
	}

	/**
	 * 设置结算数量
	 */
	public void setAccountQty(java.math.BigDecimal accountQty) {
		this.accountQty = accountQty;
	}

	/**
	 * 获取结算数量
	 */
	public java.math.BigDecimal getAccountQty() {
		return accountQty;
	}

	/**
	 * 设置溢价比
	 */
	public void setOverflow(java.math.BigDecimal overflow) {
		this.overflow = overflow;
	}

	/**
	 * 获取溢价比
	 */
	public java.math.BigDecimal getOverflow() {
		return overflow;
	}
}
