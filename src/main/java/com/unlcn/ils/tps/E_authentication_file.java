package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 分供方认证材料表 
 * @author  
 * @generated
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_authentication_file", table = "tps_authentication_file", ds = "ilsdb")
public class E_authentication_file implements Serializable {
	private static final long serialVersionUID = 1686832680189952L;
	/**
	 *序号
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
	@Column(id = "file_type", datatype = "string128")
	private java.lang.String fileType;

	/**
	 *
	 */
	@Column(id = "file_url", datatype = "string128")
	private java.lang.String fileUrl;

	/**
	 *
	 */
	@Column(id = "authentication_id", association = true)
	private E_authentication authenticationId;

	/**
	 *文件大小
	 */
	@Column(id = "file_size", datatype = "int")
	private java.lang.Integer fileSize;

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
	public void setFileType(java.lang.String fileType) {
		this.fileType = fileType;
	}

	/**
	 * 获取文件扩展名
	 */
	public java.lang.String getFileType() {
		return fileType;
	}

	/**
	 * 设置
	 */
	public void setFileUrl(java.lang.String fileUrl) {
		this.fileUrl = fileUrl;
	}

	/**
	 * 获取
	 */
	public java.lang.String getFileUrl() {
		return fileUrl;
	}

	/**
	 * 设置
	 */
	public void setAuthenticationId(E_authentication authenticationId) {
		this.authenticationId = authenticationId;
	}

	/**
	 * 获取
	 */
	public E_authentication getAuthenticationId() {
		return authenticationId;
	}

	/**
	 * 设置文件大小
	 */
	public void setFileSize(java.lang.Integer fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * 获取文件大小
	 */
	public java.lang.Integer getFileSize() {
		return fileSize;
	}
}
