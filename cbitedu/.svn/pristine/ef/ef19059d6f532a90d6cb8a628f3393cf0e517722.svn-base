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
public class TsysModule extends BaseDomain{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "模块(菜单)表";
	public static final String ALIAS_MODULE_ID = "moduleId";
	public static final String ALIAS_APP_ID = "所属子系统";
	public static final String ALIAS_MODULE_NAME = "模块名称";
	public static final String ALIAS_LINK_ADDR = "链接地址";
	public static final String ALIAS_MODULE_CLASS = "模块级别";
	public static final String ALIAS_ICON = "模块图标";
	public static final String ALIAS_MODULE_CODE = "模块编码";
	public static final String ALIAS_PARENT_MODULEID = "父模块";
	public static final String ALIAS_SORT_NUM = "排序号";
	public static final String ALIAS_REMARK = "模块描述";
	public static final String ALIAS_DISPLAY = "是否在菜单中显示";
	

	private String appName;
	private String parentModuleName;
    public String getParentModuleName() {
		return parentModuleName;
	}

	public void setParentModuleName(String parentModuleName) {
		this.parentModuleName = parentModuleName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
     * moduleId       db_column: MODULE_ID 
     */	
	private java.lang.String moduleId;
    /**
     * 所属子系统       db_column: APP_ID 
     */	
	private java.lang.String appId;
    /**
     * 模块名称       db_column: MODULE_NAME 
     */	
	private java.lang.String moduleName;
    /**
     * 链接地址       db_column: LINK_ADDR 
     */	
	private java.lang.String linkAddr;
    /**
     * 模块级别       db_column: MODULE_CLASS 
     */	
	private java.lang.Long moduleClass;
    /**
     * 模块图标       db_column: ICON 
     */	
	private java.lang.String icon;
    /**
     * 模块编码       db_column: MODULE_CODE 
     */	
	private java.lang.String moduleCode;
    /**
     * 父模块       db_column: PARENT_MODULEID 
     */	
	private java.lang.String parentModuleid;
    /**
     * 排序号       db_column: SORT_NUM 
     */	
	private java.lang.Long sortNum;
    /**
     * 模块描述       db_column: REMARK 
     */	
	private java.lang.String remark;
    /**
     * 是否在菜单中显示       db_column: DISPLAY 
     */	
	private java.lang.String display;
	//columns END

	public TsysModule(){
	}

	public TsysModule(
		java.lang.String moduleId
	){
		this.moduleId = moduleId;
	}

	public void setModuleId(java.lang.String value) {
		this.moduleId = value;
	}
	
	public java.lang.String getModuleId() {
		return this.moduleId;
	}
	public void setAppId(java.lang.String value) {
		this.appId = value;
	}
	
	public java.lang.String getAppId() {
		return this.appId;
	}
	public void setModuleName(java.lang.String value) {
		this.moduleName = value;
	}
	
	public java.lang.String getModuleName() {
		return this.moduleName;
	}
	public void setLinkAddr(java.lang.String value) {
		this.linkAddr = value;
	}
	
	public java.lang.String getLinkAddr() {
		return this.linkAddr;
	}
	public void setModuleClass(java.lang.Long value) {
		this.moduleClass = value;
	}
	
	public java.lang.Long getModuleClass() {
		return this.moduleClass;
	}
	public void setIcon(java.lang.String value) {
		this.icon = value;
	}
	
	public java.lang.String getIcon() {
		return this.icon;
	}
	public void setModuleCode(java.lang.String value) {
		this.moduleCode = value;
	}
	
	public java.lang.String getModuleCode() {
		return this.moduleCode;
	}
	public void setParentModuleid(java.lang.String value) {
		this.parentModuleid = value;
	}
	
	public java.lang.String getParentModuleid() {
		return this.parentModuleid;
	}
	public void setSortNum(java.lang.Long value) {
		this.sortNum = value;
	}
	
	public java.lang.Long getSortNum() {
		return this.sortNum;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setDisplay(java.lang.String value) {
		this.display = value;
	}
	
	public java.lang.String getDisplay() {
		return this.display;
	}
	
	private Set tsysModuleoperates = new HashSet(0);
	public void setTsysModuleoperates(Set<TsysModuleoperate> tsysModuleoperate){
		this.tsysModuleoperates = tsysModuleoperate;
	}
	
	public Set<TsysModuleoperate> getTsysModuleoperates() {
		return tsysModuleoperates;
	}
	
	private TsysApp tsysApp;
	
	public void setTsysApp(TsysApp tsysApp){
		this.tsysApp = tsysApp;
	}
	
	public TsysApp getTsysApp() {
		return tsysApp;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ModuleId",getModuleId())
			.append("AppId",getAppId())
			.append("ModuleName",getModuleName())
			.append("LinkAddr",getLinkAddr())
			.append("ModuleClass",getModuleClass())
			.append("Icon",getIcon())
			.append("ModuleCode",getModuleCode())
			.append("ParentModuleid",getParentModuleid())
			.append("SortNum",getSortNum())
			.append("Remark",getRemark())
			.append("Display",getDisplay())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getModuleId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysModule == false) return false;
		if(this == obj) return true;
		TsysModule other = (TsysModule)obj;
		return new EqualsBuilder()
			.append(getModuleId(),other.getModuleId())
			.isEquals();
	}
}

