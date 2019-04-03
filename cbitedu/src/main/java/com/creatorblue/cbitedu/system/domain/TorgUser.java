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
public class TorgUser extends BaseDomain{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "单位用户关联表";
	public static final String ALIAS_ORGUSER_ID = "orguserId";
	public static final String ALIAS_ORG_ID = "机构ID";
	public static final String ALIAS_USER_ID = "用户ID";
	

    /**
     * orguserId       db_column: ORGUSER_ID 
     */	
	private java.lang.Long orguserId;
    /**
     * 机构ID       db_column: ORG_ID 
     */	
	private java.lang.String orgId;
    /**
     * 用户ID       db_column: USER_ID 
     */	
	private java.lang.String userId;
	//columns END

	public TorgUser(){
	}

	public TorgUser(
		java.lang.Long orguserId
	){
		this.orguserId = orguserId;
	}

	public void setOrguserId(java.lang.Long value) {
		this.orguserId = value;
	}
	
	public java.lang.Long getOrguserId() {
		return this.orguserId;
	}
	public void setOrgId(java.lang.String value) {
		this.orgId = value;
	}
	
	public java.lang.String getOrgId() {
		return this.orgId;
	}
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	private TsysUserinfo tsysUserinfo;
	
	public void setTsysUserinfo(TsysUserinfo tsysUserinfo){
		this.tsysUserinfo = tsysUserinfo;
	}
	
	public TsysUserinfo getTsysUserinfo() {
		return tsysUserinfo;
	}
	
	private TsysOrg tsysOrg;
	
	public void setTsysOrg(TsysOrg tsysOrg){
		this.tsysOrg = tsysOrg;
	}
	
	public TsysOrg getTsysOrg() {
		return tsysOrg;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("OrguserId",getOrguserId())
			.append("OrgId",getOrgId())
			.append("UserId",getUserId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOrguserId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TorgUser == false) return false;
		if(this == obj) return true;
		TorgUser other = (TorgUser)obj;
		return new EqualsBuilder()
			.append(getOrguserId(),other.getOrguserId())
			.isEquals();
	}
}

