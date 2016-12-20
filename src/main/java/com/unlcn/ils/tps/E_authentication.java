package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 分供方认证表
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_authentication", table = "tps_authentication", ds = "ilsdb")
public class E_authentication implements Serializable {
	private static final long serialVersionUID = 1741383510523904L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *分供方id
	 */
	@Column(id = "shippper_id", datatype = "string32")
	private java.lang.String shippperId;

	/**
	 *认证人
	 */
	@Column(id = "authentication_user", datatype = "string32")
	private java.lang.String authenticationUser;

	/**
	 *认证时间
	 */
	@Column(id = "authentication_time", datatype = "timestamp")
	private java.sql.Timestamp authenticationTime;

	/**
	 *认证意见
	 */
	@Column(id = "authentication_memo", datatype = "string512")
	private java.lang.String authenticationMemo;

	/**
	 *申请id
	 */
	@Column(id = "join_id", datatype = "string32")
	private java.lang.String joinId;

	/**
	 *加盟/申请
	 */
	@Column(id = "join_flag", datatype = "int")
	private java.lang.Integer joinFlag;

	/**
	 *认证结果
	 */
	@Column(id = "authentication_flag", datatype = "int")
	private java.lang.Integer authenticationFlag;

	/**
	 *认证沟通方式
	 */
	@Column(id = "authentication_tool", datatype = "int")
	private java.lang.Integer authenticationTool;

	/**
	 *有效日期
	 */
	@Column(id = "begin_date", datatype = "timestamp")
	private java.sql.Timestamp beginDate;

	/**
	 *失效日期
	 */
	@Column(id = "end_date", datatype = "timestamp")
	private java.sql.Timestamp endDate;

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
	 *
	 */
	@Column(id = "authentication_reasonid", association = true)
	private E_authentication_type authenticationReasonid;

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
	 * 设置分供方id
	 */
	public void setShippperId(java.lang.String shippperId) {
		this.shippperId = shippperId;
	}

	/**
	 * 获取分供方id
	 */
	public java.lang.String getShippperId() {
		return shippperId;
	}

	/**
	 * 设置认证人
	 */
	public void setAuthenticationUser(java.lang.String authenticationUser) {
		this.authenticationUser = authenticationUser;
	}

	/**
	 * 获取认证人
	 */
	public java.lang.String getAuthenticationUser() {
		return authenticationUser;
	}

	/**
	 * 设置认证时间
	 */
	public void setAuthenticationTime(java.sql.Timestamp authenticationTime) {
		this.authenticationTime = authenticationTime;
	}

	/**
	 * 获取认证时间
	 */
	public java.sql.Timestamp getAuthenticationTime() {
		return authenticationTime;
	}

	/**
	 * 设置认证意见
	 */
	public void setAuthenticationMemo(java.lang.String authenticationMemo) {
		this.authenticationMemo = authenticationMemo;
	}

	/**
	 * 获取认证意见
	 */
	public java.lang.String getAuthenticationMemo() {
		return authenticationMemo;
	}

	/**
	 * 设置申请id
	 */
	public void setJoinId(java.lang.String joinId) {
		this.joinId = joinId;
	}

	/**
	 * 获取申请id
	 */
	public java.lang.String getJoinId() {
		return joinId;
	}

	/**
	 * 设置加盟/申请
	 */
	public void setJoinFlag(java.lang.Integer joinFlag) {
		this.joinFlag = joinFlag;
	}

	/**
	 * 获取加盟/申请
	 */
	public java.lang.Integer getJoinFlag() {
		return joinFlag;
	}

	/**
	 * 设置认证结果
	 */
	public void setAuthenticationFlag(java.lang.Integer authenticationFlag) {
		this.authenticationFlag = authenticationFlag;
	}

	/**
	 * 获取认证结果
	 */
	public java.lang.Integer getAuthenticationFlag() {
		return authenticationFlag;
	}

	/**
	 * 设置认证沟通方式
	 */
	public void setAuthenticationTool(java.lang.Integer authenticationTool) {
		this.authenticationTool = authenticationTool;
	}

	/**
	 * 获取认证沟通方式
	 */
	public java.lang.Integer getAuthenticationTool() {
		return authenticationTool;
	}

	/**
	 * 设置有效日期
	 */
	public void setBeginDate(java.sql.Timestamp beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取有效日期
	 */
	public java.sql.Timestamp getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置失效日期
	 */
	public void setEndDate(java.sql.Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取失效日期
	 */
	public java.sql.Timestamp getEndDate() {
		return endDate;
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
	 * 设置
	 */
	public void setAuthenticationReasonid(
			E_authentication_type authenticationReasonid) {
		this.authenticationReasonid = authenticationReasonid;
	}

	/**
	 * 获取
	 */
	public E_authentication_type getAuthenticationReasonid() {
		return authenticationReasonid;
	}
}
