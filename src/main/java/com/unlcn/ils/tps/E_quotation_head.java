package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 报价主表
 * @author 
 * @generated  
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_quotation_head", table = "tps_quotation_head", ds = "ilsdb")
public class E_quotation_head implements Serializable {
	private static final long serialVersionUID = 1744165892603904L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *分供方id
	 */
	@Column(id = "shipper_id", datatype = "string32")
	private java.lang.String shipperId;

	/**
	 *报价单编号
	 */
	@Column(id = "quotation_no", datatype = "string32")
	private java.lang.String quotationNo;

	/**
	 *开票方式
	 */
	@Column(id = "invoiceway", datatype = "int")
	private java.lang.Integer invoiceway;

	/**
	 *支付帐期
	 */
	@Column(id = "pay_period", association = true)
	private E_pay_period payPeriod;

	/**
	 *紧急程度
	 */
	@Column(id = "urgent", datatype = "int")
	private java.lang.Integer urgent;

	/**
	 *有效日期
	 */
	@Column(id = "begin_date", datatype = "timestamp")
	private java.sql.Timestamp beginDate;

	/**
	 *失效日期
	 */
	@Column(id = "end_date", datatype = "timestamp")
	private java.sql.Timestamp endDate;

	/**
	 *份额id
	 */
	@Column(id = "share_id", datatype = "int")
	private java.lang.Integer shareId;

	/**
	 *报价单类型
	 */
	@Column(id = "quotation_type", datatype = "int")
	private java.lang.Integer quotationType;

	/**
	 *关联合同id
	 */
	@Column(id = "contract_id", datatype = "int")
	private java.lang.Integer contractId;

	/**
	 *标准价汇总
	 */
	@Column(id = "standard_price_total", datatype = "bigdecimal")
	private java.math.BigDecimal standardPriceTotal;

	/**
	 *报价单汇总
	 */
	@Column(id = "quotation_price_total", datatype = "bigdecimal")
	private java.math.BigDecimal quotationPriceTotal;

	/**
	 *溢价比例
	 */
	@Column(id = "price_overflow", datatype = "bigdecimal")
	private java.math.BigDecimal priceOverflow;

	/**
	 *溢价说明
	 */
	@Column(id = "overflow_memo", datatype = "string512")
	private java.lang.String overflowMemo;

	/**
	 *其它费用汇总
	 */
	@Column(id = "other_charge_total", datatype = "bigdecimal")
	private java.math.BigDecimal otherChargeTotal;

	/**
	 *审核状态
	 */
	@Column(id = "check_flag", datatype = "int")
	private java.lang.Integer checkFlag;

	/**
	 *报价方式(按总价/按单台)
	 */
	@Column(id = "quotation_flag", datatype = "int")
	private java.lang.Integer quotationFlag;

	/**
	 *创建人
	 */
	@Column(id = "create_user", datatype = "string32")
	private java.lang.String createUser;

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
	 *打印次数
	 */
	@Column(id = "print_count", datatype = "int")
	private java.lang.Integer printCount;

	/**
	 *运输方式id
	 */
	@Column(id = "transway_id", association = true)
	private E_transway transwayId;

	/**
	 *运输方式名称
	 */
	@Column(id = "transway_name", datatype = "string128")
	private java.lang.String transwayName;

	/**
	 *暂估价
	 */
	@Column(id = "estimate", datatype = "int")
	private java.lang.Integer estimate;

	/**
	 *已提交申请
	 */
	@Column(id = "isapply", datatype = "int")
	private java.lang.Integer isapply;

	/**
	 *提交申请时间
	 */
	@Column(id = "apply_date", datatype = "timestamp")
	private java.sql.Timestamp applyDate;

	/**
	 *当前审核人
	 */
	@Column(id = "audit_user", datatype = "string32")
	private java.lang.String auditUser;

	/**
	 *当前审核结点
	 */
	@Column(id = "flowid", datatype = "int")
	private java.lang.Integer flowid;

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
	 * 设置分供方id
	 */
	public void setShipperId(java.lang.String shipperId) {
		this.shipperId = shipperId;
	}

	/**
	 * 获取分供方id
	 */
	public java.lang.String getShipperId() {
		return shipperId;
	}

	/**
	 * 设置报价单编号
	 */
	public void setQuotationNo(java.lang.String quotationNo) {
		this.quotationNo = quotationNo;
	}

	/**
	 * 获取报价单编号
	 */
	public java.lang.String getQuotationNo() {
		return quotationNo;
	}

	/**
	 * 设置开票方式
	 */
	public void setInvoiceway(java.lang.Integer invoiceway) {
		this.invoiceway = invoiceway;
	}

	/**
	 * 获取开票方式
	 */
	public java.lang.Integer getInvoiceway() {
		return invoiceway;
	}

	/**
	 * 设置支付帐期
	 */
	public void setPayPeriod(E_pay_period payPeriod) {
		this.payPeriod = payPeriod;
	}

	/**
	 * 获取支付帐期
	 */
	public E_pay_period getPayPeriod() {
		return payPeriod;
	}

	/**
	 * 设置紧急程度
	 */
	public void setUrgent(java.lang.Integer urgent) {
		this.urgent = urgent;
	}

	/**
	 * 获取紧急程度
	 */
	public java.lang.Integer getUrgent() {
		return urgent;
	}

	/**
	 * 设置有效日期
	 */
	public void setBeginDate(java.sql.Timestamp beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取有效日期
	 */
	public java.sql.Timestamp getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置失效日期
	 */
	public void setEndDate(java.sql.Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取失效日期
	 */
	public java.sql.Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * 设置份额id
	 */
	public void setShareId(java.lang.Integer shareId) {
		this.shareId = shareId;
	}

	/**
	 * 获取份额id
	 */
	public java.lang.Integer getShareId() {
		return shareId;
	}

	/**
	 * 设置报价单类型
	 */
	public void setQuotationType(java.lang.Integer quotationType) {
		this.quotationType = quotationType;
	}

	/**
	 * 获取报价单类型
	 */
	public java.lang.Integer getQuotationType() {
		return quotationType;
	}

	/**
	 * 设置关联合同id
	 */
	public void setContractId(java.lang.Integer contractId) {
		this.contractId = contractId;
	}

	/**
	 * 获取关联合同id
	 */
	public java.lang.Integer getContractId() {
		return contractId;
	}

	/**
	 * 设置标准价汇总
	 */
	public void setStandardPriceTotal(java.math.BigDecimal standardPriceTotal) {
		this.standardPriceTotal = standardPriceTotal;
	}

	/**
	 * 获取标准价汇总
	 */
	public java.math.BigDecimal getStandardPriceTotal() {
		return standardPriceTotal;
	}

	/**
	 * 设置报价单汇总
	 */
	public void setQuotationPriceTotal(java.math.BigDecimal quotationPriceTotal) {
		this.quotationPriceTotal = quotationPriceTotal;
	}

	/**
	 * 获取报价单汇总
	 */
	public java.math.BigDecimal getQuotationPriceTotal() {
		return quotationPriceTotal;
	}

	/**
	 * 设置溢价比例
	 */
	public void setPriceOverflow(java.math.BigDecimal priceOverflow) {
		this.priceOverflow = priceOverflow;
	}

	/**
	 * 获取溢价比例
	 */
	public java.math.BigDecimal getPriceOverflow() {
		return priceOverflow;
	}

	/**
	 * 设置溢价说明
	 */
	public void setOverflowMemo(java.lang.String overflowMemo) {
		this.overflowMemo = overflowMemo;
	}

	/**
	 * 获取溢价说明
	 */
	public java.lang.String getOverflowMemo() {
		return overflowMemo;
	}

	/**
	 * 设置其它费用汇总
	 */
	public void setOtherChargeTotal(java.math.BigDecimal otherChargeTotal) {
		this.otherChargeTotal = otherChargeTotal;
	}

	/**
	 * 获取其它费用汇总
	 */
	public java.math.BigDecimal getOtherChargeTotal() {
		return otherChargeTotal;
	}

	/**
	 * 设置审核状态
	 */
	public void setCheckFlag(java.lang.Integer checkFlag) {
		this.checkFlag = checkFlag;
	}

	/**
	 * 获取审核状态
	 */
	public java.lang.Integer getCheckFlag() {
		return checkFlag;
	}

	/**
	 * 设置报价方式(按总价/按单台)
	 */
	public void setQuotationFlag(java.lang.Integer quotationFlag) {
		this.quotationFlag = quotationFlag;
	}

	/**
	 * 获取报价方式(按总价/按单台)
	 */
	public java.lang.Integer getQuotationFlag() {
		return quotationFlag;
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
	 * 设置打印次数
	 */
	public void setPrintCount(java.lang.Integer printCount) {
		this.printCount = printCount;
	}

	/**
	 * 获取打印次数
	 */
	public java.lang.Integer getPrintCount() {
		return printCount;
	}

	/**
	 * 设置运输方式id
	 */
	public void setTranswayId(E_transway transwayId) {
		this.transwayId = transwayId;
	}

	/**
	 * 获取运输方式id
	 */
	public E_transway getTranswayId() {
		return transwayId;
	}

	/**
	 * 设置运输方式名称
	 */
	public void setTranswayName(java.lang.String transwayName) {
		this.transwayName = transwayName;
	}

	/**
	 * 获取运输方式名称
	 */
	public java.lang.String getTranswayName() {
		return transwayName;
	}

	/**
	 * 设置暂估价
	 */
	public void setEstimate(java.lang.Integer estimate) {
		this.estimate = estimate;
	}

	/**
	 * 获取暂估价
	 */
	public java.lang.Integer getEstimate() {
		return estimate;
	}

	/**
	 * 设置已提交申请
	 */
	public void setIsapply(java.lang.Integer isapply) {
		this.isapply = isapply;
	}

	/**
	 * 获取已提交申请
	 */
	public java.lang.Integer getIsapply() {
		return isapply;
	}

	/**
	 * 设置提交申请时间
	 */
	public void setApplyDate(java.sql.Timestamp applyDate) {
		this.applyDate = applyDate;
	}

	/**
	 * 获取提交申请时间
	 */
	public java.sql.Timestamp getApplyDate() {
		return applyDate;
	}

	/**
	 * 设置当前审核人
	 */
	public void setAuditUser(java.lang.String auditUser) {
		this.auditUser = auditUser;
	}

	/**
	 * 获取当前审核人
	 */
	public java.lang.String getAuditUser() {
		return auditUser;
	}

	/**
	 * 设置当前审核结点
	 */
	public void setFlowid(java.lang.Integer flowid) {
		this.flowid = flowid;
	}

	/**
	 * 获取当前审核结点
	 */
	public java.lang.Integer getFlowid() {
		return flowid;
	}
}
