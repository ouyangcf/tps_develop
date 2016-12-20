package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 考核单表头
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_checkhead", table = "tps_check_head", ds = "ilsdb")
public class E_checkhead implements Serializable { 
	private static final long serialVersionUID = 1671822738653184L;
	/**
	 *
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *
	 */
	@Column(id = "shipper_lineid", datatype = "string32")
	private java.lang.String shipperLineid;

	/**
	 *分供方名称
	 */
	@Column(id = "shipper_name", datatype = "string128")
	private java.lang.String shipperName;

	/**
	 *考核年月
	 */
	@Column(id = "countmonth", datatype = "string10")
	private java.lang.String countmonth;

	/**
	 *扣款金额
	 */
	@Column(id = "fact_submoney", datatype = "bigdecimal")
	private java.math.BigDecimal factSubmoney;

	/**
	 *申诉状态
	 */
	@Column(id = "argue_status", datatype = "int")
	private java.lang.Integer argueStatus;

	/**
	 *标准扣分
	 */
	@Column(id = "standard_value", datatype = "int")
	private java.lang.Integer standardValue;

	/**
	 *本月扣分
	 */
	@Column(id = "standard_subvalue", datatype = "int")
	private java.lang.Integer standardSubvalue;

	/**
	 *申诉截止时间
	 */
	@Column(id = "argue_time", datatype = "timestamp")
	private java.sql.Timestamp argueTime;

	/**
	 *标准考核金额
	 */
	@Column(id = "standard_submoney", datatype = "bigdecimal")
	private java.math.BigDecimal standardSubmoney;

	/**
	 *汇总考核金额
	 */
	@Column(id = "check_money", datatype = "bigdecimal")
	private java.math.BigDecimal checkMoney;

	/**
	 *汇总减免金额
	 */
	@Column(id = "sub_money", datatype = "bigdecimal")
	private java.math.BigDecimal subMoney;

	/**
	 *汇总扣款金额
	 */
	@Column(id = "deduct_money", datatype = "bigdecimal")
	private java.math.BigDecimal deductMoney;

	/**
	 *汇总冻结金额
	 */
	@Column(id = "fronzen_money", datatype = "bigdecimal")
	private java.math.BigDecimal fronzenMoney;

	/**
	 *执行汇总状态
	 */
	@Column(id = "collect_status", datatype = "int")
	private java.lang.Integer collectStatus;

	/**
	 *考核单状态
	 */
	@Column(id = "check_status", datatype = "int")
	private java.lang.Integer checkStatus;

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
	 * 设置
	 */
	public void setShipperLineid(java.lang.String shipperLineid) {
		this.shipperLineid = shipperLineid;
	}

	/**
	 * 获取
	 */
	public java.lang.String getShipperLineid() {
		return shipperLineid;
	}

	/**
	 * 设置分供方名称
	 */
	public void setShipperName(java.lang.String shipperName) {
		this.shipperName = shipperName;
	}

	/**
	 * 获取分供方名称
	 */
	public java.lang.String getShipperName() {
		return shipperName;
	}

	/**
	 * 设置考核年月
	 */
	public void setCountmonth(java.lang.String countmonth) {
		this.countmonth = countmonth;
	}

	/**
	 * 获取考核年月
	 */
	public java.lang.String getCountmonth() {
		return countmonth;
	}

	/**
	 * 设置扣款金额
	 */
	public void setFactSubmoney(java.math.BigDecimal factSubmoney) {
		this.factSubmoney = factSubmoney;
	}

	/**
	 * 获取扣款金额
	 */
	public java.math.BigDecimal getFactSubmoney() {
		return factSubmoney;
	}

	/**
	 * 设置申诉状态
	 */
	public void setArgueStatus(java.lang.Integer argueStatus) {
		this.argueStatus = argueStatus;
	}

	/**
	 * 获取申诉状态
	 */
	public java.lang.Integer getArgueStatus() {
		return argueStatus;
	}

	/**
	 * 设置标准扣分
	 */
	public void setStandardValue(java.lang.Integer standardValue) {
		this.standardValue = standardValue;
	}

	/**
	 * 获取标准扣分
	 */
	public java.lang.Integer getStandardValue() {
		return standardValue;
	}

	/**
	 * 设置本月扣分
	 */
	public void setStandardSubvalue(java.lang.Integer standardSubvalue) {
		this.standardSubvalue = standardSubvalue;
	}

	/**
	 * 获取本月扣分
	 */
	public java.lang.Integer getStandardSubvalue() {
		return standardSubvalue;
	}

	/**
	 * 设置申诉截止时间
	 */
	public void setArgueTime(java.sql.Timestamp argueTime) {
		this.argueTime = argueTime;
	}

	/**
	 * 获取申诉截止时间
	 */
	public java.sql.Timestamp getArgueTime() {
		return argueTime;
	}

	/**
	 * 设置标准考核金额
	 */
	public void setStandardSubmoney(java.math.BigDecimal standardSubmoney) {
		this.standardSubmoney = standardSubmoney;
	}

	/**
	 * 获取标准考核金额
	 */
	public java.math.BigDecimal getStandardSubmoney() {
		return standardSubmoney;
	}

	/**
	 * 设置汇总考核金额
	 */
	public void setCheckMoney(java.math.BigDecimal checkMoney) {
		this.checkMoney = checkMoney;
	}

	/**
	 * 获取汇总考核金额
	 */
	public java.math.BigDecimal getCheckMoney() {
		return checkMoney;
	}

	/**
	 * 设置汇总减免金额
	 */
	public void setSubMoney(java.math.BigDecimal subMoney) {
		this.subMoney = subMoney;
	}

	/**
	 * 获取汇总减免金额
	 */
	public java.math.BigDecimal getSubMoney() {
		return subMoney;
	}

	/**
	 * 设置汇总扣款金额
	 */
	public void setDeductMoney(java.math.BigDecimal deductMoney) {
		this.deductMoney = deductMoney;
	}

	/**
	 * 获取汇总扣款金额
	 */
	public java.math.BigDecimal getDeductMoney() {
		return deductMoney;
	}

	/**
	 * 设置汇总冻结金额
	 */
	public void setFronzenMoney(java.math.BigDecimal fronzenMoney) {
		this.fronzenMoney = fronzenMoney;
	}

	/**
	 * 获取汇总冻结金额
	 */
	public java.math.BigDecimal getFronzenMoney() {
		return fronzenMoney;
	}

	/**
	 * 设置执行汇总状态
	 */
	public void setCollectStatus(java.lang.Integer collectStatus) {
		this.collectStatus = collectStatus;
	}

	/**
	 * 获取执行汇总状态
	 */
	public java.lang.Integer getCollectStatus() {
		return collectStatus;
	}

	/**
	 * 设置考核单状态
	 */
	public void setCheckStatus(java.lang.Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	/**
	 * 获取考核单状态
	 */
	public java.lang.Integer getCheckStatus() {
		return checkStatus;
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
}
