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
public class TuserRole extends BaseDomain{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "用户角色关联表";
	public static final String ALIAS_USER_ROLEID = "userRoleid";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_ROLE_ID = "所属角色";
	

    /**
     * userRoleid       db_column: USER_ROLEID 
     */	
	private java.lang.String userRoleid;
    /**
     * 用户ID       db_column: USER_ID 
     */	
	private java.lang.String userId;
    /**
     * 所属角色       db_column: ROLE_ID 
     */	
	private java.lang.String roleId;
	//columns END

	public TuserRole(){
	}

	public TuserRole(
		java.lang.String userRoleid
	){
		this.userRoleid = userRoleid;
	}

	public void setUserRoleid(java.lang.String value) {
		this.userRoleid = value;
	}
	
	public java.lang.String getUserRoleid() {
		return this.userRoleid;
	}
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}
	
	public java.lang.String getRoleId() {
		return this.roleId;
	}
	
	private TsysUserinfo tsysUserinfo;
	
	public void setTsysUserinfo(TsysUserinfo tsysUserinfo){
		this.tsysUserinfo = tsysUserinfo;
	}
	
	public TsysUserinfo getTsysUserinfo() {
		return tsysUserinfo;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserRoleid",getUserRoleid())
			.append("UserId",getUserId())
			.append("RoleId",getRoleId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserRoleid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TuserRole == false) return false;
		if(this == obj) return true;
		TuserRole other = (TuserRole)obj;
		return new EqualsBuilder()
			.append(getUserRoleid(),other.getUserRoleid())
			.isEquals();
	}
}

