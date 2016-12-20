package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 考核数据汇总表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_checkDatas", table = "tps_check_datas", ds = "ilsdb")
public class E_checkDatas implements Serializable {
	private static final long serialVersionUID = 1952285207330816L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String lineid;

	/**
	 *分供方序号
	 */
	@Column(id = "shipper_id", datatype = "string32")
	private java.lang.String shipperId;

	/**
	 *分供方名称
	 */
	@Column(id = "shipper_name", datatype = "string128")
	private java.lang.String shipperName;

	/**
	 *累计扣分
	 */
	@Column(id = "subvalue", datatype = "int")
	private java.lang.Integer subvalue;

	/**
	 *考核月份
	 */
	@Column(id = "check_month", datatype = "string20")
	private java.lang.String checkMonth;

	/**
	 *考核年份
	 */
	@Column(id = "check_year", datatype = "int")
	private java.lang.Integer checkYear;

	/**
	 *考核周期
	 */
	@Column(id = "check_cycle", datatype = "int")
	private java.lang.Integer checkCycle;

	/**
	 *计划完成率
	 */
	@Column(id = "planned_completion_rate", datatype = "smalldouble")
	private java.lang.Double plannedCompletionRate;

	/**
	 *交付及时率
	 */
	@Column(id = "timely_delivery_rate", datatype = "smalldouble")
	private java.lang.Double timelyDeliveryRate;

	/**
	 *报班准确率
	 */
	@Column(id = "report_rate", datatype = "smalldouble")
	private java.lang.Double reportRate;

	/**
	 *起运及时率
	 */
	@Column(id = "departure_time_rate", datatype = "smalldouble")
	private java.lang.Double departureTimeRate;

	/**
	 *回单及时率
	 */
	@Column(id = "reply_rate", datatype = "smalldouble")
	private java.lang.Double replyRate;

	/**
	 *GPS考核
	 */
	@Column(id = "gps", datatype = "smalldouble")
	private java.lang.Double gps;

	/**
	 *规范考核扣分
	 */
	@Column(id = "operate_subvaule", datatype = "int")
	private java.lang.Integer operateSubvaule;

	/**
	 *规范考核扣款
	 */
	@Column(id = "operate_submoney", datatype = "bigdecimal")
	private java.math.BigDecimal operateSubmoney;

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
	public void setShipperId(java.lang.String shipperId) {
		this.shipperId = shipperId;
	}

	/**
	 * 获取分供方序号
	 */
	public java.lang.String getShipperId() {
		return shipperId;
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
	 * 设置累计扣分
	 */
	public void setSubvalue(java.lang.Integer subvalue) {
		this.subvalue = subvalue;
	}

	/**
	 * 获取累计扣分
	 */
	public java.lang.Integer getSubvalue() {
		return subvalue;
	}

	/**
	 * 设置考核月份
	 */
	public void setCheckMonth(java.lang.String checkMonth) {
		this.checkMonth = checkMonth;
	}

	/**
	 * 获取考核月份
	 */
	public java.lang.String getCheckMonth() {
		return checkMonth;
	}

	/**
	 * 设置考核年份
	 */
	public void setCheckYear(java.lang.Integer checkYear) {
		this.checkYear = checkYear;
	}

	/**
	 * 获取考核年份
	 */
	public java.lang.Integer getCheckYear() {
		return checkYear;
	}

	/**
	 * 设置考核周期
	 */
	public void setCheckCycle(java.lang.Integer checkCycle) {
		this.checkCycle = checkCycle;
	}

	/**
	 * 获取考核周期
	 */
	public java.lang.Integer getCheckCycle() {
		return checkCycle;
	}

	/**
	 * 设置计划完成率
	 */
	public void setPlannedCompletionRate(java.lang.Double plannedCompletionRate) {
		this.plannedCompletionRate = plannedCompletionRate;
	}

	/**
	 * 获取计划完成率
	 */
	public java.lang.Double getPlannedCompletionRate() {
		return plannedCompletionRate;
	}

	/**
	 * 设置交付及时率
	 */
	public void setTimelyDeliveryRate(java.lang.Double timelyDeliveryRate) {
		this.timelyDeliveryRate = timelyDeliveryRate;
	}

	/**
	 * 获取交付及时率
	 */
	public java.lang.Double getTimelyDeliveryRate() {
		return timelyDeliveryRate;
	}

	/**
	 * 设置报班准确率
	 */
	public void setReportRate(java.lang.Double reportRate) {
		this.reportRate = reportRate;
	}

	/**
	 * 获取报班准确率
	 */
	public java.lang.Double getReportRate() {
		return reportRate;
	}

	/**
	 * 设置起运及时率
	 */
	public void setDepartureTimeRate(java.lang.Double departureTimeRate) {
		this.departureTimeRate = departureTimeRate;
	}

	/**
	 * 获取起运及时率
	 */
	public java.lang.Double getDepartureTimeRate() {
		return departureTimeRate;
	}

	/**
	 * 设置回单及时率
	 */
	public void setReplyRate(java.lang.Double replyRate) {
		this.replyRate = replyRate;
	}

	/**
	 * 获取回单及时率
	 */
	public java.lang.Double getReplyRate() {
		return replyRate;
	}

	/**
	 * 设置GPS考核
	 */
	public void setGps(java.lang.Double gps) {
		this.gps = gps;
	}

	/**
	 * 获取GPS考核
	 */
	public java.lang.Double getGps() {
		return gps;
	}

	/**
	 * 设置规范考核扣分
	 */
	public void setOperateSubvaule(java.lang.Integer operateSubvaule) {
		this.operateSubvaule = operateSubvaule;
	}

	/**
	 * 获取规范考核扣分
	 */
	public java.lang.Integer getOperateSubvaule() {
		return operateSubvaule;
	}

	/**
	 * 设置规范考核扣款
	 */
	public void setOperateSubmoney(java.math.BigDecimal operateSubmoney) {
		this.operateSubmoney = operateSubmoney;
	}

	/**
	 * 获取规范考核扣款
	 */
	public java.math.BigDecimal getOperateSubmoney() {
		return operateSubmoney;
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
}
