package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * crm客诉异常分类信息关联情况表
 * @author  
 * @generated 
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_crm_complaint_type", table = "tps_crm_complaint_type", ds = "ilsdb")
public class E_crm_complaint_type implements Serializable {
	private static final long serialVersionUID = 1755680856621056L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *分类ID
	 */
	@Column(id = "type_id", datatype = "string64")
	private java.lang.String typeId;

	/**
	 *客诉异常类型,1、客诉。2、异常
	 */
	@Column(id = "type_category", datatype = "string64")
	private java.lang.String typeCategory;

	/**
	 *分类名称
	 */
	@Column(id = "type_name", datatype = "string64")
	private java.lang.String typeName;

	/**
	 *分类说明
	 */
	@Column(id = "type_desc", datatype = "string64")
	private java.lang.String typeDesc;

	/**
	 *父节点ID
	 */
	@Column(id = "parent_id", datatype = "string64")
	private java.lang.String parentId;

	/**
	 *分类状态,1：生效，0：失效
	 */
	@Column(id = "type_status", datatype = "string64")
	private java.lang.String typeStatus;

	/**
	 *添加人
	 */
	@Column(id = "oper_user", datatype = "string64")
	private java.lang.String operUser;

	/**
	 *添加时间
	 */
	@Column(id = "oper_time", datatype = "string64")
	private java.lang.String operTime;

	/**
	 *关联标志，0：未关联，1：已关联
	 */
	@Column(id = "realate_flag", datatype = "int")
	private java.lang.Integer realateFlag;

	/**
	 *关联类型主键序号
	 */
	@Column(id = "realate_lineid", datatype = "int")
	private java.lang.Integer realateLineid;

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
	 * 设置分类ID
	 */
	public void setTypeId(java.lang.String typeId) {
		this.typeId = typeId;
	}

	/**
	 * 获取分类ID
	 */
	public java.lang.String getTypeId() {
		return typeId;
	}

	/**
	 * 设置客诉异常类型,1、客诉。2、异常
	 */
	public void setTypeCategory(java.lang.String typeCategory) {
		this.typeCategory = typeCategory;
	}

	/**
	 * 获取客诉异常类型,1、客诉。2、异常
	 */
	public java.lang.String getTypeCategory() {
		return typeCategory;
	}

	/**
	 * 设置分类名称
	 */
	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 获取分类名称
	 */
	public java.lang.String getTypeName() {
		return typeName;
	}

	/**
	 * 设置分类说明
	 */
	public void setTypeDesc(java.lang.String typeDesc) {
		this.typeDesc = typeDesc;
	}

	/**
	 * 获取分类说明
	 */
	public java.lang.String getTypeDesc() {
		return typeDesc;
	}

	/**
	 * 设置父节点ID
	 */
	public void setParentId(java.lang.String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取父节点ID
	 */
	public java.lang.String getParentId() {
		return parentId;
	}

	/**
	 * 设置分类状态,1：生效，0：失效
	 */
	public void setTypeStatus(java.lang.String typeStatus) {
		this.typeStatus = typeStatus;
	}

	/**
	 * 获取分类状态,1：生效，0：失效
	 */
	public java.lang.String getTypeStatus() {
		return typeStatus;
	}

	/**
	 * 设置添加人
	 */
	public void setOperUser(java.lang.String operUser) {
		this.operUser = operUser;
	}

	/**
	 * 获取添加人
	 */
	public java.lang.String getOperUser() {
		return operUser;
	}

	/**
	 * 设置添加时间
	 */
	public void setOperTime(java.lang.String operTime) {
		this.operTime = operTime;
	}

	/**
	 * 获取添加时间
	 */
	public java.lang.String getOperTime() {
		return operTime;
	}

	/**
	 * 设置关联标志，0：未关联，1：已关联
	 */
	public void setRealateFlag(java.lang.Integer realateFlag) {
		this.realateFlag = realateFlag;
	}

	/**
	 * 获取关联标志，0：未关联，1：已关联
	 */
	public java.lang.Integer getRealateFlag() {
		return realateFlag;
	}

	/**
	 * 设置关联类型主键序号
	 */
	public void setRealateLineid(java.lang.Integer realateLineid) {
		this.realateLineid = realateLineid;
	}

	/**
	 * 获取关联类型主键序号
	 */
	public java.lang.Integer getRealateLineid() {
		return realateLineid;
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
