package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 分供方认证原因 
 * @author 
 * @generated 
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_authentication_type", table = "tps_authentication_type", ds = "ilsdb")
public class E_authentication_type implements Serializable {
	private static final long serialVersionUID = 1734464434388992L;
	/**
	 *
	 */
	@Column(id = "lineid", type = ColumnType.increment, datatype = "int")
	private java.lang.Integer lineid;

	/**
	 *
	 */
	@Column(id = "linename", datatype = "string128")
	private java.lang.String linename;

	/**
	 *
	 */
	@Column(id = "memo", datatype = "string256")
	private java.lang.String memo;

	/**
	 * 设置
	 */
	public void setLineid(java.lang.Integer lineid) {
		this.lineid = lineid;
	}

	/**
	 * 获取
	 */
	public java.lang.Integer getLineid() {
		return lineid;
	}

	/**
	 * 设置
	 */
	public void setLinename(java.lang.String linename) {
		this.linename = linename;
	}

	/**
	 * 获取
	 */
	public java.lang.String getLinename() {
		return linename;
	}

	/**
	 * 设置
	 */
	public void setMemo(java.lang.String memo) {
		this.memo = memo;
	}

	/**
	 * 获取
	 */
	public java.lang.String getMemo() {
		return memo;
	}
}
