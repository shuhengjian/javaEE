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
public class TsysCustomvalues extends BaseDomain{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "对自定义属性赋值";
	public static final String ALIAS_VALUEID = "valueid";
	public static final String ALIAS_CUSTOMIZED_ID = "customizedId";
	public static final String ALIAS_CUSTOM_FIELD_ID = "自定义字段";
	public static final String ALIAS_VALUE = "字段值";
	

    /**
     * valueid       db_column: VALUEID 
     */	
	private java.lang.String valueid;
    /**
     * customizedId       db_column: CUSTOMIZED_ID 
     */	
	private java.lang.String customizedId;
    /**
     * 自定义字段       db_column: CUSTOM_FIELD_ID 
     */	
	private java.lang.String customFieldId;
    /**
     * 字段值       db_column: VALUE 
     */	
	private java.lang.String value;
	//columns END

	public TsysCustomvalues(){
	}

	public TsysCustomvalues(
		java.lang.String valueid
	){
		this.valueid = valueid;
	}

	public void setValueid(java.lang.String value) {
		this.valueid = value;
	}
	
	public java.lang.String getValueid() {
		return this.valueid;
	}
	public void setCustomizedId(java.lang.String value) {
		this.customizedId = value;
	}
	
	public java.lang.String getCustomizedId() {
		return this.customizedId;
	}
	public void setCustomFieldId(java.lang.String value) {
		this.customFieldId = value;
	}
	
	public java.lang.String getCustomFieldId() {
		return this.customFieldId;
	}
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	
	public java.lang.String getValue() {
		return this.value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Valueid",getValueid())
			.append("CustomizedId",getCustomizedId())
			.append("CustomFieldId",getCustomFieldId())
			.append("Value",getValue())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getValueid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysCustomvalues == false) return false;
		if(this == obj) return true;
		TsysCustomvalues other = (TsysCustomvalues)obj;
		return new EqualsBuilder()
			.append(getValueid(),other.getValueid())
			.isEquals();
	}
}

