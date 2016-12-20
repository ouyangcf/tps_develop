package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 考核操作规范表
 * @author 
 * @generated 
 */ 
@Entity(id = "entity:com.unlcn.ils.tps.e_checkrule", table = "tps_checkrule", ds = "ilsdb")
public class E_checkrule implements Serializable {
	private static final long serialVersionUID = 1671037854711808L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.increment, datatype = "int")
	private java.lang.Integer lineid;

	/**
	 *考核项目序号
	 */
	@Column(id = "item_lineid", datatype = "int")
	private java.lang.Integer itemLineid;

	/**
	 *考核项目名称
	 */
	@Column(id = "item_name", datatype = "string64")
	private java.lang.String itemName;

	/**
	 *考核规范操作类型名称
	 */
	@Column(id = "operate_name", datatype = "string64")
	private java.lang.String operateName;

	/**
	 *每发生一起扣分值
	 */
	@Column(id = "subvalue", datatype = "int")
	private java.lang.Integer subvalue;

	/**
	 *考核金额
	 */
	@Column(id = "submoney", datatype = "bigdecimal")
	private java.math.BigDecimal submoney;

	/**
	 *开始有效日期
	 */
	@Column(id = "start_date", datatype = "timestamp")
	private java.sql.Timestamp startDate;

	/**
	 *结束日期
	 */
	@Column(id = "end_date", datatype = "timestamp")
	private java.sql.Timestamp endDate;

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
	 * 设置序号
	 */
	public void setLineid(java.lang.Integer lineid) {
		this.lineid = lineid;
	}

	/**
	 * 获取序号
	 */
	public java.lang.Integer getLineid() {
		return lineid;
	}

	/**
	 * 设置考核项目序号
	 */
	public void setItemLineid(java.lang.Integer itemLineid) {
		this.itemLineid = itemLineid;
	}

	/**
	 * 获取考核项目序号
	 */
	public java.lang.Integer getItemLineid() {
		return itemLineid;
	}

	/**
	 * 设置考核项目名称
	 */
	public void setItemName(java.lang.String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 获取考核项目名称
	 */
	public java.lang.String getItemName() {
		return itemName;
	}

	/**
	 * 设置考核规范操作类型名称
	 */
	public void setOperateName(java.lang.String operateName) {
		this.operateName = operateName;
	}

	/**
	 * 获取考核规范操作类型名称
	 */
	public java.lang.String getOperateName() {
		return operateName;
	}

	/**
	 * 设置每发生一起扣分值
	 */
	public void setSubvalue(java.lang.Integer subvalue) {
		this.subvalue = subvalue;
	}

	/**
	 * 获取每发生一起扣分值
	 */
	public java.lang.Integer getSubvalue() {
		return subvalue;
	}

	/**
	 * 设置考核金额
	 */
	public void setSubmoney(java.math.BigDecimal submoney) {
		this.submoney = submoney;
	}

	/**
	 * 获取考核金额
	 */
	public java.math.BigDecimal getSubmoney() {
		return submoney;
	}

	/**
	 * 设置开始有效日期
	 */
	public void setStartDate(java.sql.Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * 获取开始有效日期
	 */
	public java.sql.Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * 设置结束日期
	 */
	public void setEndDate(java.sql.Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取结束日期
	 */
	public java.sql.Timestamp getEndDate() {
		return endDate;
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
