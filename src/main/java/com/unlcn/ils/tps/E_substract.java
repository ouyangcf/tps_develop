package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 减免单
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_substract", table = "tps_subtract", ds = "ilsdb")
public class E_substract implements Serializable {
	private static final long serialVersionUID = 1686088097333248L;
	/**
	 *
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *减免单来源（0：考核单；1：申诉单；2：整改单;3:其他减免）
	 */
	@Column(id = "sub_from", datatype = "int")
	private java.lang.Integer subFrom;

	/**
	 *
	 */
	@Column(id = "sub_fromid", datatype = "string32")
	private java.lang.String subFromid;

	/**
	 *
	 */
	@Column(id = "checkhead_lineid", datatype = "string32")
	private java.lang.String checkheadLineid;

	/**
	 *减免金额
	 */
	@Column(id = "sub_money", datatype = "bigdecimal")
	private java.math.BigDecimal subMoney;

	/**
	 *减免原因
	 */
	@Column(id = "sub_reson", datatype = "string128")
	private java.lang.String subReson;

	/**
	 *创建时间
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
	@Column(id = "active", datatype = "int")
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
	 * 设置减免单来源（0：考核单；1：申诉单；2：整改单;3:其他减免）
	 */
	public void setSubFrom(java.lang.Integer subFrom) {
		this.subFrom = subFrom;
	}

	/**
	 * 获取减免单来源（0：考核单；1：申诉单；2：整改单;3:其他减免）
	 */
	public java.lang.Integer getSubFrom() {
		return subFrom;
	}

	/**
	 * 设置
	 */
	public void setSubFromid(java.lang.String subFromid) {
		this.subFromid = subFromid;
	}

	/**
	 * 获取
	 */
	public java.lang.String getSubFromid() {
		return subFromid;
	}

	/**
	 * 设置
	 */
	public void setCheckheadLineid(java.lang.String checkheadLineid) {
		this.checkheadLineid = checkheadLineid;
	}

	/**
	 * 获取
	 */
	public java.lang.String getCheckheadLineid() {
		return checkheadLineid;
	}

	/**
	 * 设置减免金额
	 */
	public void setSubMoney(java.math.BigDecimal subMoney) {
		this.subMoney = subMoney;
	}

	/**
	 * 获取减免金额
	 */
	public java.math.BigDecimal getSubMoney() {
		return subMoney;
	}

	/**
	 * 设置减免原因
	 */
	public void setSubReson(java.lang.String subReson) {
		this.subReson = subReson;
	}

	/**
	 * 获取减免原因
	 */
	public java.lang.String getSubReson() {
		return subReson;
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
