package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 级别表
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_currentlevel", table = "tps_currentlevel", ds = "ilsdb")
public class E_currentlevel implements Serializable {
	private static final long serialVersionUID = 1682114089123840L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *分供方序号
	 */
	@Column(id = "shipper_lineid", datatype = "string32")
	private java.lang.String shipperLineid;

	/**
	 *分供方名称
	 */
	@Column(id = "shipper_name", datatype = "string64")
	private java.lang.String shipperName;

	/**
	 *线路序号
	 */
	@Column(id = "start_cityid", datatype = "string32")
	private java.lang.String startCityid;

	/**
	 *当前级别序号
	 */
	@Column(id = "level_lineid", datatype = "int")
	private java.lang.Integer levelLineid;

	/**
	 *当前级别名称
	 */
	@Column(id = "level_name", datatype = "string10")
	private java.lang.String levelName;

	/**
	 *创建人
	 */
	@Column(id = "create_username", datatype = "string64")
	private java.lang.String createUsername;

	/**
	 *创建时间
	 */
	@Column(id = "create_time", datatype = "timestamp")
	private java.sql.Timestamp createTime;

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
	 * 设置分供方序号
	 */
	public void setShipperLineid(java.lang.String shipperLineid) {
		this.shipperLineid = shipperLineid;
	}

	/**
	 * 获取分供方序号
	 */
	public java.lang.String getShipperLineid() {
		return shipperLineid;
	}

	/**
	 * 设置分供方名称
	 */
	public void setShipperName(java.lang.String shipperName) {
		this.shipperName = shipperName;
	}

	/**
	 * 获取分供方名称
	 */
	public java.lang.String getShipperName() {
		return shipperName;
	}

	/**
	 * 设置线路序号
	 */
	public void setStartCityid(java.lang.String startCityid) {
		this.startCityid = startCityid;
	}

	/**
	 * 获取线路序号
	 */
	public java.lang.String getStartCityid() {
		return startCityid;
	}

	/**
	 * 设置当前级别序号
	 */
	public void setLevelLineid(java.lang.Integer levelLineid) {
		this.levelLineid = levelLineid;
	}

	/**
	 * 获取当前级别序号
	 */
	public java.lang.Integer getLevelLineid() {
		return levelLineid;
	}

	/**
	 * 设置当前级别名称
	 */
	public void setLevelName(java.lang.String levelName) {
		this.levelName = levelName;
	}

	/**
	 * 获取当前级别名称
	 */
	public java.lang.String getLevelName() {
		return levelName;
	}

	/**
	 * 设置创建人
	 */
	public void setCreateUsername(java.lang.String createUsername) {
		this.createUsername = createUsername;
	}

	/**
	 * 获取创建人
	 */
	public java.lang.String getCreateUsername() {
		return createUsername;
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
