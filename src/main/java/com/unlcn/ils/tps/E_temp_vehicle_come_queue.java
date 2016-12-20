package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 报班明细暂存
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_temp_vehicle_come_queue", table = "td_temp_vehicle_come_queue", ds = "ilsdb")
public class E_temp_vehicle_come_queue implements Serializable {
	private static final long serialVersionUID = 1803907327901696L;
	/**
	 *
	 */
	@Column(id = "tempid", type = ColumnType.increment, datatype = "int")
	private java.lang.Integer tempid;

	/**
	 *分供方id
	 */
	@Column(id = "ifleetid", datatype = "int")
	private java.lang.Integer ifleetid;

	/**
	 *车牌号
	 */
	@Column(id = "vclicense", datatype = "string128")
	private java.lang.String vclicense;

	/**
	 *报板时间
	 */
	@Column(id = "dtdate", datatype = "date")
	private java.sql.Date dtdate;

	/**
	 *报板城市
	 */
	@Column(id = "vccomecityname", datatype = "string128")
	private java.lang.String vccomecityname;

	/**
	 *预计抵达时间
	 */
	@Column(id = "dtrecomedate", datatype = "date")
	private java.sql.Date dtrecomedate;

	/**
	 *报板位置
	 */
	@Column(id = "vclocation", datatype = "string128")
	private java.lang.String vclocation;

	/**
	 *实际抵达时间
	 */
	@Column(id = "dtcomedate", datatype = "date")
	private java.sql.Date dtcomedate;

	/**
	 *报班状态
	 */
	@Column(id = "istatus", datatype = "string128")
	private java.lang.String istatus;

	/**
	 * 设置
	 */
	public void setTempid(java.lang.Integer tempid) {
		this.tempid = tempid;
	}

	/**
	 * 获取
	 */
	public java.lang.Integer getTempid() {
		return tempid;
	}

	/**
	 * 设置分供方id
	 */
	public void setIfleetid(java.lang.Integer ifleetid) {
		this.ifleetid = ifleetid;
	}

	/**
	 * 获取分供方id
	 */
	public java.lang.Integer getIfleetid() {
		return ifleetid;
	}

	/**
	 * 设置车牌号
	 */
	public void setVclicense(java.lang.String vclicense) {
		this.vclicense = vclicense;
	}

	/**
	 * 获取车牌号
	 */
	public java.lang.String getVclicense() {
		return vclicense;
	}

	/**
	 * 设置报板时间
	 */
	public void setDtdate(java.sql.Date dtdate) {
		this.dtdate = dtdate;
	}

	/**
	 * 获取报板时间
	 */
	public java.sql.Date getDtdate() {
		return dtdate;
	}

	/**
	 * 设置报板城市
	 */
	public void setVccomecityname(java.lang.String vccomecityname) {
		this.vccomecityname = vccomecityname;
	}

	/**
	 * 获取报板城市
	 */
	public java.lang.String getVccomecityname() {
		return vccomecityname;
	}

	/**
	 * 设置预计抵达时间
	 */
	public void setDtrecomedate(java.sql.Date dtrecomedate) {
		this.dtrecomedate = dtrecomedate;
	}

	/**
	 * 获取预计抵达时间
	 */
	public java.sql.Date getDtrecomedate() {
		return dtrecomedate;
	}

	/**
	 * 设置报板位置
	 */
	public void setVclocation(java.lang.String vclocation) {
		this.vclocation = vclocation;
	}

	/**
	 * 获取报板位置
	 */
	public java.lang.String getVclocation() {
		return vclocation;
	}

	/**
	 * 设置实际抵达时间
	 */
	public void setDtcomedate(java.sql.Date dtcomedate) {
		this.dtcomedate = dtcomedate;
	}

	/**
	 * 获取实际抵达时间
	 */
	public java.sql.Date getDtcomedate() {
		return dtcomedate;
	}

	/**
	 * 设置报班状态
	 */
	public void setIstatus(java.lang.String istatus) {
		this.istatus = istatus;
	}

	/**
	 * 获取报班状态
	 */
	public java.lang.String getIstatus() {
		return istatus;
	}
}
