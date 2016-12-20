package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 联系人信息
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_contact_information", table = "tps_contact_information", ds = "ilsdb")
public class E_contact_information implements Serializable {
	private static final long serialVersionUID = 1762767247605760L;
	/**
	 *
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *申请id
	 */
	@Column(id = "join_id", datatype = "int")
	private java.lang.Integer joinId;

	/**
	 *加盟/合作
	 */
	@Column(id = "join_flag", datatype = "int")
	private java.lang.Integer joinFlag;

	/**
	 *职责岗位
	 */
	@Column(id = "contact_respons", datatype = "string32")
	private java.lang.String contactRespons;

	/**
	 *姓名
	 */
	@Column(id = "contact_name", datatype = "string32")
	private java.lang.String contactName;

	/**
	 *电话
	 */
	@Column(id = "contact_phone", datatype = "string32")
	private java.lang.String contactPhone;

	/**
	 *QQ
	 */
	@Column(id = "contact_qq", datatype = "string32")
	private java.lang.String contactQq;

	/**
	 *E-mail
	 */
	@Column(id = "contact_email", datatype = "string32")
	private java.lang.String contactEmail;

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
	 * 设置申请id
	 */
	public void setJoinId(java.lang.Integer joinId) {
		this.joinId = joinId;
	}

	/**
	 * 获取申请id
	 */
	public java.lang.Integer getJoinId() {
		return joinId;
	}

	/**
	 * 设置加盟/合作
	 */
	public void setJoinFlag(java.lang.Integer joinFlag) {
		this.joinFlag = joinFlag;
	}

	/**
	 * 获取加盟/合作
	 */
	public java.lang.Integer getJoinFlag() {
		return joinFlag;
	}

	/**
	 * 设置职责岗位
	 */
	public void setContactRespons(java.lang.String contactRespons) {
		this.contactRespons = contactRespons;
	}

	/**
	 * 获取职责岗位
	 */
	public java.lang.String getContactRespons() {
		return contactRespons;
	}

	/**
	 * 设置姓名
	 */
	public void setContactName(java.lang.String contactName) {
		this.contactName = contactName;
	}

	/**
	 * 获取姓名
	 */
	public java.lang.String getContactName() {
		return contactName;
	}

	/**
	 * 设置电话
	 */
	public void setContactPhone(java.lang.String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * 获取电话
	 */
	public java.lang.String getContactPhone() {
		return contactPhone;
	}

	/**
	 * 设置QQ
	 */
	public void setContactQq(java.lang.String contactQq) {
		this.contactQq = contactQq;
	}

	/**
	 * 获取QQ
	 */
	public java.lang.String getContactQq() {
		return contactQq;
	}

	/**
	 * 设置E-mail
	 */
	public void setContactEmail(java.lang.String contactEmail) {
		this.contactEmail = contactEmail;
	}

	/**
	 * 获取E-mail
	 */
	public java.lang.String getContactEmail() {
		return contactEmail;
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
