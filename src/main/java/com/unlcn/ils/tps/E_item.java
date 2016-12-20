package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 考核项目字典
 * @author 
 * @generated
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_item", table = "tps_item", ds = "ilsdb")
public class E_item implements Serializable {
	private static final long serialVersionUID = 1673741708263424L;
	/**
	 *流水号
	 */
	@Column(id = "lineid", type = ColumnType.increment, datatype = "int")
	private java.lang.Integer lineid;

	/**
	 *考核项目名称
	 */
	@Column(id = "item_name", datatype = "string64")
	private java.lang.String itemName;

	/**
	 *考核规则配置表头序号
	 */
	@Column(id = "configrue_lineid", datatype = "int")
	private java.lang.Integer configrueLineid;

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
	 *1:用于绩效，2:用于规范
	 */
	@Column(id = "flag", datatype = "int")
	private java.lang.Integer flag;

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
	 * 设置考核项目名称
	 */
	public void setItemName(java.lang.String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 获取考核项目名称
	 */
	public java.lang.String getItemName() {
		return itemName;
	}

	/**
	 * 设置考核规则配置表头序号
	 */
	public void setConfigrueLineid(java.lang.Integer configrueLineid) {
		this.configrueLineid = configrueLineid;
	}

	/**
	 * 获取考核规则配置表头序号
	 */
	public java.lang.Integer getConfigrueLineid() {
		return configrueLineid;
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

	/**
	 * 设置1:用于绩效，2:用于规范
	 */
	public void setFlag(java.lang.Integer flag) {
		this.flag = flag;
	}

	/**
	 * 获取1:用于绩效，2:用于规范
	 */
	public java.lang.Integer getFlag() {
		return flag;
	}
}
