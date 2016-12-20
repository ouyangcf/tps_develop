package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 货物型号
 * @author  
 * @generated 
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_style", table = "tps_style", ds = "ilsdb")
public class E_style implements Serializable {
	private static final long serialVersionUID = 1743364960501760L;
	/**
	 *
	 */
	@Column(id = "lineid", type = ColumnType.increment, datatype = "int")
	private java.lang.Integer lineid;

	/**
	 *
	 */
	@Column(id = "stylename", datatype = "string128")
	private java.lang.String stylename;

	/**
	 *
	 */
	@Column(id = "customerid", datatype = "int")
	private java.lang.Integer customerid;

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
	public void setStylename(java.lang.String stylename) {
		this.stylename = stylename;
	}

	/**
	 * 获取
	 */
	public java.lang.String getStylename() {
		return stylename;
	}

	/**
	 * 设置
	 */
	public void setCustomerid(java.lang.Integer customerid) {
		this.customerid = customerid;
	}

	/**
	 * 获取
	 */
	public java.lang.Integer getCustomerid() {
		return customerid;
	}
}
