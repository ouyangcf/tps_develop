package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 回单明细暂存
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_temp_return_status_order", table = "td_temp_return_status_order", ds = "ilsdb")
public class E_temp_return_status_order implements Serializable {
	private static final long serialVersionUID = 1803908243980288L;
	/**
	 *
	 */
	@Column(id = "tempid", type = ColumnType.increment, datatype = "int")
	private java.lang.Integer tempid;

	/**
	 *分供方id
	 */
	@Column(id = "ifleetid2", datatype = "int")
	private java.lang.Integer ifleetid2;

	/**
	 *分供方名称
	 */
	@Column(id = "vccustomername", datatype = "string128")
	private java.lang.String vccustomername;

	/**
	 *下单日期
	 */
	@Column(id = "orderdate", datatype = "date")
	private java.sql.Date orderdate;

	/**
	 *调度时间
	 */
	@Column(id = "shipdate", datatype = "date")
	private java.sql.Date shipdate;

	/**
	 *发运时间
	 */
	@Column(id = "dtoutdate", datatype = "date")
	private java.sql.Date dtoutdate;

	/**
	 *抵达时间
	 */
	@Column(id = "dtcomedate", datatype = "date")
	private java.sql.Date dtcomedate;

	/**
	 *车型
	 */
	@Column(id = "vcstyleno", datatype = "string128")
	private java.lang.String vcstyleno;

	/**
	 *起运地
	 */
	@Column(id = "vcstartcityname", datatype = "string128")
	private java.lang.String vcstartcityname;

	/**
	 *目的地
	 */
	@Column(id = "vcendcityname", datatype = "string128")
	private java.lang.String vcendcityname;

	/**
	 *启运省份
	 */
	@Column(id = "vcprovince", datatype = "string128")
	private java.lang.String vcprovince;

	/**
	 *启运城市
	 */
	@Column(id = "vcstartcity", datatype = "string128")
	private java.lang.String vcstartcity;

	/**
	 *订单目的地
	 */
	@Column(id = "vccityname", datatype = "string128")
	private java.lang.String vccityname;

	/**
	 *指令号
	 */
	@Column(id = "vcshipno", datatype = "string128")
	private java.lang.String vcshipno;

	/**
	 *客户订单号
	 */
	@Column(id = "vccustorderno", datatype = "string128")
	private java.lang.String vccustorderno;

	/**
	 *回单日期
	 */
	@Column(id = "returnoutdate", datatype = "string128")
	private java.lang.String returnoutdate;

	/**
	 *超期天数
	 */
	@Column(id = "returntimeout", datatype = "string128")
	private java.lang.String returntimeout;

	/**
	 *未回单原因
	 */
	@Column(id = "noreturnreason", datatype = "string128")
	private java.lang.String noreturnreason;

	/**
	 *回单状态
	 */
	@Column(id = "breturn", datatype = "string128")
	private java.lang.String breturn;

	/**
	 *回单时间
	 */
	@Column(id = "dtreturndate", datatype = "date")
	private java.sql.Date dtreturndate;

	/**
	 *分供方名称
	 */
	@Column(id = "vcfleetname", datatype = "string128")
	private java.lang.String vcfleetname;

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
	public void setIfleetid2(java.lang.Integer ifleetid2) {
		this.ifleetid2 = ifleetid2;
	}

	/**
	 * 获取分供方id
	 */
	public java.lang.Integer getIfleetid2() {
		return ifleetid2;
	}

	/**
	 * 设置分供方名称
	 */
	public void setVccustomername(java.lang.String vccustomername) {
		this.vccustomername = vccustomername;
	}

	/**
	 * 获取分供方名称
	 */
	public java.lang.String getVccustomername() {
		return vccustomername;
	}

	/**
	 * 设置下单日期
	 */
	public void setOrderdate(java.sql.Date orderdate) {
		this.orderdate = orderdate;
	}

	/**
	 * 获取下单日期
	 */
	public java.sql.Date getOrderdate() {
		return orderdate;
	}

	/**
	 * 设置调度时间
	 */
	public void setShipdate(java.sql.Date shipdate) {
		this.shipdate = shipdate;
	}

	/**
	 * 获取调度时间
	 */
	public java.sql.Date getShipdate() {
		return shipdate;
	}

	/**
	 * 设置发运时间
	 */
	public void setDtoutdate(java.sql.Date dtoutdate) {
		this.dtoutdate = dtoutdate;
	}

	/**
	 * 获取发运时间
	 */
	public java.sql.Date getDtoutdate() {
		return dtoutdate;
	}

	/**
	 * 设置抵达时间
	 */
	public void setDtcomedate(java.sql.Date dtcomedate) {
		this.dtcomedate = dtcomedate;
	}

	/**
	 * 获取抵达时间
	 */
	public java.sql.Date getDtcomedate() {
		return dtcomedate;
	}

	/**
	 * 设置车型
	 */
	public void setVcstyleno(java.lang.String vcstyleno) {
		this.vcstyleno = vcstyleno;
	}

	/**
	 * 获取车型
	 */
	public java.lang.String getVcstyleno() {
		return vcstyleno;
	}

	/**
	 * 设置起运地
	 */
	public void setVcstartcityname(java.lang.String vcstartcityname) {
		this.vcstartcityname = vcstartcityname;
	}

	/**
	 * 获取起运地
	 */
	public java.lang.String getVcstartcityname() {
		return vcstartcityname;
	}

	/**
	 * 设置目的地
	 */
	public void setVcendcityname(java.lang.String vcendcityname) {
		this.vcendcityname = vcendcityname;
	}

	/**
	 * 获取目的地
	 */
	public java.lang.String getVcendcityname() {
		return vcendcityname;
	}

	/**
	 * 设置启运省份
	 */
	public void setVcprovince(java.lang.String vcprovince) {
		this.vcprovince = vcprovince;
	}

	/**
	 * 获取启运省份
	 */
	public java.lang.String getVcprovince() {
		return vcprovince;
	}

	/**
	 * 设置启运城市
	 */
	public void setVcstartcity(java.lang.String vcstartcity) {
		this.vcstartcity = vcstartcity;
	}

	/**
	 * 获取启运城市
	 */
	public java.lang.String getVcstartcity() {
		return vcstartcity;
	}

	/**
	 * 设置订单目的地
	 */
	public void setVccityname(java.lang.String vccityname) {
		this.vccityname = vccityname;
	}

	/**
	 * 获取订单目的地
	 */
	public java.lang.String getVccityname() {
		return vccityname;
	}

	/**
	 * 设置指令号
	 */
	public void setVcshipno(java.lang.String vcshipno) {
		this.vcshipno = vcshipno;
	}

	/**
	 * 获取指令号
	 */
	public java.lang.String getVcshipno() {
		return vcshipno;
	}

	/**
	 * 设置客户订单号
	 */
	public void setVccustorderno(java.lang.String vccustorderno) {
		this.vccustorderno = vccustorderno;
	}

	/**
	 * 获取客户订单号
	 */
	public java.lang.String getVccustorderno() {
		return vccustorderno;
	}

	/**
	 * 设置回单日期
	 */
	public void setReturnoutdate(java.lang.String returnoutdate) {
		this.returnoutdate = returnoutdate;
	}

	/**
	 * 获取回单日期
	 */
	public java.lang.String getReturnoutdate() {
		return returnoutdate;
	}

	/**
	 * 设置超期天数
	 */
	public void setReturntimeout(java.lang.String returntimeout) {
		this.returntimeout = returntimeout;
	}

	/**
	 * 获取超期天数
	 */
	public java.lang.String getReturntimeout() {
		return returntimeout;
	}

	/**
	 * 设置未回单原因
	 */
	public void setNoreturnreason(java.lang.String noreturnreason) {
		this.noreturnreason = noreturnreason;
	}

	/**
	 * 获取未回单原因
	 */
	public java.lang.String getNoreturnreason() {
		return noreturnreason;
	}

	/**
	 * 设置回单状态
	 */
	public void setBreturn(java.lang.String breturn) {
		this.breturn = breturn;
	}

	/**
	 * 获取回单状态
	 */
	public java.lang.String getBreturn() {
		return breturn;
	}

	/**
	 * 设置回单时间
	 */
	public void setDtreturndate(java.sql.Date dtreturndate) {
		this.dtreturndate = dtreturndate;
	}

	/**
	 * 获取回单时间
	 */
	public java.sql.Date getDtreturndate() {
		return dtreturndate;
	}

	/**
	 * 设置分供方名称
	 */
	public void setVcfleetname(java.lang.String vcfleetname) {
		this.vcfleetname = vcfleetname;
	}

	/**
	 * 获取分供方名称
	 */
	public java.lang.String getVcfleetname() {
		return vcfleetname;
	}
}
