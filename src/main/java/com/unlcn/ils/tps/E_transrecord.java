package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 历史承运信息
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_transrecord", table = "trans_record", ds = "ilsdb")
public class E_transrecord implements Serializable {
	private static final long serialVersionUID = 1756873107587072L;
	/**
	 *
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *
	 */
	@Column(id = "id", datatype = "string32")
	private java.lang.String id;

	/**
	 *
	 */
	@Column(id = "startcity", datatype = "string10")
	private java.lang.String startcity;

	/**
	 *
	 */
	@Column(id = "endprovince", datatype = "string10")
	private java.lang.String endprovince;

	/**
	 *
	 */
	@Column(id = "endcity", datatype = "string10")
	private java.lang.String endcity;

	/**
	 *
	 */
	@Column(id = "number", datatype = "smalldouble")
	private java.lang.Double number;

	/**
	 *
	 */
	@Column(id = "date", datatype = "date")
	private java.sql.Date date;

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
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置
	 */
	public void setStartcity(java.lang.String startcity) {
		this.startcity = startcity;
	}

	/**
	 * 获取
	 */
	public java.lang.String getStartcity() {
		return startcity;
	}

	/**
	 * 设置
	 */
	public void setEndprovince(java.lang.String endprovince) {
		this.endprovince = endprovince;
	}

	/**
	 * 获取
	 */
	public java.lang.String getEndprovince() {
		return endprovince;
	}

	/**
	 * 设置
	 */
	public void setEndcity(java.lang.String endcity) {
		this.endcity = endcity;
	}

	/**
	 * 获取
	 */
	public java.lang.String getEndcity() {
		return endcity;
	}

	/**
	 * 设置
	 */
	public void setNumber(java.lang.Double number) {
		this.number = number;
	}

	/**
	 * 获取
	 */
	public java.lang.Double getNumber() {
		return number;
	}

	/**
	 * 设置
	 */
	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	/**
	 * 获取
	 */
	public java.sql.Date getDate() {
		return date;
	}
}
