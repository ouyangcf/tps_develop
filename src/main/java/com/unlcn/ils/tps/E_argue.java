package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/** 
 * 考核申诉单
 * @author  
 * @generated 
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_argue", table = "tps_argue", ds = "ilsdb")
public class E_argue implements Serializable {
	private static final long serialVersionUID = 1698802205900800L;
	/**
	 *
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *
	 */
	@Column(id = "checkhead_lineid", datatype = "string32")
	private java.lang.String checkheadLineid;

	/**
	 *
	 */
	@Column(id = "checkdetail_lineid", datatype = "string32")
	private java.lang.String checkdetailLineid;

	/**
	 *申诉理由
	 */
	@Column(id = "argue_reson", datatype = "string256")
	private java.lang.String argueReson;

	/**
	 *申诉状态
	 */
	@Column(id = "argue_status", datatype = "int")
	private java.lang.Integer argueStatus;

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
	@Column(id = "active", datatype = "int")
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
	public void setCheckheadLineid(java.lang.String checkheadLineid) {
		this.checkheadLineid = checkheadLineid;
	}

	/**
	 * 获取
	 */
	public java.lang.String getCheckheadLineid() {
		return checkheadLineid;
	}

	/**
	 * 设置
	 */
	public void setCheckdetailLineid(java.lang.String checkdetailLineid) {
		this.checkdetailLineid = checkdetailLineid;
	}

	/**
	 * 获取
	 */
	public java.lang.String getCheckdetailLineid() {
		return checkdetailLineid;
	}

	/**
	 * 设置申诉理由
	 */
	public void setArgueReson(java.lang.String argueReson) {
		this.argueReson = argueReson;
	}

	/**
	 * 获取申诉理由
	 */
	public java.lang.String getArgueReson() {
		return argueReson;
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
}
