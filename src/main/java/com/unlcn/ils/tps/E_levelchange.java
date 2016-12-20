package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 级别调整
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_levelchange", table = "tps_levelchange", ds = "ilsdb")
public class E_levelchange implements Serializable {
	private static final long serialVersionUID = 1682115543711744L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *当前级别表序号
	 */
	@Column(id = "leveltable_lineid", datatype = "string32")
	private java.lang.String leveltableLineid;

	/**
	 *分供方序号
	 */
	@Column(id = "shipper_lineid", datatype = "string32")
	private java.lang.String shipperLineid;

	/**
	 *线路序号
	 */
	@Column(id = "rout_lineid", datatype = "string32")
	private java.lang.String routLineid;

	/**
	 *调整前级别序号
	 */
	@Column(id = "prelevelid", datatype = "int")
	private java.lang.Integer prelevelid;

	/**
	 *调整前级别名称
	 */
	@Column(id = "prelevelname", datatype = "string10")
	private java.lang.String prelevelname;

	/**
	 *调整后级别序号
	 */
	@Column(id = "afterlevelid", datatype = "int")
	private java.lang.Integer afterlevelid;

	/**
	 *调整后级别名称
	 */
	@Column(id = "afterlevelname", datatype = "string10")
	private java.lang.String afterlevelname;

	/**
	 *调整原因序号
	 */
	@Column(id = "resonchange_lineid", datatype = "int")
	private java.lang.Integer resonchangeLineid;

	/**
	 *调整原因
	 */
	@Column(id = "resonchange_name", datatype = "string128")
	private java.lang.String resonchangeName;

	/**
	 *其它原因
	 */
	@Column(id = "change_otherreson", datatype = "string128")
	private java.lang.String changeOtherreson;

	/**
	 *级别有效开始日期
	 */
	@Column(id = "strart_date", datatype = "timestamp")
	private java.sql.Timestamp strartDate;

	/**
	 *级别有效结束日期
	 */
	@Column(id = "end_date", datatype = "timestamp")
	private java.sql.Timestamp endDate;

	/**
	 *审核人
	 */
	@Column(id = "approve_username", datatype = "string64")
	private java.lang.String approveUsername;

	/**
	 *审核意见
	 */
	@Column(id = "approve_memo", datatype = "string128")
	private java.lang.String approveMemo;

	/**
	 *审核时间
	 */
	@Column(id = "approve_time", datatype = "timestamp")
	private java.sql.Timestamp approveTime;

	/**
	 *审核结果
	 */
	@Column(id = "approve_status", datatype = "int")
	private java.lang.Integer approveStatus;

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
	 * 设置当前级别表序号
	 */
	public void setLeveltableLineid(java.lang.String leveltableLineid) {
		this.leveltableLineid = leveltableLineid;
	}

	/**
	 * 获取当前级别表序号
	 */
	public java.lang.String getLeveltableLineid() {
		return leveltableLineid;
	}

	/**
	 * 设置分供方序号
	 */
	public void setShipperLineid(java.lang.String shipperLineid) {
		this.shipperLineid = shipperLineid;
	}

	/**
	 * 获取分供方序号
	 */
	public java.lang.String getShipperLineid() {
		return shipperLineid;
	}

	/**
	 * 设置线路序号
	 */
	public void setRoutLineid(java.lang.String routLineid) {
		this.routLineid = routLineid;
	}

	/**
	 * 获取线路序号
	 */
	public java.lang.String getRoutLineid() {
		return routLineid;
	}

	/**
	 * 设置调整前级别序号
	 */
	public void setPrelevelid(java.lang.Integer prelevelid) {
		this.prelevelid = prelevelid;
	}

	/**
	 * 获取调整前级别序号
	 */
	public java.lang.Integer getPrelevelid() {
		return prelevelid;
	}

	/**
	 * 设置调整前级别名称
	 */
	public void setPrelevelname(java.lang.String prelevelname) {
		this.prelevelname = prelevelname;
	}

	/**
	 * 获取调整前级别名称
	 */
	public java.lang.String getPrelevelname() {
		return prelevelname;
	}

	/**
	 * 设置调整后级别序号
	 */
	public void setAfterlevelid(java.lang.Integer afterlevelid) {
		this.afterlevelid = afterlevelid;
	}

	/**
	 * 获取调整后级别序号
	 */
	public java.lang.Integer getAfterlevelid() {
		return afterlevelid;
	}

	/**
	 * 设置调整后级别名称
	 */
	public void setAfterlevelname(java.lang.String afterlevelname) {
		this.afterlevelname = afterlevelname;
	}

	/**
	 * 获取调整后级别名称
	 */
	public java.lang.String getAfterlevelname() {
		return afterlevelname;
	}

	/**
	 * 设置调整原因序号
	 */
	public void setResonchangeLineid(java.lang.Integer resonchangeLineid) {
		this.resonchangeLineid = resonchangeLineid;
	}

	/**
	 * 获取调整原因序号
	 */
	public java.lang.Integer getResonchangeLineid() {
		return resonchangeLineid;
	}

	/**
	 * 设置调整原因
	 */
	public void setResonchangeName(java.lang.String resonchangeName) {
		this.resonchangeName = resonchangeName;
	}

	/**
	 * 获取调整原因
	 */
	public java.lang.String getResonchangeName() {
		return resonchangeName;
	}

	/**
	 * 设置其它原因
	 */
	public void setChangeOtherreson(java.lang.String changeOtherreson) {
		this.changeOtherreson = changeOtherreson;
	}

	/**
	 * 获取其它原因
	 */
	public java.lang.String getChangeOtherreson() {
		return changeOtherreson;
	}

	/**
	 * 设置级别有效开始日期
	 */
	public void setStrartDate(java.sql.Timestamp strartDate) {
		this.strartDate = strartDate;
	}

	/**
	 * 获取级别有效开始日期
	 */
	public java.sql.Timestamp getStrartDate() {
		return strartDate;
	}

	/**
	 * 设置级别有效结束日期
	 */
	public void setEndDate(java.sql.Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取级别有效结束日期
	 */
	public java.sql.Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * 设置审核人
	 */
	public void setApproveUsername(java.lang.String approveUsername) {
		this.approveUsername = approveUsername;
	}

	/**
	 * 获取审核人
	 */
	public java.lang.String getApproveUsername() {
		return approveUsername;
	}

	/**
	 * 设置审核意见
	 */
	public void setApproveMemo(java.lang.String approveMemo) {
		this.approveMemo = approveMemo;
	}

	/**
	 * 获取审核意见
	 */
	public java.lang.String getApproveMemo() {
		return approveMemo;
	}

	/**
	 * 设置审核时间
	 */
	public void setApproveTime(java.sql.Timestamp approveTime) {
		this.approveTime = approveTime;
	}

	/**
	 * 获取审核时间
	 */
	public java.sql.Timestamp getApproveTime() {
		return approveTime;
	}

	/**
	 * 设置审核结果
	 */
	public void setApproveStatus(java.lang.Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	/**
	 * 获取审核结果
	 */
	public java.lang.Integer getApproveStatus() {
		return approveStatus;
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
