package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 整改单
 * @author  
 * @generated 
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_revise", table = "tps_revise", ds = "ilsdb")
public class E_revise implements Serializable {
	private static final long serialVersionUID = 1686083619012608L;
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
	 *
	 */
	@Column(id = "argue_lineid", datatype = "string32")
	private java.lang.String argueLineid;

	/**
	 *问题描述
	 */
	@Column(id = "revise_description", datatype = "string128")
	private java.lang.String reviseDescription;

	/**
	 *整改要求
	 */
	@Column(id = "revise_content", datatype = "string256")
	private java.lang.String reviseContent;

	/**
	 *最晚完成日期
	 */
	@Column(id = "dead_date", datatype = "timestamp")
	private java.sql.Timestamp deadDate;

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
	 *整改状态
	 */
	@Column(id = "revise_status", datatype = "int")
	private java.lang.Integer reviseStatus;

	/**
	 *审核结果(对应申诉单里的申诉状态，申诉通过，申诉不通过)
	 */
	@Column(id = "approve_status", datatype = "int")
	private java.lang.Integer approveStatus;

	/**
	 *根本原因
	 */
	@Column(id = "reson", datatype = "string256")
	private java.lang.String reson;

	/**
	 *临时整改措施
	 */
	@Column(id = "tempcontent", datatype = "string256")
	private java.lang.String tempcontent;

	/**
	 *长期整改措施
	 */
	@Column(id = "longcontent", datatype = "string256")
	private java.lang.String longcontent;

	/**
	 *预防措施
	 */
	@Column(id = "precontent", datatype = "string256")
	private java.lang.String precontent;

	/**
	 *验证措施
	 */
	@Column(id = "verifycontent", datatype = "string256")
	private java.lang.String verifycontent;

	/**
	 *联系方式
	 */
	@Column(id = "contact", datatype = "string128")
	private java.lang.String contact;

	/**
	 *整改人
	 */
	@Column(id = "execute_username", datatype = "string64")
	private java.lang.String executeUsername;

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
	 * 设置
	 */
	public void setArgueLineid(java.lang.String argueLineid) {
		this.argueLineid = argueLineid;
	}

	/**
	 * 获取
	 */
	public java.lang.String getArgueLineid() {
		return argueLineid;
	}

	/**
	 * 设置问题描述
	 */
	public void setReviseDescription(java.lang.String reviseDescription) {
		this.reviseDescription = reviseDescription;
	}

	/**
	 * 获取问题描述
	 */
	public java.lang.String getReviseDescription() {
		return reviseDescription;
	}

	/**
	 * 设置整改要求
	 */
	public void setReviseContent(java.lang.String reviseContent) {
		this.reviseContent = reviseContent;
	}

	/**
	 * 获取整改要求
	 */
	public java.lang.String getReviseContent() {
		return reviseContent;
	}

	/**
	 * 设置最晚完成日期
	 */
	public void setDeadDate(java.sql.Timestamp deadDate) {
		this.deadDate = deadDate;
	}

	/**
	 * 获取最晚完成日期
	 */
	public java.sql.Timestamp getDeadDate() {
		return deadDate;
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
	 * 设置整改状态
	 */
	public void setReviseStatus(java.lang.Integer reviseStatus) {
		this.reviseStatus = reviseStatus;
	}

	/**
	 * 获取整改状态
	 */
	public java.lang.Integer getReviseStatus() {
		return reviseStatus;
	}

	/**
	 * 设置审核结果(对应申诉单里的申诉状态，申诉通过，申诉不通过)
	 */
	public void setApproveStatus(java.lang.Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	/**
	 * 获取审核结果(对应申诉单里的申诉状态，申诉通过，申诉不通过)
	 */
	public java.lang.Integer getApproveStatus() {
		return approveStatus;
	}

	/**
	 * 设置根本原因
	 */
	public void setReson(java.lang.String reson) {
		this.reson = reson;
	}

	/**
	 * 获取根本原因
	 */
	public java.lang.String getReson() {
		return reson;
	}

	/**
	 * 设置临时整改措施
	 */
	public void setTempcontent(java.lang.String tempcontent) {
		this.tempcontent = tempcontent;
	}

	/**
	 * 获取临时整改措施
	 */
	public java.lang.String getTempcontent() {
		return tempcontent;
	}

	/**
	 * 设置长期整改措施
	 */
	public void setLongcontent(java.lang.String longcontent) {
		this.longcontent = longcontent;
	}

	/**
	 * 获取长期整改措施
	 */
	public java.lang.String getLongcontent() {
		return longcontent;
	}

	/**
	 * 设置预防措施
	 */
	public void setPrecontent(java.lang.String precontent) {
		this.precontent = precontent;
	}

	/**
	 * 获取预防措施
	 */
	public java.lang.String getPrecontent() {
		return precontent;
	}

	/**
	 * 设置验证措施
	 */
	public void setVerifycontent(java.lang.String verifycontent) {
		this.verifycontent = verifycontent;
	}

	/**
	 * 获取验证措施
	 */
	public java.lang.String getVerifycontent() {
		return verifycontent;
	}

	/**
	 * 设置联系方式
	 */
	public void setContact(java.lang.String contact) {
		this.contact = contact;
	}

	/**
	 * 获取联系方式
	 */
	public java.lang.String getContact() {
		return contact;
	}

	/**
	 * 设置整改人
	 */
	public void setExecuteUsername(java.lang.String executeUsername) {
		this.executeUsername = executeUsername;
	}

	/**
	 * 获取整改人
	 */
	public java.lang.String getExecuteUsername() {
		return executeUsername;
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
