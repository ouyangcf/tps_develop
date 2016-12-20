package com.unlcn.ils.tps;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 序列表
 * @author  
 * @generated 
 */
@Entity(id = "entity:com.unlcn.ils.tps.e_sequence_data", table = "sequence_data", ds = "ilsdb")
public class E_sequence_data implements Serializable {
	private static final long serialVersionUID = 1781285091655680L;
	/**
	 *序列名称
	 */
	@Column(id = "sequence_name", type = ColumnType.uuid, datatype = "string128")
	private java.lang.String sequenceName;

	/**
	 *序列描述
	 */
	@Column(id = "sequence_desc", datatype = "string256")
	private java.lang.String sequenceDesc;

	/**
	 *自增量
	 */
	@Column(id = "sequence_increment", datatype = "int")
	private java.lang.Integer sequenceIncrement;

	/**
	 *序列最小值
	 */
	@Column(id = "sequence_min_value", datatype = "int")
	private java.lang.Integer sequenceMinValue;

	/**
	 *序列最大值
	 */
	@Column(id = "sequence_max_value", datatype = "bigint")
	private java.math.BigInteger sequenceMaxValue;

	/**
	 *序列当前值
	 */
	@Column(id = "sequence_cur_value", datatype = "bigint")
	private java.math.BigInteger sequenceCurValue;

	/**
	 *是否循环
	 */
	@Column(id = "sequence_cycle", datatype = "tinyint")
	private java.lang.Integer sequenceCycle;

	/**
	 *序列前缀
	 */
	@Column(id = "sequence_prefix", datatype = "string5")
	private java.lang.String sequencePrefix;

	/**
	 * 设置序列名称
	 */
	public void setSequenceName(java.lang.String sequenceName) {
		this.sequenceName = sequenceName;
	}

	/**
	 * 获取序列名称
	 */
	public java.lang.String getSequenceName() {
		return sequenceName;
	}

	/**
	 * 设置序列描述
	 */
	public void setSequenceDesc(java.lang.String sequenceDesc) {
		this.sequenceDesc = sequenceDesc;
	}

	/**
	 * 获取序列描述
	 */
	public java.lang.String getSequenceDesc() {
		return sequenceDesc;
	}

	/**
	 * 设置自增量
	 */
	public void setSequenceIncrement(java.lang.Integer sequenceIncrement) {
		this.sequenceIncrement = sequenceIncrement;
	}

	/**
	 * 获取自增量
	 */
	public java.lang.Integer getSequenceIncrement() {
		return sequenceIncrement;
	}

	/**
	 * 设置序列最小值
	 */
	public void setSequenceMinValue(java.lang.Integer sequenceMinValue) {
		this.sequenceMinValue = sequenceMinValue;
	}

	/**
	 * 获取序列最小值
	 */
	public java.lang.Integer getSequenceMinValue() {
		return sequenceMinValue;
	}

	/**
	 * 设置序列最大值
	 */
	public void setSequenceMaxValue(java.math.BigInteger sequenceMaxValue) {
		this.sequenceMaxValue = sequenceMaxValue;
	}

	/**
	 * 获取序列最大值
	 */
	public java.math.BigInteger getSequenceMaxValue() {
		return sequenceMaxValue;
	}

	/**
	 * 设置序列当前值
	 */
	public void setSequenceCurValue(java.math.BigInteger sequenceCurValue) {
		this.sequenceCurValue = sequenceCurValue;
	}

	/**
	 * 获取序列当前值
	 */
	public java.math.BigInteger getSequenceCurValue() {
		return sequenceCurValue;
	}

	/**
	 * 设置是否循环
	 */
	public void setSequenceCycle(java.lang.Integer sequenceCycle) {
		this.sequenceCycle = sequenceCycle;
	}

	/**
	 * 获取是否循环
	 */
	public java.lang.Integer getSequenceCycle() {
		return sequenceCycle;
	}

	/**
	 * 设置序列前缀
	 */
	public void setSequencePrefix(java.lang.String sequencePrefix) {
		this.sequencePrefix = sequencePrefix;
	}

	/**
	 * 获取序列前缀
	 */
	public java.lang.String getSequencePrefix() {
		return sequencePrefix;
	}
}
