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
import com.creatorblue.cbitedu.core.page.Page;
public class TsysDataprivilege extends BaseDomain{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "TsysDataprivilege";
	public static final String ALIAS_ROLE_ORGID = "roleOrgid";
	public static final String ALIAS_ORG_ID = "角色授权数据权限时,保存机构ID";
	public static final String ALIAS_ROLE_ID = "roleId";
	public static final String ALIAS_USER_ID = "用户ID";
	

    /**
     * roleOrgid       db_column: ROLE_ORGID 
     */	
	private java.lang.String roleOrgid;
    /**
     * 角色授权数据权限时,保存机构ID       db_column: ORG_ID 
     */	
	private java.lang.String orgId;
    /**
     * roleId       db_column: ROLE_ID 
     */	
	private java.lang.String roleId;
    /**
     * 用户ID       db_column: USER_ID 
     */	
	private java.lang.String userId;
	//columns END

	private Page page;
	
	public TsysDataprivilege(){
	}

	public TsysDataprivilege(
		java.lang.String roleOrgid
	){
		this.roleOrgid = roleOrgid;
	}

	public void setRoleOrgid(java.lang.String value) {
		this.roleOrgid = value;
	}
	
	public java.lang.String getRoleOrgid() {
		return this.roleOrgid;
	}
	public void setOrgId(java.lang.String value) {
		this.orgId = value;
	}
	
	public java.lang.String getOrgId() {
		return this.orgId;
	}
	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}
	
	public java.lang.String getRoleId() {
		return this.roleId;
	}
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	private TsysRole tsysRole;
	
	public void setTsysRole(TsysRole tsysRole){
		this.tsysRole = tsysRole;
	}
	
	public TsysRole getTsysRole() {
		return tsysRole;
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
			.append("RoleOrgid",getRoleOrgid())
			.append("OrgId",getOrgId())
			.append("RoleId",getRoleId())
			.append("UserId",getUserId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRoleOrgid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysDataprivilege == false) return false;
		if(this == obj) return true;
		TsysDataprivilege other = (TsysDataprivilege)obj;
		return new EqualsBuilder()
			.append(getRoleOrgid(),other.getRoleOrgid())
			.isEquals();
	}

}

