package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 合作申请表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_join_company", table = "tps_join_company", ds = "ilsdb")
public class E_join_company implements Serializable {
	private static final long serialVersionUID = 1681778676334592L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *分供方全称
	 */
	@Column(id = "company_name", datatype = "string128")
	private java.lang.String companyName;

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
	 *法人代表
	 */
	@Column(id = "legal_user", datatype = "string32")
	private java.lang.String legalUser;

	/**
	 *总负责人
	 */
	@Column(id = "main_contract", datatype = "string32")
	private java.lang.String mainContract;

	/**
	 *税务号
	 */
	@Column(id = "tax_no", datatype = "string32")
	private java.lang.String taxNo;

	/**
	 *注册资金
	 */
	@Column(id = "capital", datatype = "bigdecimal")
	private java.math.BigDecimal capital;

	/**
	 *可用车辆数
	 */
	@Column(id = "car_number", datatype = "int")
	private java.lang.Integer carNumber;

	/**
	 *资产状况
	 */
	@Column(id = "assets", datatype = "string512")
	private java.lang.String assets;

	/**
	 *营业执照编号
	 */
	@Column(id = "id_number", datatype = "string64")
	private java.lang.String idNumber;

	/**
	 *联系电话
	 */
	@Column(id = "contract", datatype = "string20")
	private java.lang.String contract;

	/**
	 *手机号码
	 */
	@Column(id = "mobileno", datatype = "string20")
	private java.lang.String mobileno;

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
	 *道路经营许可证
	 */
	@Column(id = "road_licence", datatype = "string64")
	private java.lang.String roadLicence;

	/**
	 *联系人
	 */
	@Column(id = "contract_name", datatype = "string20")
	private java.lang.String contractName;

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
	 * 设置分供方全称
	 */
	public void setCompanyName(java.lang.String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 获取分供方全称
	 */
	public java.lang.String getCompanyName() {
		return companyName;
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
	 * 设置法人代表
	 */
	public void setLegalUser(java.lang.String legalUser) {
		this.legalUser = legalUser;
	}

	/**
	 * 获取法人代表
	 */
	public java.lang.String getLegalUser() {
		return legalUser;
	}

	/**
	 * 设置总负责人
	 */
	public void setMainContract(java.lang.String mainContract) {
		this.mainContract = mainContract;
	}

	/**
	 * 获取总负责人
	 */
	public java.lang.String getMainContract() {
		return mainContract;
	}

	/**
	 * 设置税务号
	 */
	public void setTaxNo(java.lang.String taxNo) {
		this.taxNo = taxNo;
	}

	/**
	 * 获取税务号
	 */
	public java.lang.String getTaxNo() {
		return taxNo;
	}

	/**
	 * 设置注册资金
	 */
	public void setCapital(java.math.BigDecimal capital) {
		this.capital = capital;
	}

	/**
	 * 获取注册资金
	 */
	public java.math.BigDecimal getCapital() {
		return capital;
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
	 * 设置资产状况
	 */
	public void setAssets(java.lang.String assets) {
		this.assets = assets;
	}

	/**
	 * 获取资产状况
	 */
	public java.lang.String getAssets() {
		return assets;
	}

	/**
	 * 设置营业执照编号
	 */
	public void setIdNumber(java.lang.String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * 获取营业执照编号
	 */
	public java.lang.String getIdNumber() {
		return idNumber;
	}

	/**
	 * 设置联系电话
	 */
	public void setContract(java.lang.String contract) {
		this.contract = contract;
	}

	/**
	 * 获取联系电话
	 */
	public java.lang.String getContract() {
		return contract;
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

	/**
	 * 设置道路经营许可证
	 */
	public void setRoadLicence(java.lang.String roadLicence) {
		this.roadLicence = roadLicence;
	}

	/**
	 * 获取道路经营许可证
	 */
	public java.lang.String getRoadLicence() {
		return roadLicence;
	}

	/**
	 * 设置联系人
	 */
	public void setContractName(java.lang.String contractName) {
		this.contractName = contractName;
	}

	/**
	 * 获取联系人
	 */
	public java.lang.String getContractName() {
		return contractName;
	}
}
