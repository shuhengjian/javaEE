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
public class TsysRoleprivilege extends BaseDomain{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "角色权限表";
	public static final String ALIAS_PRIVILEGE_ID = "privilegeId";
	public static final String ALIAS_ROLE_ID = "所属角色";
	public static final String ALIAS_OPERATE_ID = "模块操作";
	public static final String ALIAS_MODULE_ID = "模块";
	

    /**
     * privilegeId       db_column: PRIVILEGE_ID 
     */	
	private java.lang.String privilegeId;
    /**
     * 所属角色       db_column: ROLE_ID 
     */	
	private java.lang.String roleId;
    /**
     * 模块操作       db_column: OPERATE_ID 
     */	
	private java.lang.String operateId;
    /**
     * 模块       db_column: MODULE_ID 
     */	
	private java.lang.String moduleId;
	//columns END

	public TsysRoleprivilege(){
	}

	public TsysRoleprivilege(
		java.lang.String privilegeId
	){
		this.privilegeId = privilegeId;
	}

	public void setPrivilegeId(java.lang.String value) {
		this.privilegeId = value;
	}
	
	public java.lang.String getPrivilegeId() {
		return this.privilegeId;
	}
	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}
	
	public java.lang.String getRoleId() {
		return this.roleId;
	}
	public void setOperateId(java.lang.String value) {
		this.operateId = value;
	}
	
	public java.lang.String getOperateId() {
		return this.operateId;
	}
	public void setModuleId(java.lang.String value) {
		this.moduleId = value;
	}
	
	public java.lang.String getModuleId() {
		return this.moduleId;
	}
	
	private TsysRole tsysRole;
	
	public void setTsysRole(TsysRole tsysRole){
		this.tsysRole = tsysRole;
	}
	
	public TsysRole getTsysRole() {
		return tsysRole;
	}
	
	private TsysModuleoperate tsysModuleoperate;
	
	public void setTsysModuleoperate(TsysModuleoperate tsysModuleoperate){
		this.tsysModuleoperate = tsysModuleoperate;
	}
	
	public TsysModuleoperate getTsysModuleoperate() {
		return tsysModuleoperate;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("PrivilegeId",getPrivilegeId())
			.append("RoleId",getRoleId())
			.append("OperateId",getOperateId())
			.append("ModuleId",getModuleId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPrivilegeId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysRoleprivilege == false) return false;
		if(this == obj) return true;
		TsysRoleprivilege other = (TsysRoleprivilege)obj;
		return new EqualsBuilder()
			.append(getPrivilegeId(),other.getPrivilegeId())
			.isEquals();
	}
}

