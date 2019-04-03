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

public class TsysDict extends BaseDomain {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "TsysDict";
	public static final String ALIAS_DICT_ID = "dictId";
	public static final String ALIAS_DICT_TYPE = "字典分类";
	public static final String ALIAS_DICT_NAME = "字典分类名称";
	public static final String ALIAS_DICT_VALUE = "字典值";
	public static final String ALIAS_DICT_CODE = "字典编码";
	public static final String ALIAS_REMARK = "字典描述";
	public static final String ALIAS_DICT_SORT = "1、平台内2、平台外";
	public static final String ALIAS_SORT_NUM = "排序号";
	public static final String ALIAS_PARENT_TYPE = "父分类";
	public static final String ALIAS_DISPLAY_SORT = "字典展现分类:下拉类型(select)、树形(tree) 复选型(checkbox)单选radio";
	public static final String ALIAS_MULTI_TYPE = "单选或多选：主要针对树形控件";
	public static final String ALIAS_ORG_ID = "所属机构";

	/**
	 * dictId db_column: DICT_ID
	 */
	private java.lang.String dictId;
	/**
	 * 字典分类 db_column: DICT_TYPE
	 */
	private java.lang.String dictType;
	/**
	 * 字典分类名称 db_column: DICT_NAME
	 */
	private java.lang.String dictName;
	/**
	 * 字典值 db_column: DICT_VALUE
	 */
	private java.lang.String dictValue;
	/**
	 * 字典编码 db_column: DICT_CODE
	 */
	private java.lang.String dictCode;
	/**
	 * 字典描述 db_column: REMARK
	 */
	private java.lang.String remark;
	/**
	 * 1、平台内2、平台外 db_column: DICT_SORT
	 */
	private java.lang.String dictSort;
	/**
	 * 排序号 db_column: SORT_NUM
	 */
	private java.lang.Long sortNum;
	/**
	 * 父分类 db_column: PARENT_TYPE
	 */
	private java.lang.String parentType;
	/**
	 * 字典展现分类:下拉类型(select)、树形(tree) 复选型(checkbox)单选radio db_column: DISPLAY_SORT
	 */
	private java.lang.String displaySort;
	/**
	 * dictClass db_column: DICT_CLASS
	 */
	private java.lang.String isdefault;
	/**
	 * 单选或多选：主要针对树形控件 db_column: MULTI_TYPE
	 */
	private java.lang.String multiType;
	/**
	 * 所属机构 db_column: ORG_ID
	 */
	private java.lang.String orgId;


	// columns END

	public TsysDict() {
	}

	public TsysDict(java.lang.String dictId) {
		this.dictId = dictId;
	}

	public void setDictId(java.lang.String value) {
		this.dictId = value;
	}

	public java.lang.String getDictId() {
		return this.dictId;
	}

	public void setDictType(java.lang.String value) {
		this.dictType = value;
	}

	public java.lang.String getDictType() {
		return this.dictType;
	}

	public void setDictName(java.lang.String value) {
		this.dictName = value;
	}

	public java.lang.String getDictName() {
		return this.dictName;
	}

	public void setDictValue(java.lang.String value) {
		this.dictValue = value;
	}

	public java.lang.String getDictValue() {
		return this.dictValue;
	}

	public void setDictCode(java.lang.String value) {
		this.dictCode = value;
	}

	public java.lang.String getDictCode() {
		return this.dictCode;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setDictSort(java.lang.String value) {
		this.dictSort = value;
	}

	public java.lang.String getDictSort() {
		return this.dictSort;
	}

	public void setSortNum(java.lang.Long value) {
		this.sortNum = value;
	}

	public java.lang.Long getSortNum() {
		return this.sortNum;
	}

	public void setParentType(java.lang.String value) {
		this.parentType = value;
	}

	public java.lang.String getParentType() {
		return this.parentType;
	}

	public void setDisplaySort(java.lang.String value) {
		this.displaySort = value;
	}

	public java.lang.String getDisplaySort() {
		return this.displaySort;
	}


	public void setIsdefault(java.lang.String value) {
		this.isdefault = value;
	}

	public java.lang.String getIsdefault() {
		return this.isdefault;
	}

	public void setMultiType(java.lang.String value) {
		this.multiType = value;
	}

	public java.lang.String getMultiType() {
		return this.multiType;
	}

	public void setOrgId(java.lang.String value) {
		this.orgId = value;
	}

	public java.lang.String getOrgId() {
		return this.orgId;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("DictId", getDictId())
				.append("DictType", getDictType())
				.append("DictName", getDictName())
				.append("DictValue", getDictValue())
				.append("DictCode", getDictCode())
				.append("Remark", getRemark())
				.append("DictSort", getDictSort())
				.append("SortNum", getSortNum())
				.append("ParentType", getParentType())
				.append("DisplaySort", getDisplaySort())
				.append("isdefault", getIsdefault())
				.append("MultiType", getMultiType())
				.append("OrgId", getOrgId()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getDictId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TsysDict == false)
			return false;
		if (this == obj)
			return true;
		TsysDict other = (TsysDict) obj;
		return new EqualsBuilder().append(getDictId(), other.getDictId())
				.isEquals();
	}
}
