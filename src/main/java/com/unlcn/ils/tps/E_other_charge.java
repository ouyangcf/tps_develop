package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 报价单其它费用
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_other_charge", table = "tps_other_charge", ds = "ilsdb")
public class E_other_charge implements Serializable {
	private static final long serialVersionUID = 1756893422338048L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *费用id
	 */
	@Column(id = "charge_id", association = true)
	private E_charge_list chargeId;

	/**
	 *费用名称
	 */
	@Column(id = "charge_name", datatype = "string64")
	private java.lang.String chargeName;

	/**
	 *费用说明
	 */
	@Column(id = "charge_desc", datatype = "string512")
	private java.lang.String chargeDesc;

	/**
	 *金额
	 */
	@Column(id = "total", datatype = "bigdecimal")
	private java.math.BigDecimal total;

	/**
	 *报价单编号
	 */
	@Column(id = "quotation_id", association = true)
	private E_quotation_head quotationId;

	/**
	 *创建时间1
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
	 * 设置费用id
	 */
	public void setChargeId(E_charge_list chargeId) {
		this.chargeId = chargeId;
	}

	/**
	 * 获取费用id
	 */
	public E_charge_list getChargeId() {
		return chargeId;
	}

	/**
	 * 设置费用名称
	 */
	public void setChargeName(java.lang.String chargeName) {
		this.chargeName = chargeName;
	}

	/**
	 * 获取费用名称
	 */
	public java.lang.String getChargeName() {
		return chargeName;
	}

	/**
	 * 设置费用说明
	 */
	public void setChargeDesc(java.lang.String chargeDesc) {
		this.chargeDesc = chargeDesc;
	}

	/**
	 * 获取费用说明
	 */
	public java.lang.String getChargeDesc() {
		return chargeDesc;
	}

	/**
	 * 设置金额
	 */
	public void setTotal(java.math.BigDecimal total) {
		this.total = total;
	}

	/**
	 * 获取金额
	 */
	public java.math.BigDecimal getTotal() {
		return total;
	}

	/**
	 * 设置报价单编号
	 */
	public void setQuotationId(E_quotation_head quotationId) {
		this.quotationId = quotationId;
	}

	/**
	 * 获取报价单编号
	 */
	public E_quotation_head getQuotationId() {
		return quotationId;
	}

	/**
	 * 设置创建时间1
	 */
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取创建时间1
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
