package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 计算方法表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_method", table = "td_method", ds = "ilsdb")
public class E_method implements Serializable {
	private static final long serialVersionUID = 1935524785340416L;
	/**
	 *序号
	 */
	@Column(id = "lineid", type = ColumnType.increment, datatype = "int")
	private java.lang.Integer lineid;

	/**
	 *方法名
	 */
	@Column(id = "method", datatype = "string64")
	private java.lang.String method;

	/**
	 *左区间
	 */
	@Column(id = "left_node", datatype = "smalldouble")
	private java.lang.Double leftNode;

	/**
	 *右区间
	 */
	@Column(id = "right_node", datatype = "smalldouble")
	private java.lang.Double rightNode;

	/**
	 *单位扣款
	 */
	@Column(id = "sub_money", datatype = "bigdecimal")
	private java.math.BigDecimal subMoney;

	/**
	 *计算指标
	 */
	@Column(id = "target", datatype = "int")
	private java.lang.Integer target;

	/**
	 *创建时间
	 */
	@Column(id = "create_time", datatype = "timestamp")
	private java.sql.Timestamp createTime;

	/**
	 *创建人
	 */
	@Column(id = "create_user", datatype = "string64")
	private java.lang.String createUser;

	/**
	 * 设置序号
	 */
	public void setLineid(java.lang.Integer lineid) {
		this.lineid = lineid;
	}

	/**
	 * 获取序号
	 */
	public java.lang.Integer getLineid() {
		return lineid;
	}

	/**
	 * 设置方法名
	 */
	public void setMethod(java.lang.String method) {
		this.method = method;
	}

	/**
	 * 获取方法名
	 */
	public java.lang.String getMethod() {
		return method;
	}

	/**
	 * 设置左区间
	 */
	public void setLeftNode(java.lang.Double leftNode) {
		this.leftNode = leftNode;
	}

	/**
	 * 获取左区间
	 */
	public java.lang.Double getLeftNode() {
		return leftNode;
	}

	/**
	 * 设置右区间
	 */
	public void setRightNode(java.lang.Double rightNode) {
		this.rightNode = rightNode;
	}

	/**
	 * 获取右区间
	 */
	public java.lang.Double getRightNode() {
		return rightNode;
	}

	/**
	 * 设置单位扣款
	 */
	public void setSubMoney(java.math.BigDecimal subMoney) {
		this.subMoney = subMoney;
	}

	/**
	 * 获取单位扣款
	 */
	public java.math.BigDecimal getSubMoney() {
		return subMoney;
	}

	/**
	 * 设置计算指标
	 */
	public void setTarget(java.lang.Integer target) {
		this.target = target;
	}

	/**
	 * 获取计算指标
	 */
	public java.lang.Integer getTarget() {
		return target;
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
