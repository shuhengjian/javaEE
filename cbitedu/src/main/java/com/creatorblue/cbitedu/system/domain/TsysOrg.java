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
public class TsysOrg extends BaseDomain{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "机构表";
	public static final String ALIAS_ORG_ID = "机构ID";
	public static final String ALIAS_ORG_CODE = "机构编号";
	public static final String ALIAS_ORG_SN = "机构排序ID";
	public static final String ALIAS_ORG_NAME = "机构名称";
	public static final String ALIAS_PARENT_ID = "父机构ID";
	public static final String ALIAS_LAYER = "层（阶次）";
	public static final String ALIAS_CREATE_DATE = "创建时间";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_REMARK = "单位简介";
	public static final String ALIAS_ORG_TYPE = "机构类型:1、部门 2、公司";
	public static final String ALIAS_ADDR = "通讯地址";
	public static final String ALIAS_ZIP = "邮政编码";
	public static final String ALIAS_EMAIL = "电子邮箱";
	public static final String ALIAS_LEADER = "机构负责人";
	public static final String ALIAS_PHONE = "办公电话";
	public static final String ALIAS_FAX = "传真号码";
	public static final String ALIAS_STATE = "状态:1有效 2、停用";
	public static final String ALIAS_MOBILE = "负责人手机号码";
	public static final String ALIAS_ANCESTRY = "祖谱";
	

    /**
     * 机构ID       db_column: ORG_ID 
     */	
	private java.lang.String orgId;
   
	 /**
     * 机构编号       db_column: ORG_CODE 
     */	
	private java.lang.String orgCode;
    public java.lang.String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(java.lang.String orgCode) {
		this.orgCode = orgCode;
	}

	/**
     * 机构排序ID       db_column: ORG_SN 
     */	
	private java.lang.Long orgSn;
    /**
     * 机构名称       db_column: ORG_NAME 
     */	
	private java.lang.String orgName;
    /**
     * 父机构ID       db_column: PARENT_ID 
     */	
	private java.lang.String parentId;
    /**
     * 层（阶次）       db_column: LAYER 
     */	
	private java.lang.Long layer;
    /**
     * 创建时间       db_column: CREATE_DATE 
     */	
	private java.lang.String createDate;
    /**
     * 创建人       db_column: CREATOR 
     */	
	private java.lang.String creator;
    /**
     * 单位简介       db_column: REMARK 
     */	
	private java.lang.String remark;
    /**
     * 机构类型:1、部门 2、公司       db_column: ORG_TYPE 
     */	
	private java.lang.String orgType;
    /**
     * 通讯地址       db_column: ADDR 
     */	
	private java.lang.String addr;
    /**
     * 邮政编码       db_column: ZIP 
     */	
	private java.lang.String zip;
    /**
     * 电子邮箱       db_column: EMAIL 
     */	
	private java.lang.String email;
    /**
     * 机构负责人       db_column: LEADER 
     */	
	private java.lang.String leader;
    /**
     * 办公电话       db_column: PHONE 
     */	
	private java.lang.String phone;
    /**
     * 传真号码       db_column: FAX 
     */	
	private java.lang.String fax;
    /**
     * 状态:1有效 2、停用       db_column: STATE 
     */	
	private java.lang.String state;
    /**
     * 负责人手机号码       db_column: MOBILE 
     */	
	private java.lang.String mobile;
	
    public java.lang.String getAncestry() {
		return ancestry;
	}

	public void setAncestry(java.lang.String ancestry) {
		this.ancestry = ancestry;
	}

	/**
     * 祖谱（子孙保存字段）       db_column: ANCESTRY 
     */	
	private java.lang.String ancestry;
	/*
	public java.lang.String getRownum_() {
		return rownum_;
	}

	public void setRownum_(java.lang.String rownum_) {
		this.rownum_ = rownum_;
	}*/

	//private java.lang.String rownum_;
	//columns END

	public TsysOrg(){
	}

	public TsysOrg(
		java.lang.String orgId
	){
		this.orgId = orgId;
	}

	public void setOrgId(java.lang.String value) {
		this.orgId = value;
	}
	
	public java.lang.String getOrgId() {
		return this.orgId;
	}

	public void setOrgSn(java.lang.Long value) {
		this.orgSn = value;
	}
	
	public java.lang.Long getOrgSn() {
		return this.orgSn;
	}
	public void setOrgName(java.lang.String value) {
		this.orgName = value;
	}
	
	public java.lang.String getOrgName() {
		return this.orgName;
	}
	public void setParentId(java.lang.String value) {
		this.parentId = value;
	}
	
	public java.lang.String getParentId() {
		return this.parentId;
	}
	public void setLayer(java.lang.Long value) {
		this.layer = value;
	}
	
	public java.lang.Long getLayer() {
		return this.layer;
	}
	public void setCreateDate(java.lang.String value) {
		this.createDate = value;
	}
	
	public java.lang.String getCreateDate() {
		return this.createDate;
	}
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setOrgType(java.lang.String value) {
		this.orgType = value;
	}
	
	public java.lang.String getOrgType() {
		return this.orgType;
	}
	public void setAddr(java.lang.String value) {
		this.addr = value;
	}
	
	public java.lang.String getAddr() {
		return this.addr;
	}
	public void setZip(java.lang.String value) {
		this.zip = value;
	}
	
	public java.lang.String getZip() {
		return this.zip;
	}
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	public void setLeader(java.lang.String value) {
		this.leader = value;
	}
	
	public java.lang.String getLeader() {
		return this.leader;
	}
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
	public void setFax(java.lang.String value) {
		this.fax = value;
	}
	
	public java.lang.String getFax() {
		return this.fax;
	}
	public void setState(java.lang.String value) {
		this.state = value;
	}
	
	public java.lang.String getState() {
		return this.state;
	}
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	private Set torgUsers = new HashSet(0);
	public void setTorgUsers(Set<TorgUser> torgUser){
		this.torgUsers = torgUser;
	}
	
	public Set<TorgUser> getTorgUsers() {
		return torgUsers;
	}
	
	private Set tsysDataprivileges = new HashSet(0);
	public void setTsysDataprivileges(Set<TsysDataprivilege> tsysDataprivilege){
		this.tsysDataprivileges = tsysDataprivilege;
	}
	
	public Set<TsysDataprivilege> getTsysDataprivileges() {
		return tsysDataprivileges;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("OrgId",getOrgId())
			.append("OrgSn",getOrgSn())
			.append("OrgName",getOrgName())
			.append("ParentId",getParentId())
			.append("Layer",getLayer())
			.append("CreateDate",getCreateDate())
			.append("Creator",getCreator())
			.append("Remark",getRemark())
			.append("OrgType",getOrgType())
			.append("Addr",getAddr())
			.append("Zip",getZip())
			.append("Email",getEmail())
			.append("Leader",getLeader())
			.append("Phone",getPhone())
			.append("Fax",getFax())
			.append("State",getState())
			.append("Mobile",getMobile())
			.append("OrgCode",getOrgCode())
			.append("Ancestry",getAncestry())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOrgId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysOrg == false) return false;
		if(this == obj) return true;
		TsysOrg other = (TsysOrg)obj;
		return new EqualsBuilder()
			.append(getOrgId(),other.getOrgId())
			.isEquals();
	}
}

