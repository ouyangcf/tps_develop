package com.unlcn.ils.tps;

import java.io.Serializable;
import java.util.Collection;

import com.chinacreator.c2.annotation.Children;
import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 城市表
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_city", table = "city", ds = "ilsdb")
public class E_city implements Serializable {
	private static final long serialVersionUID = 1673982347001856L;
	/**
	 *流水号
	 */
	@Column(id = "lineid", type = ColumnType.increment, datatype = "int")
	private java.lang.Integer lineid;

	/**
	 *
	 */
	@Column(id = "cityname", datatype = "string128")
	private java.lang.String cityname;

	/**
	 *
	 */
	@Column(id = "provinceid", datatype = "int")
	private java.lang.Integer provinceid;

	/**
	 *所属省份
	 */
	@Column(id = "provincename", datatype = "string128")
	private java.lang.String provincename;

	/**
	 *
	 */
	@Column(id = "cityno", datatype = "string10")
	private java.lang.String cityno;

	/**
	 *省份表
	 */
	@Children(id = "e_province", fk = "lineid:lineid")
	private Collection<E_province> eProvince;

	/**
	 * 设置流水号
	 */
	public void setLineid(java.lang.Integer lineid) {
		this.lineid = lineid;
	}

	/**
	 * 获取流水号
	 */
	public java.lang.Integer getLineid() {
		return lineid;
	}

	/**
	 * 设置
	 */
	public void setCityname(java.lang.String cityname) {
		this.cityname = cityname;
	}

	/**
	 * 获取
	 */
	public java.lang.String getCityname() {
		return cityname;
	}

	/**
	 * 设置
	 */
	public void setProvinceid(java.lang.Integer provinceid) {
		this.provinceid = provinceid;
	}

	/**
	 * 获取
	 */
	public java.lang.Integer getProvinceid() {
		return provinceid;
	}

	/**
	 * 设置所属省份
	 */
	public void setProvincename(java.lang.String provincename) {
		this.provincename = provincename;
	}

	/**
	 * 获取所属省份
	 */
	public java.lang.String getProvincename() {
		return provincename;
	}

	/**
	 * 设置
	 */
	public void setCityno(java.lang.String cityno) {
		this.cityno = cityno;
	}

	/**
	 * 获取
	 */
	public java.lang.String getCityno() {
		return cityno;
	}

	/**
	 * 设置省份表
	 */
	public void setEProvince(Collection<E_province> eProvince) {
		this.eProvince = eProvince;
	}

	/**
	 * 获取省份表
	 */
	public Collection<E_province> getEProvince() {
		return eProvince;
	}
}
