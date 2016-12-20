package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 个人申请表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_join_driver", table = "tps_join_driver", ds = "ilsdb")
public class E_join_driver implements Serializable {
	private static final long serialVersionUID = 1681792392036352L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *司机姓名
	 */
	@Column(id = "driver_name", datatype = "string32")
	private java.lang.String driverName;

	/**
	 *车牌号
	 */
	@Column(id = "licenseno", datatype = "string20")
	private java.lang.String licenseno;

	/**
	 *行驶证号
	 */
	@Column(id = "vehicle_licenseno", datatype = "string32")
	private java.lang.String vehicleLicenseno;

	/**
	 *身份证号码
	 */
	@Column(id = "cardno", datatype = "string20")
	private java.lang.String cardno;

	/**
	 *手机号码
	 */
	@Column(id = "mobileno", datatype = "string20")
	private java.lang.String mobileno;

	/**
	 *车辆注册省份id
	 */
	@Column(id = "province_id", datatype = "int")
	private java.lang.Integer provinceId;

	/**
	 *车辆注册省份
	 */
	@Column(id = "province", datatype = "string32")
	private java.lang.String province;

	/**
	 *车辆注册城市id
	 */
	@Column(id = "city_id", datatype = "int")
	private java.lang.Integer cityId;

	/**
	 *车辆注册城市
	 */
	@Column(id = "city", datatype = "string32")
	private java.lang.String city;

	/**
	 *可用车辆数
	 */
	@Column(id = "car_number", datatype = "int")
	private java.lang.Integer carNumber;

	/**
	 *初审状态
	 */
	@Column(id = "check_flag", datatype = "int")
	private java.lang.Integer checkFlag;

	/**
	 *创建时间
	 */
	@Column(id = "create_time", datatype = "timestamp")
	private java.sql.Timestamp createTime;

	/**
	 *创建人
	 */
	@Column(id = "create_user", datatype = "string64")
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
	 *分供方id
	 */
	@Column(id = "shipper_id", datatype = "string32")
	private java.lang.String shipperId;

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
	 * 设置司机姓名
	 */
	public void setDriverName(java.lang.String driverName) {
		this.driverName = driverName;
	}

	/**
	 * 获取司机姓名
	 */
	public java.lang.String getDriverName() {
		return driverName;
	}

	/**
	 * 设置车牌号
	 */
	public void setLicenseno(java.lang.String licenseno) {
		this.licenseno = licenseno;
	}

	/**
	 * 获取车牌号
	 */
	public java.lang.String getLicenseno() {
		return licenseno;
	}

	/**
	 * 设置行驶证号
	 */
	public void setVehicleLicenseno(java.lang.String vehicleLicenseno) {
		this.vehicleLicenseno = vehicleLicenseno;
	}

	/**
	 * 获取行驶证号
	 */
	public java.lang.String getVehicleLicenseno() {
		return vehicleLicenseno;
	}

	/**
	 * 设置身份证号码
	 */
	public void setCardno(java.lang.String cardno) {
		this.cardno = cardno;
	}

	/**
	 * 获取身份证号码
	 */
	public java.lang.String getCardno() {
		return cardno;
	}

	/**
	 * 设置手机号码
	 */
	public void setMobileno(java.lang.String mobileno) {
		this.mobileno = mobileno;
	}

	/**
	 * 获取手机号码
	 */
	public java.lang.String getMobileno() {
		return mobileno;
	}

	/**
	 * 设置车辆注册省份id
	 */
	public void setProvinceId(java.lang.Integer provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * 获取车辆注册省份id
	 */
	public java.lang.Integer getProvinceId() {
		return provinceId;
	}

	/**
	 * 设置车辆注册省份
	 */
	public void setProvince(java.lang.String province) {
		this.province = province;
	}

	/**
	 * 获取车辆注册省份
	 */
	public java.lang.String getProvince() {
		return province;
	}

	/**
	 * 设置车辆注册城市id
	 */
	public void setCityId(java.lang.Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * 获取车辆注册城市id
	 */
	public java.lang.Integer getCityId() {
		return cityId;
	}

	/**
	 * 设置车辆注册城市
	 */
	public void setCity(java.lang.String city) {
		this.city = city;
	}

	/**
	 * 获取车辆注册城市
	 */
	public java.lang.String getCity() {
		return city;
	}

	/**
	 * 设置可用车辆数
	 */
	public void setCarNumber(java.lang.Integer carNumber) {
		this.carNumber = carNumber;
	}

	/**
	 * 获取可用车辆数
	 */
	public java.lang.Integer getCarNumber() {
		return carNumber;
	}

	/**
	 * 设置初审状态
	 */
	public void setCheckFlag(java.lang.Integer checkFlag) {
		this.checkFlag = checkFlag;
	}

	/**
	 * 获取初审状态
	 */
	public java.lang.Integer getCheckFlag() {
		return checkFlag;
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
}
