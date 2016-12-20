package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 联系人信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_linkman", table = "td_linkman", ds = "ilsdb")
public class E_linkman implements Serializable {
	private static final long serialVersionUID = 1764201414311936L;
	/**
	 *
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String id;

	/**
	 *客户ID
	 */
	@Column(id = "customer_id", datatype = "string64")
	private java.lang.String customerId;

	/**
	 *服务类型，不同的服务项目的联系人
	 */
	@Column(id = "service_type", datatype = "string64")
	private java.lang.String serviceType;

	/**
	 *部门
	 */
	@Column(id = "department", datatype = "string64")
	private java.lang.String department;

	/**
	 *联系人岗位
	 */
	@Column(id = "job", datatype = "string64")
	private java.lang.String job;

	/**
	 *联系人名称
	 */
	@Column(id = "name", datatype = "string64")
	private java.lang.String name;

	/**
	 *手机
	 */
	@Column(id = "phone", datatype = "string64")
	private java.lang.String phone;

	/**
	 *联系电话
	 */
	@Column(id = "tel", datatype = "string64")
	private java.lang.String tel;

	/**
	 *邮箱
	 */
	@Column(id = "email", datatype = "string64")
	private java.lang.String email;

	/**
	 *QQ
	 */
	@Column(id = "qq", datatype = "string64")
	private java.lang.String qq;

	/**
	 *添加人
	 */
	@Column(id = "oper_user", datatype = "string64")
	private java.lang.String operUser;

	/**
	 *添加时间
	 */
	@Column(id = "oper_time", datatype = "timestamp")
	private java.sql.Timestamp operTime;

	/**
	 *加盟/合作
	 */
	@Column(id = "flag", datatype = "int")
	private java.lang.Integer flag;

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
	 * 设置客户ID
	 */
	public void setCustomerId(java.lang.String customerId) {
		this.customerId = customerId;
	}

	/**
	 * 获取客户ID
	 */
	public java.lang.String getCustomerId() {
		return customerId;
	}

	/**
	 * 设置服务类型，不同的服务项目的联系人
	 */
	public void setServiceType(java.lang.String serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * 获取服务类型，不同的服务项目的联系人
	 */
	public java.lang.String getServiceType() {
		return serviceType;
	}

	/**
	 * 设置部门
	 */
	public void setDepartment(java.lang.String department) {
		this.department = department;
	}

	/**
	 * 获取部门
	 */
	public java.lang.String getDepartment() {
		return department;
	}

	/**
	 * 设置联系人岗位
	 */
	public void setJob(java.lang.String job) {
		this.job = job;
	}

	/**
	 * 获取联系人岗位
	 */
	public java.lang.String getJob() {
		return job;
	}

	/**
	 * 设置联系人名称
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 获取联系人名称
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * 设置手机
	 */
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	/**
	 * 获取手机
	 */
	public java.lang.String getPhone() {
		return phone;
	}

	/**
	 * 设置联系电话
	 */
	public void setTel(java.lang.String tel) {
		this.tel = tel;
	}

	/**
	 * 获取联系电话
	 */
	public java.lang.String getTel() {
		return tel;
	}

	/**
	 * 设置邮箱
	 */
	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	/**
	 * 获取邮箱
	 */
	public java.lang.String getEmail() {
		return email;
	}

	/**
	 * 设置QQ
	 */
	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}

	/**
	 * 获取QQ
	 */
	public java.lang.String getQq() {
		return qq;
	}

	/**
	 * 设置添加人
	 */
	public void setOperUser(java.lang.String operUser) {
		this.operUser = operUser;
	}

	/**
	 * 获取添加人
	 */
	public java.lang.String getOperUser() {
		return operUser;
	}

	/**
	 * 设置添加时间
	 */
	public void setOperTime(java.sql.Timestamp operTime) {
		this.operTime = operTime;
	}

	/**
	 * 获取添加时间
	 */
	public java.sql.Timestamp getOperTime() {
		return operTime;
	}

	/**
	 * 设置加盟/合作
	 */
	public void setFlag(java.lang.Integer flag) {
		this.flag = flag;
	}

	/**
	 * 获取加盟/合作
	 */
	public java.lang.Integer getFlag() {
		return flag;
	}
}
