package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 考核规则配置表明细
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_configure_detail", table = "tps_configure_detail", ds = "ilsdb")
public class E_configure_detail implements Serializable {
	private static final long serialVersionUID = 1673743456616448L;
	/**
	 *
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *
	 */
	@Column(id = "configure_lineid", datatype = "string32")
	private java.lang.String configureLineid;

	/**
	 *考核规范操作序号
	 */
	@Column(id = "item_name", datatype = "int")
	private java.lang.Integer itemName;

	/**
	 *考核规范操作类型
	 */
	@Column(id = "opearate_name", datatype = "string64")
	private java.lang.String opearateName;

	/**
	 *考核依据
	 */
	@Column(id = "description", datatype = "string64")
	private java.lang.String description;

	/**
	 *权重
	 */
	@Column(id = "weight", datatype = "int")
	private java.lang.Integer weight;

	/**
	 *指标要求
	 */
	@Column(id = "requirements", datatype = "string512")
	private java.lang.String requirements;

	/**
	 *总分
	 */
	@Column(id = "totalvalue", datatype = "int")
	private java.lang.Integer totalvalue;

	/**
	 *每降一个百分点扣分值
	 */
	@Column(id = "subvalue", datatype = "int")
	private java.lang.Integer subvalue;

	/**
	 *最低得分指标
	 */
	@Column(id = "lowvalue", datatype = "int")
	private java.lang.Integer lowvalue;

	/**
	 *计算方法
	 */
	@Column(id = "cal_description", datatype = "string128")
	private java.lang.String calDescription;

	/**
	 *考核金额
	 */
	@Column(id = "checkvalue", datatype = "bigdecimal")
	private java.math.BigDecimal checkvalue;

	/**
	 *货款帐期
	 */
	@Column(id = "finacialdesc", datatype = "int")
	private java.lang.Integer finacialdesc;

	/**
	 *开始日期
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
	public void setConfigureLineid(java.lang.String configureLineid) {
		this.configureLineid = configureLineid;
	}

	/**
	 * 获取
	 */
	public java.lang.String getConfigureLineid() {
		return configureLineid;
	}

	/**
	 * 设置考核规范操作序号
	 */
	public void setItemName(java.lang.Integer itemName) {
		this.itemName = itemName;
	}

	/**
	 * 获取考核规范操作序号
	 */
	public java.lang.Integer getItemName() {
		return itemName;
	}

	/**
	 * 设置考核规范操作类型
	 */
	public void setOpearateName(java.lang.String opearateName) {
		this.opearateName = opearateName;
	}

	/**
	 * 获取考核规范操作类型
	 */
	public java.lang.String getOpearateName() {
		return opearateName;
	}

	/**
	 * 设置考核依据
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * 获取考核依据
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * 设置权重
	 */
	public void setWeight(java.lang.Integer weight) {
		this.weight = weight;
	}

	/**
	 * 获取权重
	 */
	public java.lang.Integer getWeight() {
		return weight;
	}

	/**
	 * 设置指标要求
	 */
	public void setRequirements(java.lang.String requirements) {
		this.requirements = requirements;
	}

	/**
	 * 获取指标要求
	 */
	public java.lang.String getRequirements() {
		return requirements;
	}

	/**
	 * 设置总分
	 */
	public void setTotalvalue(java.lang.Integer totalvalue) {
		this.totalvalue = totalvalue;
	}

	/**
	 * 获取总分
	 */
	public java.lang.Integer getTotalvalue() {
		return totalvalue;
	}

	/**
	 * 设置每降一个百分点扣分值
	 */
	public void setSubvalue(java.lang.Integer subvalue) {
		this.subvalue = subvalue;
	}

	/**
	 * 获取每降一个百分点扣分值
	 */
	public java.lang.Integer getSubvalue() {
		return subvalue;
	}

	/**
	 * 设置最低得分指标
	 */
	public void setLowvalue(java.lang.Integer lowvalue) {
		this.lowvalue = lowvalue;
	}

	/**
	 * 获取最低得分指标
	 */
	public java.lang.Integer getLowvalue() {
		return lowvalue;
	}

	/**
	 * 设置计算方法
	 */
	public void setCalDescription(java.lang.String calDescription) {
		this.calDescription = calDescription;
	}

	/**
	 * 获取计算方法
	 */
	public java.lang.String getCalDescription() {
		return calDescription;
	}

	/**
	 * 设置考核金额
	 */
	public void setCheckvalue(java.math.BigDecimal checkvalue) {
		this.checkvalue = checkvalue;
	}

	/**
	 * 获取考核金额
	 */
	public java.math.BigDecimal getCheckvalue() {
		return checkvalue;
	}

	/**
	 * 设置货款帐期
	 */
	public void setFinacialdesc(java.lang.Integer finacialdesc) {
		this.finacialdesc = finacialdesc;
	}

	/**
	 * 获取货款帐期
	 */
	public java.lang.Integer getFinacialdesc() {
		return finacialdesc;
	}

	/**
	 * 设置开始日期
	 */
	public void setStartDate(java.sql.Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * 获取开始日期
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
