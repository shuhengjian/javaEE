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

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.creatorblue.cbitedu.core.baseclass.domain.BaseDomain;
public class TsysModuleoperate extends BaseDomain{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "模块操作表";
	public static final String ALIAS_OPERATE_ID = "operateId";
	public static final String ALIAS_MODULE_ID = "moduleId";
	public static final String ALIAS_OPERATE_NAME = "操作名称";
	public static final String ALIAS_OPERATE_CODE = "操作编码,权限编码";
	

	private String moduleName;


	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
     * operateId       db_column: OPERATE_ID 
     */	
	private java.lang.String operateId;
    /**
     * moduleId       db_column: MODULE_ID 
     */	
	private java.lang.String moduleId;
    /**
     * 操作名称       db_column: OPERATE_NAME 
     */	
	private java.lang.String operateName;
    /**
     * 操作编码,权限编码       db_column: OPERATE_CODE 
     */	
	private java.lang.String operateCode;
	//columns END

	public TsysModuleoperate(){
	}

	public TsysModuleoperate(
		java.lang.String operateId
	){
		this.operateId = operateId;
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
	public void setOperateName(java.lang.String value) {
		this.operateName = value;
	}
	
	public java.lang.String getOperateName() {
		return this.operateName;
	}
	public void setOperateCode(java.lang.String value) {
		this.operateCode = value;
	}
	
	public java.lang.String getOperateCode() {
		return this.operateCode;
	}
	
	private Set tsysRoleprivileges = new HashSet(0);
	public void setTsysRoleprivileges(Set<TsysRoleprivilege> tsysRoleprivilege){
		this.tsysRoleprivileges = tsysRoleprivilege;
	}
	
	public Set<TsysRoleprivilege> getTsysRoleprivileges() {
		return tsysRoleprivileges;
	}
	
	private TsysModule tsysModule;
	
	public void setTsysModule(TsysModule tsysModule){
		this.tsysModule = tsysModule;
	}
	
	public TsysModule getTsysModule() {
		return tsysModule;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("OperateId",getOperateId())
			.append("ModuleId",getModuleId())
			.append("OperateName",getOperateName())
			.append("OperateCode",getOperateCode())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOperateId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysModuleoperate == false) return false;
		if(this == obj) return true;
		TsysModuleoperate other = (TsysModuleoperate)obj;
		return new EqualsBuilder()
			.append(getOperateId(),other.getOperateId())
			.isEquals();
	}
}

