package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 客诉异常明细暂存
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_temp_complain_charge", table = "td_temp_complain_charge", ds = "ilsdb")
public class E_temp_complain_charge implements Serializable {
	private static final long serialVersionUID = 1803906301460480L;
	/**
	 *
	 */
	@Column(id = "tempid", type = ColumnType.increment, datatype = "int")
	private java.lang.Integer tempid;

	/**
	 *主键
	 */
	@Column(id = "id", datatype = "string128")
	private java.lang.String id;

	/**
	 *客诉异常ID
	 */
	@Column(id = "complainid", datatype = "string128")
	private java.lang.String complainid;

	/**
	 *客户订单号
	 */
	@Column(id = "customerorder", datatype = "string128")
	private java.lang.String customerorder;

	/**
	 *客诉异常类型
	 */
	@Column(id = "category", datatype = "string128")
	private java.lang.String category;

	/**
	 *分类ID
	 */
	@Column(id = "categoryid", datatype = "string128")
	private java.lang.String categoryid;

	/**
	 *分类名称
	 */
	@Column(id = "categoryname", datatype = "string128")
	private java.lang.String categoryname;

	/**
	 *责任人类型
	 */
	@Column(id = "chargertype", datatype = "string128")
	private java.lang.String chargertype;

	/**
	 *责任人ID
	 */
	@Column(id = "chargerid", datatype = "string128")
	private java.lang.String chargerid;

	/**
	 *责任人名称
	 */
	@Column(id = "chargername", datatype = "string128")
	private java.lang.String chargername;

	/**
	 *责任百分比
	 */
	@Column(id = "percent", datatype = "string128")
	private java.lang.String percent;

	/**
	 *备注
	 */
	@Column(id = "memo", datatype = "string256")
	private java.lang.String memo;

	/**
	 *定责时间
	 */
	@Column(id = "chargetime", datatype = "timestamp")
	private java.sql.Timestamp chargetime;

	/**
	 *添加人
	 */
	@Column(id = "createuser", datatype = "string128")
	private java.lang.String createuser;

	/**
	 *添加时间
	 */
	@Column(id = "createtime", datatype = "timestamp")
	private java.sql.Timestamp createtime;

	/**
	 * 设置
	 */
	public void setTempid(java.lang.Integer tempid) {
		this.tempid = tempid;
	}

	/**
	 * 获取
	 */
	public java.lang.Integer getTempid() {
		return tempid;
	}

	/**
	 * 设置主键
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取主键
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置客诉异常ID
	 */
	public void setComplainid(java.lang.String complainid) {
		this.complainid = complainid;
	}

	/**
	 * 获取客诉异常ID
	 */
	public java.lang.String getComplainid() {
		return complainid;
	}

	/**
	 * 设置客户订单号
	 */
	public void setCustomerorder(java.lang.String customerorder) {
		this.customerorder = customerorder;
	}

	/**
	 * 获取客户订单号
	 */
	public java.lang.String getCustomerorder() {
		return customerorder;
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
	public void setCategoryid(java.lang.String categoryid) {
		this.categoryid = categoryid;
	}

	/**
	 * 获取分类ID
	 */
	public java.lang.String getCategoryid() {
		return categoryid;
	}

	/**
	 * 设置分类名称
	 */
	public void setCategoryname(java.lang.String categoryname) {
		this.categoryname = categoryname;
	}

	/**
	 * 获取分类名称
	 */
	public java.lang.String getCategoryname() {
		return categoryname;
	}

	/**
	 * 设置责任人类型
	 */
	public void setChargertype(java.lang.String chargertype) {
		this.chargertype = chargertype;
	}

	/**
	 * 获取责任人类型
	 */
	public java.lang.String getChargertype() {
		return chargertype;
	}

	/**
	 * 设置责任人ID
	 */
	public void setChargerid(java.lang.String chargerid) {
		this.chargerid = chargerid;
	}

	/**
	 * 获取责任人ID
	 */
	public java.lang.String getChargerid() {
		return chargerid;
	}

	/**
	 * 设置责任人名称
	 */
	public void setChargername(java.lang.String chargername) {
		this.chargername = chargername;
	}

	/**
	 * 获取责任人名称
	 */
	public java.lang.String getChargername() {
		return chargername;
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
	public void setMemo(java.lang.String memo) {
		this.memo = memo;
	}

	/**
	 * 获取备注
	 */
	public java.lang.String getMemo() {
		return memo;
	}

	/**
	 * 设置定责时间
	 */
	public void setChargetime(java.sql.Timestamp chargetime) {
		this.chargetime = chargetime;
	}

	/**
	 * 获取定责时间
	 */
	public java.sql.Timestamp getChargetime() {
		return chargetime;
	}

	/**
	 * 设置添加人
	 */
	public void setCreateuser(java.lang.String createuser) {
		this.createuser = createuser;
	}

	/**
	 * 获取添加人
	 */
	public java.lang.String getCreateuser() {
		return createuser;
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
