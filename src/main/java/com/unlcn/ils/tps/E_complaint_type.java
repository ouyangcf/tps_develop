package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 客诉异常分类信息表
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_complaint_type", table = "td_complaint_type", ds = "ilsdb")
public class E_complaint_type implements Serializable {
	private static final long serialVersionUID = 1755650588524544L;
	/**
	 *分类ID
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

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
	@Column(id = "type_status", datatype = "string10")
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
	 *客诉异常类型,1、客诉。2、异常
	 */
	@Column(id = "type_category", datatype = "string10")
	private java.lang.String typeCategory;

	/**
	 * 设置分类ID
	 */
	public void setLineid(java.lang.String lineid) {
		this.lineid = lineid;
	}

	/**
	 * 获取分类ID
	 */
	public java.lang.String getLineid() {
		return lineid;
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
}
