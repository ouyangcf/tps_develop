package com.unlcn.ils.tps;
import java.io.Serializable;
import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 线路表
 * @author 
 * @generated 
 */ 
@Entity(id = "entity:com.unlcn.ils.tps.e_line", table = "tps_line", ds = "ilsdb")
public class E_line implements Serializable {
	private static final long serialVersionUID = 1734537802776576L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *线路编号
	 */
	@Column(id = "lineno", datatype = "string64")
	private java.lang.String lineno;

	/**
	 *起运城市id
	 */
	@Column(id = "start_city_id", datatype = "int")
	private java.lang.Integer startCityId;

	/**
	 *起运城市
	 */
	@Column(id = "start_city", datatype = "string32")
	private java.lang.String startCity;

	/**
	 *起运省份id
	 */
	@Column(id = "start_province_id", datatype = "int")
	private java.lang.Integer startProvinceId;

	/**
	 *起运省份
	 */
	@Column(id = "start_province", datatype = "string32")
	private java.lang.String startProvince;

	/**
	 *目的地省份id
	 */
	@Column(id = "dest_province_id", datatype = "int")
	private java.lang.Integer destProvinceId;

	/**
	 *目的省
	 */
	@Column(id = "dest_province", datatype = "string32")
	private java.lang.String destProvince;

	/**
	 *运输方式
	 */
	@Column(id = "transway", association = true)
	private E_transway transway;

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
	 * 设置线路编号
	 */
	public void setLineno(java.lang.String lineno) {
		this.lineno = lineno;
	}

	/**
	 * 获取线路编号
	 */
	public java.lang.String getLineno() {
		return lineno;
	}

	/**
	 * 设置起运城市id
	 */
	public void setStartCityId(java.lang.Integer startCityId) {
		this.startCityId = startCityId;
	}

	/**
	 * 获取起运城市id
	 */
	public java.lang.Integer getStartCityId() {
		return startCityId;
	}

	/**
	 * 设置起运城市
	 */
	public void setStartCity(java.lang.String startCity) {
		this.startCity = startCity;
	}

	/**
	 * 获取起运城市
	 */
	public java.lang.String getStartCity() {
		return startCity;
	}

	/**
	 * 设置起运省份id
	 */
	public void setStartProvinceId(java.lang.Integer startProvinceId) {
		this.startProvinceId = startProvinceId;
	}

	/**
	 * 获取起运省份id
	 */
	public java.lang.Integer getStartProvinceId() {
		return startProvinceId;
	}

	/**
	 * 设置起运省份
	 */
	public void setStartProvince(java.lang.String startProvince) {
		this.startProvince = startProvince;
	}

	/**
	 * 获取起运省份
	 */
	public java.lang.String getStartProvince() {
		return startProvince;
	}

	/**
	 * 设置目的地省份id
	 */
	public void setDestProvinceId(java.lang.Integer destProvinceId) {
		this.destProvinceId = destProvinceId;
	}

	/**
	 * 获取目的地省份id
	 */
	public java.lang.Integer getDestProvinceId() {
		return destProvinceId;
	}

	/**
	 * 设置目的省
	 */
	public void setDestProvince(java.lang.String destProvince) {
		this.destProvince = destProvince;
	}

	/**
	 * 获取目的省
	 */
	public java.lang.String getDestProvince() {
		return destProvince;
	}

	/**
	 * 设置运输方式
	 */
	public void setTransway(E_transway transway) {
		this.transway = transway;
	}

	/**
	 * 获取运输方式
	 */
	public E_transway getTransway() {
		return transway;
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
