package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 分供方份额表
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_fleetshare", table = "tps_share", ds = "ilsdb")
public class E_fleetshare implements Serializable {
	private static final long serialVersionUID = 1741638594805760L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *分供方id
	 */
	@Column(id = "shipper_id", datatype = "string32")
	private java.lang.String shipperId;

	/**
	 *份额编号
	 */
	@Column(id = "lineno", datatype = "string64")
	private java.lang.String lineno;

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
	 *目的地省份id
	 */
	@Column(id = "dest_province_id", datatype = "int")
	private java.lang.Integer destProvinceId;

	/**
	 *目的地省份
	 */
	@Column(id = "dest_province", datatype = "string32")
	private java.lang.String destProvince;

	/**
	 *份额量
	 */
	@Column(id = "scale", datatype = "bigdecimal")
	private java.math.BigDecimal scale;

	/**
	 *预计发运量
	 */
	@Column(id = "totalqty", datatype = "bigdecimal")
	private java.math.BigDecimal totalqty;

	/**
	 *有效日期
	 */
	@Column(id = "begin_date", datatype = "timestamp")
	private java.sql.Timestamp beginDate;

	/**
	 *失效日期
	 */
	@Column(id = "end_date", datatype = "timestamp")
	private java.sql.Timestamp endDate;

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
	 *1
	 */
	@Column(id = "shipper_name", datatype = "string256")
	private java.lang.String shipperName;

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
	 * 设置分供方id
	 */
	public void setShipperId(java.lang.String shipperId) {
		this.shipperId = shipperId;
	}

	/**
	 * 获取分供方id
	 */
	public java.lang.String getShipperId() {
		return shipperId;
	}

	/**
	 * 设置份额编号
	 */
	public void setLineno(java.lang.String lineno) {
		this.lineno = lineno;
	}

	/**
	 * 获取份额编号
	 */
	public java.lang.String getLineno() {
		return lineno;
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
	 * 设置目的地省份id
	 */
	public void setDestProvinceId(java.lang.Integer destProvinceId) {
		this.destProvinceId = destProvinceId;
	}

	/**
	 * 获取目的地省份id
	 */
	public java.lang.Integer getDestProvinceId() {
		return destProvinceId;
	}

	/**
	 * 设置目的地省份
	 */
	public void setDestProvince(java.lang.String destProvince) {
		this.destProvince = destProvince;
	}

	/**
	 * 获取目的地省份
	 */
	public java.lang.String getDestProvince() {
		return destProvince;
	}

	/**
	 * 设置份额量
	 */
	public void setScale(java.math.BigDecimal scale) {
		this.scale = scale;
	}

	/**
	 * 获取份额量
	 */
	public java.math.BigDecimal getScale() {
		return scale;
	}

	/**
	 * 设置预计发运量
	 */
	public void setTotalqty(java.math.BigDecimal totalqty) {
		this.totalqty = totalqty;
	}

	/**
	 * 获取预计发运量
	 */
	public java.math.BigDecimal getTotalqty() {
		return totalqty;
	}

	/**
	 * 设置有效日期
	 */
	public void setBeginDate(java.sql.Timestamp beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取有效日期
	 */
	public java.sql.Timestamp getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置失效日期
	 */
	public void setEndDate(java.sql.Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取失效日期
	 */
	public java.sql.Timestamp getEndDate() {
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

	/**
	 * 设置1
	 */
	public void setShipperName(java.lang.String shipperName) {
		this.shipperName = shipperName;
	}

	/**
	 * 获取1
	 */
	public java.lang.String getShipperName() {
		return shipperName;
	}
}
