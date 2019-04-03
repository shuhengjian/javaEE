/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

package com.creatorblue.cbitedu.system.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.creatorblue.cbitedu.core.baseclass.domain.BaseDomain;


public class TsysParameter extends BaseDomain {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "TsysParameter";
	public static final String ALIAS_PARAID = "paraid";
	public static final String ALIAS_ISDEFAULT = "是否默认";
	public static final String ALIAS_PARANAME = "参数名称";
	public static final String ALIAS_PARANO = "参数编码";
	public static final String ALIAS_PARA_CLASS = "系统级、自定义级";
	public static final String ALIAS_PARA_KEY = "字典值";
	public static final String ALIAS_PARA_TYPE = "字典类型";
	public static final String ALIAS_PARAORDER = "排序号";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_PARENTPARAID = "父类型";
	public static final String ALIAS_DISPLAYSORT = "显示方式：树、下拉、复选、单选字典中获取";

	/**
	 * paraid db_column: PARAID
	 */
	private java.lang.String paraid;
	/**
	 * 是否默认 db_column: ISDEFAULT
	 */
	private java.lang.String isdefault;
	/**
	 * 参数名称 db_column: PARANAME
	 */
	private java.lang.String paraname;
	/**
	 * 参数编码 db_column: PARANO
	 */
	private java.lang.String parano;
	/**
	 * 系统级、自定义级 db_column: PARA_CLASS
	 */
	private java.lang.String paraClass;
	/**
	 * 字典值 db_column: PARA_KEY
	 */
	private java.lang.String paraKey;
	/**
	 * 字典类型 db_column: PARA_TYPE
	 */
	private java.lang.String paraType;
	/**
	 * 排序号 db_column: PARA_ORDER
	 */
	private java.lang.Long paraorder;
	/**
	 * 备注 db_column: REMARK
	 */
	private java.lang.String remark;
	/**
	 * 父类型 db_column: PARENTPARAID
	 */
	private java.lang.String parentparaid;
	/**
	 * 显示方式：树、下拉、复选、单选字典中获取 db_column: DISPLAYSORT
	 */
	private java.lang.String displaysort;

	// columns END

	public TsysParameter() {
	}

	public TsysParameter(java.lang.String paraid) {
		this.paraid = paraid;
	}


	public void setParaid(java.lang.String value) {
		this.paraid = value;
	}

	public java.lang.String getParaid() {
		return this.paraid;
	}

	public void setIsdefault(java.lang.String value) {
		this.isdefault = value;
	}

	public java.lang.String getIsdefault() {
		return this.isdefault;
	}

	public void setParaname(java.lang.String value) {
		this.paraname = value;
	}

	public java.lang.String getParaname() {
		return this.paraname;
	}

	public void setParano(java.lang.String value) {
		this.parano = value;
	}

	public java.lang.String getParano() {
		return this.parano;
	}

	public void setParaClass(java.lang.String value) {
		this.paraClass = value;
	}

	public java.lang.String getParaClass() {
		return this.paraClass;
	}

	public void setParaKey(java.lang.String value) {
		this.paraKey = value;
	}

	public java.lang.String getParaKey() {
		return this.paraKey;
	}

	public void setParaType(java.lang.String value) {
		this.paraType = value;
	}

	public java.lang.String getParaType() {
		return this.paraType;
	}

	public void setParaorder(java.lang.Long value) {
		this.paraorder = value;
	}

	public java.lang.Long getParaorder() {
		return this.paraorder;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setParentparaid(java.lang.String value) {
		this.parentparaid = value;
	}

	public java.lang.String getParentparaid() {
		return this.parentparaid;
	}

	public void setDisplaysort(java.lang.String value) {
		this.displaysort = value;
	}

	public java.lang.String getDisplaysort() {
		return this.displaysort;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("Paraid", getParaid())
				.append("Isdefault", getIsdefault())
				.append("Paraname", getParaname())
				.append("Parano", getParano())
				.append("ParaClass", getParaClass())
				.append("ParaKey", getParaKey())
				.append("ParaType", getParaType())
				.append("Paraorder", getParaorder())
				.append("Remark", getRemark())
				.append("Parentparaid", getParentparaid())
				.append("Displaysort", getDisplaysort()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getParaid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TsysParameter == false)
			return false;
		if (this == obj)
			return true;
		TsysParameter other = (TsysParameter) obj;
		return new EqualsBuilder().append(getParaid(), other.getParaid())
				.isEquals();
	}
}
