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
import com.creatorblue.cbitedu.core.taglibs.prametertag.ParamUtil;
import com.creatorblue.cbitedu.core.utils.SpringContextHolder;
import com.creatorblue.cbitedu.system.service.TsysOrgService;
import com.creatorblue.cbitedu.system.service.TsysUserinfoService;

public class TsysRole extends BaseDomain {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "角色表";
	public static final String ALIAS_ROLE_ID = "roleId";
	public static final String ALIAS_ROLE_NAME = "角色名称";
	public static final String ALIAS_ROLE_TYPE = "角色类型:1、公用、2、私用";
	public static final String ALIAS_REMARK = "角色描述";
	public static final String ALIAS_ORG_ID = "所属机构";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATE_DATE = "创建时间";
	public static final String ALIAS_DATA_SCOPE = "数据范围:01所有数据 02所在机构及以下数据; 03本级数据 04自定义明细 05所在部门数据06所在部门及以下数据 07本人数据";

	/**
	 * roleId db_column: ROLE_ID
	 */
	private java.lang.String roleId;

	/**
	 * 角色名称 db_column: ROLE_NAME
	 */
	private java.lang.String roleName;

	/**
	 * 角色类型:1、公用、2、私用 db_column: ROLE_TYPE
	 */
	private java.lang.String roleType;

	/**
	 * 角色描述 db_column: REMARK
	 */
	private java.lang.String remark;

	/**
	 * 所属机构 db_column: ORG_ID
	 */
	private java.lang.String orgId;
	private TsysOrg org;

	/**
	 * 创建人 db_column: CREATOR
	 */
	private java.lang.String creator;
	private TsysUserinfo userinfo;

	/**
	 * 创建时间 db_column: CREATE_DATE
	 */
	private java.lang.String createDate;

	/**
	 * 数据范围:01所有数据 02所在机构及以下数据; 03本级数据 04自定义明细 05所在部门数据06所在部门及以下数据 07本人数据
	 * db_column: DATA_SCOPE
	 */
	private java.lang.String dataScope;
	private Set tsysDataprivileges = new HashSet(0);
	private Set tsysRoleprivileges = new HashSet(0);

	// columns END
	public TsysRole() {
	}

	public TsysRole(java.lang.String roleId) {
		this.roleId = roleId;
	}

	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}

	public java.lang.String getRoleId() {
		return this.roleId;
	}

	public void setRoleName(java.lang.String value) {
		this.roleName = value;
	}

	public java.lang.String getRoleName() {
		return this.roleName;
	}

	public void setRoleType(java.lang.String value) {
		this.roleType = value;
	}

	public java.lang.String getRoleType() {
		return this.roleType;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setOrgId(java.lang.String value) {
		this.orgId = value;
	}

	public java.lang.String getOrgId() {
		return this.orgId;
	}

	public void setCreator(java.lang.String value) {
		this.creator = value;
	}

	public java.lang.String getCreator() {
		return this.creator;
	}

	public void setCreateDate(java.lang.String value) {
		this.createDate = value;
	}

	public java.lang.String getCreateDate() {
		return this.createDate;
	}

	public void setDataScope(java.lang.String value) {
		this.dataScope = value;
	}

	public java.lang.String getDataScope() {
		return this.dataScope;
	}

	public void setTsysDataprivileges(Set<TsysDataprivilege> tsysDataprivilege) {
		this.tsysDataprivileges = tsysDataprivilege;
	}

	public Set<TsysDataprivilege> getTsysDataprivileges() {
		return tsysDataprivileges;
	}

	public void setTsysRoleprivileges(Set<TsysRoleprivilege> tsysRoleprivilege) {
		this.tsysRoleprivileges = tsysRoleprivilege;
	}

	public Set<TsysRoleprivilege> getTsysRoleprivileges() {
		return tsysRoleprivileges;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("RoleId", getRoleId())
				.append("RoleName", getRoleName())
				.append("RoleType", getRoleType())
				.append("Remark", getRemark()).append("OrgId", getOrgId())
				.append("Creator", getCreator())
				.append("CreateDate", getCreateDate())
				.append("DataScope", getDataScope()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getRoleId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TsysRole == false) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		TsysRole other = (TsysRole) obj;

		return new EqualsBuilder().append(getRoleId(), other.getRoleId())
				.isEquals();
	}

	public TsysOrg getOrg() { // 当前的这个类，没有交给spring来管理，所以使用要手动的TsysOrgService注入到当前类中
		TsysOrg org = (TsysOrg) SpringContextHolder.getBean(
				TsysOrgService.class).selectDetailById(this.orgId);
		return org;
	}

	public void setOrg(TsysOrg org) {
		this.org = org;
	}

	public TsysUserinfo getUserinfo() {
		TsysUserinfoService<TsysUserinfo> tsysUserinfoService = SpringContextHolder
				.getBean(TsysUserinfoService.class);// 自动注入
		TsysUserinfo info = (TsysUserinfo) tsysUserinfoService
				.selectDetailById(this.creator);

		return info;
	}

	public void setUserinfo(TsysUserinfo userinfo) {
		this.userinfo = userinfo;
	}

	public java.lang.String getDataScopeName() {
		return SpringContextHolder.getBean(ParamUtil.class).getKeyByVal(
				"DATA_SCOPE", this.dataScope);
	}

}
