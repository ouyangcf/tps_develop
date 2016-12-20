package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 省份表
 * @author   
 * @generated
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_province", table = "province", ds = "ilsdb")
public class E_province implements Serializable {
	private static final long serialVersionUID = 1743362839543808L;
	/**
	 *
	 */
	@Column(id = "lineid", type = ColumnType.increment, datatype = "int")
	private java.lang.Integer lineid;

	/**
	 *1
	 */
	@Column(id = "provincename", datatype = "string128")
	private java.lang.String provincename;

	/**
	 *
	 */
	@Column(id = "provinceno", datatype = "string10")
	private java.lang.String provinceno;

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
	 * 设置1
	 */
	public void setProvincename(java.lang.String provincename) {
		this.provincename = provincename;
	}

	/**
	 * 获取1
	 */
	public java.lang.String getProvincename() {
		return provincename;
	}

	/**
	 * 设置
	 */
	public void setProvinceno(java.lang.String provinceno) {
		this.provinceno = provinceno;
	}

	/**
	 * 获取
	 */
	public java.lang.String getProvinceno() {
		return provinceno;
	}
}
