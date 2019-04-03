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
public class TsysCustomfields extends BaseDomain{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "系统扩展表:专门针对机构、部门、用户个性化需求";
	public static final String ALIAS_CUSTOMID = "customid";
	public static final String ALIAS_POSITION = "排序号";
	public static final String ALIAS_CUTOM_TYPE = "类型:以下拉列表的形式供选择,对应具体物理表即可";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_FIELD_FORMAT = "字段格式Text：文本Long text：长段文本Integer：整数Float：浮点数List：列表Date：日期Boolean：布尔量";
	public static final String ALIAS_MIN_LENGTH = "最小长度";
	public static final String ALIAS_MAX_LENGTH = "最大长度";
	public static final String ALIAS_IS_REQUIRED = "是否必填";
	public static final String ALIAS_DEFAULT_VALUE = "默认值";
	public static final String ALIAS_CUSTOM_REGEXP = "正则表达式:对新加的字段进行校验";
	

    /**
     * customid       db_column: CUSTOMID 
     */	
	private java.lang.String customid;
    /**
     * 排序号       db_column: POSITION 
     */	
	private java.lang.Long position;
    /**
     * 类型:以下拉列表的形式供选择,对应具体物理表即可       db_column: TYPE 
     */	
	private java.lang.String cutom_type;
    /**
     * 名称       db_column: NAME 
     */	
	private java.lang.String name;
    /**
     * 字段格式Text：文本Long text：长段文本Integer：整数Float：浮点数List：列表Date：日期Boolean：布尔量       db_column: FIELD_FORMAT 
     */	
	private java.lang.String fieldFormat;
    /**
     * 最小长度       db_column: MIN_LENGTH 
     */	
	private java.lang.String minLength;
    /**
     * 最大长度       db_column: MAX_LENGTH 
     */	
	private java.lang.String maxLength;
    /**
     * 是否必填       db_column: IS_REQUIRED 
     */	
	private java.lang.String isRequired;
    /**
     * 默认值       db_column: DEFAULT_VALUE 
     */	
	private java.lang.String defaultValue;
    /**
     * 正则表达式:对新加的字段进行校验       db_column: REGEXP 
     */	
	private java.lang.String custom_regexp;
	//columns END

	public TsysCustomfields(){
	}

	public TsysCustomfields(
		java.lang.String customid
	){
		this.customid = customid;
	}

	public void setCustomid(java.lang.String value) {
		this.customid = value;
	}
	
	public java.lang.String getCustomid() {
		return this.customid;
	}
	public void setPosition(java.lang.Long value) {
		this.position = value;
	}
	
	public java.lang.Long getPosition() {
		return this.position;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setFieldFormat(java.lang.String value) {
		this.fieldFormat = value;
	}
	
	public java.lang.String getFieldFormat() {
		return this.fieldFormat;
	}
	public void setMinLength(java.lang.String value) {
		this.minLength = value;
	}
	
	public java.lang.String getMinLength() {
		return this.minLength;
	}
	public void setMaxLength(java.lang.String value) {
		this.maxLength = value;
	}
	
	public java.lang.String getMaxLength() {
		return this.maxLength;
	}
	public void setIsRequired(java.lang.String value) {
		this.isRequired = value;
	}
	
	public java.lang.String getIsRequired() {
		return this.isRequired;
	}
	public void setDefaultValue(java.lang.String value) {
		this.defaultValue = value;
	}
	
	public java.lang.String getDefaultValue() {
		return this.defaultValue;
	}

	public java.lang.String getCutom_type() {
		return cutom_type;
	}

	public void setCutom_type(java.lang.String cutom_type) {
		this.cutom_type = cutom_type;
	}

	public java.lang.String getCustom_regexp() {
		return custom_regexp;
	}

	public void setCustom_regexp(java.lang.String custom_regexp) {
		this.custom_regexp = custom_regexp;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Customid",getCustomid())
			.append("Position",getPosition())
			.append("Type",getCutom_type())
			.append("Name",getName())
			.append("FieldFormat",getFieldFormat())
			.append("MinLength",getMinLength())
			.append("MaxLength",getMaxLength())
			.append("IsRequired",getIsRequired())
			.append("DefaultValue",getDefaultValue())
			.append("Regexp",getCustom_regexp())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCustomid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysCustomfields == false) return false;
		if(this == obj) return true;
		TsysCustomfields other = (TsysCustomfields)obj;
		return new EqualsBuilder()
			.append(getCustomid(),other.getCustomid())
			.isEquals();
	}
}

