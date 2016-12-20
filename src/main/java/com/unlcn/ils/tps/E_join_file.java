package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 加盟合作附件表
 * @author  
 * @generated 
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_join_file", table = "tps_join_file", ds = "ilsdb")
public class E_join_file implements Serializable {
	private static final long serialVersionUID = 1730154013818880L;
	/**
	 *
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

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
	 *文件名
	 */
	@Column(id = "file_name", datatype = "string128")
	private java.lang.String fileName;

	/**
	 *文件说明
	 */
	@Column(id = "file_memo", datatype = "string128")
	private java.lang.String fileMemo;

	/**
	 *文件路径
	 */
	@Column(id = "file_path", datatype = "string512")
	private java.lang.String filePath;

	/**
	 *文件扩展名
	 */
	@Column(id = "file_ext", datatype = "string10")
	private java.lang.String fileExt;

	/**
	 *文件所属
	 */
	@Column(id = "flag", datatype = "int")
	private java.lang.Integer flag;

	/**
	 *来源id
	 */
	@Column(id = "source_id", datatype = "string32")
	private java.lang.String sourceId;

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

	/**
	 * 设置文件名
	 */
	public void setFileName(java.lang.String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 获取文件名
	 */
	public java.lang.String getFileName() {
		return fileName;
	}

	/**
	 * 设置文件说明
	 */
	public void setFileMemo(java.lang.String fileMemo) {
		this.fileMemo = fileMemo;
	}

	/**
	 * 获取文件说明
	 */
	public java.lang.String getFileMemo() {
		return fileMemo;
	}

	/**
	 * 设置文件路径
	 */
	public void setFilePath(java.lang.String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 获取文件路径
	 */
	public java.lang.String getFilePath() {
		return filePath;
	}

	/**
	 * 设置文件扩展名
	 */
	public void setFileExt(java.lang.String fileExt) {
		this.fileExt = fileExt;
	}

	/**
	 * 获取文件扩展名
	 */
	public java.lang.String getFileExt() {
		return fileExt;
	}

	/**
	 * 设置文件所属
	 */
	public void setFlag(java.lang.Integer flag) {
		this.flag = flag;
	}

	/**
	 * 获取文件所属
	 */
	public java.lang.Integer getFlag() {
		return flag;
	}

	/**
	 * 设置来源id
	 */
	public void setSourceId(java.lang.String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * 获取来源id
	 */
	public java.lang.String getSourceId() {
		return sourceId;
	}
}
