package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 账户信息表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_account", table = "td_account", ds = "ilsdb")
public class E_account implements Serializable {
	private static final long serialVersionUID = 1807792004022272L;
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
	 *账户所有人类型-1：个人、2：企业
	 */
	@Column(id = "owner_type", datatype = "string64")
	private java.lang.String ownerType;

	/**
	 *账户名称
	 */
	@Column(id = "name", datatype = "string64")
	private java.lang.String name;

	/**
	 *银行
	 */
	@Column(id = "bank", datatype = "string64")
	private java.lang.String bank;

	/**
	 *账号
	 */
	@Column(id = "account_number", datatype = "string64")
	private java.lang.String accountNumber;

	/**
	 *银行账号照片
	 */
	@Column(id = "account_filepath", datatype = "string256")
	private java.lang.String accountFilepath;

	/**
	 *开户行
	 */
	@Column(id = "bank_open_branch", datatype = "string256")
	private java.lang.String bankOpenBranch;

	/**
	 *开户人
	 */
	@Column(id = "owner_name", datatype = "string64")
	private java.lang.String ownerName;

	/**
	 *备注
	 */
	@Column(id = "desc_", datatype = "string256")
	private java.lang.String desc;

	/**
	 *生效时间
	 */
	@Column(id = "start_time", datatype = "timestamp")
	private java.sql.Timestamp startTime;

	/**
	 *失效时间
	 */
	@Column(id = "end_time", datatype = "timestamp")
	private java.sql.Timestamp endTime;

	/**
	 *状态
	 */
	@Column(id = "status", datatype = "string64")
	private java.lang.String status;

	/**
	 *账户类型-，1：一般、2：专用
	 */
	@Column(id = "type", datatype = "string64")
	private java.lang.String type;

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
	 * 设置账户所有人类型-1：个人、2：企业
	 */
	public void setOwnerType(java.lang.String ownerType) {
		this.ownerType = ownerType;
	}

	/**
	 * 获取账户所有人类型-1：个人、2：企业
	 */
	public java.lang.String getOwnerType() {
		return ownerType;
	}

	/**
	 * 设置账户名称
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 获取账户名称
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * 设置银行
	 */
	public void setBank(java.lang.String bank) {
		this.bank = bank;
	}

	/**
	 * 获取银行
	 */
	public java.lang.String getBank() {
		return bank;
	}

	/**
	 * 设置账号
	 */
	public void setAccountNumber(java.lang.String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * 获取账号
	 */
	public java.lang.String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * 设置银行账号照片
	 */
	public void setAccountFilepath(java.lang.String accountFilepath) {
		this.accountFilepath = accountFilepath;
	}

	/**
	 * 获取银行账号照片
	 */
	public java.lang.String getAccountFilepath() {
		return accountFilepath;
	}

	/**
	 * 设置开户行
	 */
	public void setBankOpenBranch(java.lang.String bankOpenBranch) {
		this.bankOpenBranch = bankOpenBranch;
	}

	/**
	 * 获取开户行
	 */
	public java.lang.String getBankOpenBranch() {
		return bankOpenBranch;
	}

	/**
	 * 设置开户人
	 */
	public void setOwnerName(java.lang.String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * 获取开户人
	 */
	public java.lang.String getOwnerName() {
		return ownerName;
	}

	/**
	 * 设置备注
	 */
	public void setDesc(java.lang.String desc) {
		this.desc = desc;
	}

	/**
	 * 获取备注
	 */
	public java.lang.String getDesc() {
		return desc;
	}

	/**
	 * 设置生效时间
	 */
	public void setStartTime(java.sql.Timestamp startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取生效时间
	 */
	public java.sql.Timestamp getStartTime() {
		return startTime;
	}

	/**
	 * 设置失效时间
	 */
	public void setEndTime(java.sql.Timestamp endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取失效时间
	 */
	public java.sql.Timestamp getEndTime() {
		return endTime;
	}

	/**
	 * 设置状态
	 */
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	/**
	 * 获取状态
	 */
	public java.lang.String getStatus() {
		return status;
	}

	/**
	 * 设置账户类型-，1：一般、2：专用
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * 获取账户类型-，1：一般、2：专用
	 */
	public java.lang.String getType() {
		return type;
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
