package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity; 
 
/**
 * 分供方信息表
 * @author  
 * @generated
 */
@Entity(id = "entity:com.unlcn.ils.tps.ConsignorInfo", table = "td_shipper", ds = "ilsdb")
public class ConsignorInfo implements Serializable { 
	private static final long serialVersionUID = 1671990135832576L;
	/**
	 *ID
	 */
	@Column(id = "id", type = ColumnType.increment, datatype = "int")
	private java.lang.Integer id;

	/**
	 *客户ID
	 */
	@Column(id = "customer_id", datatype = "bigdecimal")
	private java.math.BigDecimal customerId;

	/**
	 *编号
	 */
	@Column(id = "code", datatype = "string64")
	private java.lang.String code;

	/**
	 *注册地
	 */
	@Column(id = "reg_address", datatype = "string256")
	private java.lang.String regAddress;

	/**
	 *注册资金
	 */
	@Column(id = "capital", datatype = "bigdecimal")
	private java.math.BigDecimal capital;

	/**
	 *等级
	 */
	@Column(id = "level", datatype = "string64")
	private java.lang.String level;

	/**
	 *是否有运输管理规定
	 */
	@Column(id = "has_permit", datatype = "string10")
	private java.lang.String hasPermit;

	/**
	 *名称
	 */
	@Column(id = "name", datatype = "string128")
	private java.lang.String name;

	/**
	 *简称
	 */
	@Column(id = "short_name", datatype = "string64")
	private java.lang.String shortName;

	/**
	 *税务登记号1
	 */
	@Column(id = "tax_no", datatype = "string64")
	private java.lang.String taxNo;

	/**
	 *业务类型
	 */
	@Column(id = "business_type", datatype = "string64")
	private java.lang.String businessType;

	/**
	 *可用车辆数
	 */
	@Column(id = "car_number", datatype = "int")
	private java.lang.Integer carNumber;

	/**
	 *负责人
	 */
	@Column(id = "linkman", datatype = "string64")
	private java.lang.String linkman;

	/**
	 *法人
	 */
	@Column(id = "owner", datatype = "string64")
	private java.lang.String owner;

	/**
	 *手机
	 */
	@Column(id = "phone", datatype = "string64")
	private java.lang.String phone;

	/**
	 *电话
	 */
	@Column(id = "tel", datatype = "string64")
	private java.lang.String tel;

	/**
	 *认证状态
	 */
	@Column(id = "cert_status", datatype = "string64")
	private java.lang.String certStatus;

	/**
	 *认证有效期
	 */
	@Column(id = "cert_valid_time", datatype = "timestamp")
	private java.sql.Timestamp certValidTime;

	/**
	 *能否开票
	 */
	@Column(id = "has_inovice", datatype = "string64")
	private java.lang.String hasInovice;

	/**
	 *备注
	 */
	@Column(id = "desc", datatype = "string256")
	private java.lang.String desc;

	/**
	 *添加人
	 */
	@Column(id = "create_user", datatype = "string64")
	private java.lang.String createUser;

	/**
	 *添加时间
	 */
	@Column(id = "create_time", datatype = "timestamp")
	private java.sql.Timestamp createTime;

	/**
	 *最后修改人
	 */
	@Column(id = "update_user", datatype = "string64")
	private java.lang.String updateUser;

	/**
	 *最后修改时间
	 */
	@Column(id = "update_time", datatype = "timestamp")
	private java.sql.Timestamp updateTime;

	/**
	 * 设置ID
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	/**
	 * 获取ID
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * 设置客户ID
	 */
	public void setCustomerId(java.math.BigDecimal customerId) {
		this.customerId = customerId;
	}

	/**
	 * 获取客户ID
	 */
	public java.math.BigDecimal getCustomerId() {
		return customerId;
	}

	/**
	 * 设置编号
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}

	/**
	 * 获取编号
	 */
	public java.lang.String getCode() {
		return code;
	}

	/**
	 * 设置注册地
	 */
	public void setRegAddress(java.lang.String regAddress) {
		this.regAddress = regAddress;
	}

	/**
	 * 获取注册地
	 */
	public java.lang.String getRegAddress() {
		return regAddress;
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
	 * 设置等级
	 */
	public void setLevel(java.lang.String level) {
		this.level = level;
	}

	/**
	 * 获取等级
	 */
	public java.lang.String getLevel() {
		return level;
	}

	/**
	 * 设置是否有运输管理规定
	 */
	public void setHasPermit(java.lang.String hasPermit) {
		this.hasPermit = hasPermit;
	}

	/**
	 * 获取是否有运输管理规定
	 */
	public java.lang.String getHasPermit() {
		return hasPermit;
	}

	/**
	 * 设置名称
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 获取名称
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * 设置简称
	 */
	public void setShortName(java.lang.String shortName) {
		this.shortName = shortName;
	}

	/**
	 * 获取简称
	 */
	public java.lang.String getShortName() {
		return shortName;
	}

	/**
	 * 设置税务登记号1
	 */
	public void setTaxNo(java.lang.String taxNo) {
		this.taxNo = taxNo;
	}

	/**
	 * 获取税务登记号1
	 */
	public java.lang.String getTaxNo() {
		return taxNo;
	}

	/**
	 * 设置业务类型
	 */
	public void setBusinessType(java.lang.String businessType) {
		this.businessType = businessType;
	}

	/**
	 * 获取业务类型
	 */
	public java.lang.String getBusinessType() {
		return businessType;
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
	 * 设置负责人
	 */
	public void setLinkman(java.lang.String linkman) {
		this.linkman = linkman;
	}

	/**
	 * 获取负责人
	 */
	public java.lang.String getLinkman() {
		return linkman;
	}

	/**
	 * 设置法人
	 */
	public void setOwner(java.lang.String owner) {
		this.owner = owner;
	}

	/**
	 * 获取法人
	 */
	public java.lang.String getOwner() {
		return owner;
	}

	/**
	 * 设置手机
	 */
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	/**
	 * 获取手机
	 */
	public java.lang.String getPhone() {
		return phone;
	}

	/**
	 * 设置电话
	 */
	public void setTel(java.lang.String tel) {
		this.tel = tel;
	}

	/**
	 * 获取电话
	 */
	public java.lang.String getTel() {
		return tel;
	}

	/**
	 * 设置认证状态
	 */
	public void setCertStatus(java.lang.String certStatus) {
		this.certStatus = certStatus;
	}

	/**
	 * 获取认证状态
	 */
	public java.lang.String getCertStatus() {
		return certStatus;
	}

	/**
	 * 设置认证有效期
	 */
	public void setCertValidTime(java.sql.Timestamp certValidTime) {
		this.certValidTime = certValidTime;
	}

	/**
	 * 获取认证有效期
	 */
	public java.sql.Timestamp getCertValidTime() {
		return certValidTime;
	}

	/**
	 * 设置能否开票
	 */
	public void setHasInovice(java.lang.String hasInovice) {
		this.hasInovice = hasInovice;
	}

	/**
	 * 获取能否开票
	 */
	public java.lang.String getHasInovice() {
		return hasInovice;
	}

	/**
	 * 设置备注
	 */
	public void setDesc(java.lang.String desc) {
		this.desc = desc;
	}

	/**
	 * 获取备注
	 */
	public java.lang.String getDesc() {
		return desc;
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
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取添加时间
	 */
	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * 设置最后修改人
	 */
	public void setUpdateUser(java.lang.String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * 获取最后修改人
	 */
	public java.lang.String getUpdateUser() {
		return updateUser;
	}

	/**
	 * 设置最后修改时间
	 */
	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取最后修改时间
	 */
	public java.sql.Timestamp getUpdateTime() {
		return updateTime;
	}
}
