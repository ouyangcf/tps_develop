package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 考核记录表 
 * @author 
 * @generated 
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_checkdocument", table = "tps_check_document", ds = "ilsdb")
public class E_checkdocument implements Serializable {
	private static final long serialVersionUID = 1671120093446144L;
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
	 *其它项目
	 */
	@Column(id = "item_other", datatype = "string64")
	private java.lang.String itemOther;

	/**
	 *考核类型名称
	 */
	@Column(id = "operate_name", datatype = "string64")
	private java.lang.String operateName;

	/**
	 *其它类型
	 */
	@Column(id = "operate_other", datatype = "string64")
	private java.lang.String operateOther;

	/**
	 *项目发生日期
	 */
	@Column(id = "start_date", datatype = "timestamp")
	private java.sql.Timestamp startDate;

	/**
	 *发生数量
	 */
	@Column(id = "check_number", datatype = "int")
	private java.lang.Integer checkNumber;

	/**
	 *考核金额
	 */
	@Column(id = "check_money", datatype = "bigdecimal")
	private java.math.BigDecimal checkMoney;

	/**
	 *标准考核金额
	 */
	@Column(id = "check_money_standard", datatype = "bigdecimal")
	private java.math.BigDecimal checkMoneyStandard;

	/**
	 *扣分
	 */
	@Column(id = "check_score", datatype = "int")
	private java.lang.Integer checkScore;

	/**
	 *标准扣分
	 */
	@Column(id = "check_score_standard", datatype = "int")
	private java.lang.Integer checkScoreStandard;

	/**
	 *考核操作规范类型序号
	 */
	@Column(id = "operate_lineid", datatype = "int")
	private java.lang.Integer operateLineid;

	/**
	 *生成考核单标识
	 */
	@Column(id = "flag", datatype = "int")
	private java.lang.Integer flag;

	/**
	 *考核说明
	 */
	@Column(id = "check_memo", datatype = "string128")
	private java.lang.String checkMemo;

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
	 * 设置其它项目
	 */
	public void setItemOther(java.lang.String itemOther) {
		this.itemOther = itemOther;
	}

	/**
	 * 获取其它项目
	 */
	public java.lang.String getItemOther() {
		return itemOther;
	}

	/**
	 * 设置考核类型名称
	 */
	public void setOperateName(java.lang.String operateName) {
		this.operateName = operateName;
	}

	/**
	 * 获取考核类型名称
	 */
	public java.lang.String getOperateName() {
		return operateName;
	}

	/**
	 * 设置其它类型
	 */
	public void setOperateOther(java.lang.String operateOther) {
		this.operateOther = operateOther;
	}

	/**
	 * 获取其它类型
	 */
	public java.lang.String getOperateOther() {
		return operateOther;
	}

	/**
	 * 设置项目发生日期
	 */
	public void setStartDate(java.sql.Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * 获取项目发生日期
	 */
	public java.sql.Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * 设置发生数量
	 */
	public void setCheckNumber(java.lang.Integer checkNumber) {
		this.checkNumber = checkNumber;
	}

	/**
	 * 获取发生数量
	 */
	public java.lang.Integer getCheckNumber() {
		return checkNumber;
	}

	/**
	 * 设置考核金额
	 */
	public void setCheckMoney(java.math.BigDecimal checkMoney) {
		this.checkMoney = checkMoney;
	}

	/**
	 * 获取考核金额
	 */
	public java.math.BigDecimal getCheckMoney() {
		return checkMoney;
	}

	/**
	 * 设置标准考核金额
	 */
	public void setCheckMoneyStandard(java.math.BigDecimal checkMoneyStandard) {
		this.checkMoneyStandard = checkMoneyStandard;
	}

	/**
	 * 获取标准考核金额
	 */
	public java.math.BigDecimal getCheckMoneyStandard() {
		return checkMoneyStandard;
	}

	/**
	 * 设置扣分
	 */
	public void setCheckScore(java.lang.Integer checkScore) {
		this.checkScore = checkScore;
	}

	/**
	 * 获取扣分
	 */
	public java.lang.Integer getCheckScore() {
		return checkScore;
	}

	/**
	 * 设置标准扣分
	 */
	public void setCheckScoreStandard(java.lang.Integer checkScoreStandard) {
		this.checkScoreStandard = checkScoreStandard;
	}

	/**
	 * 获取标准扣分
	 */
	public java.lang.Integer getCheckScoreStandard() {
		return checkScoreStandard;
	}

	/**
	 * 设置考核操作规范类型序号
	 */
	public void setOperateLineid(java.lang.Integer operateLineid) {
		this.operateLineid = operateLineid;
	}

	/**
	 * 获取考核操作规范类型序号
	 */
	public java.lang.Integer getOperateLineid() {
		return operateLineid;
	}

	/**
	 * 设置生成考核单标识
	 */
	public void setFlag(java.lang.Integer flag) {
		this.flag = flag;
	}

	/**
	 * 获取生成考核单标识
	 */
	public java.lang.Integer getFlag() {
		return flag;
	}

	/**
	 * 设置考核说明
	 */
	public void setCheckMemo(java.lang.String checkMemo) {
		this.checkMemo = checkMemo;
	}

	/**
	 * 获取考核说明
	 */
	public java.lang.String getCheckMemo() {
		return checkMemo;
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
