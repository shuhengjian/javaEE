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
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.creatorblue.cbitedu.core.baseclass.domain.BaseDomain;
import com.creatorblue.cbitedu.core.plugins.poi.excel.annotation.Excel;
import com.creatorblue.cbitedu.core.plugins.poi.excel.annotation.ExcelTarget;
/*
 * Excel导出注解
 */
@SuppressWarnings("serial")
@ExcelTarget("tsysUserinfo")
public class TsysUserinfo extends BaseDomain {
    private static final long serialVersionUID = 5454155825314635342L;

    //alias
    public static final String TABLE_ALIAS = "用户表";
    public static final String ALIAS_USER_ID = "用户ID";
    public static final String ALIAS_LOGIN_NAME = "登录账号";
    public static final String ALIAS_PASSWORD = "登录密码";
    public static final String ALIAS_USER_REALNAME = "姓名";
    public static final String ALIAS_USER_NO = "userNo";
    public static final String ALIAS_ORG_ID = "所属机构";
    public static final String ALIAS_DEPT_ID = "deptId";
    public static final String ALIAS_SEX = "性别";
    public static final String ALIAS_MOBILETEL = "手机";
    public static final String ALIAS_FAX = "传真";
    public static final String ALIAS_EMAIL = "电子邮箱";
    public static final String ALIAS_ZIP = "邮编";
    public static final String ALIAS_STATE = "状态1:启用 0停用、2、调岗 ";
    public static final String ALIAS_USER_LOGINCOUNT = "登陆次数";
    public static final String ALIAS_USER_TYPE = "用户类型";
    public static final String ALIAS_DREDGE_TIME = "开通时间";
    public static final String ALIAS_SORT_NUM = "排序号";
    public static final String ALIAS_CREATE_MAN = "创建人";
    public static final String ALIAS_LOGIN_IP = "最后登录IP";
    public static final String ALIAS_LOGIN_DATE = "最后登录时间";
    public static final String ALIAS_CREATE_DATE = "创建时间";
    private java.lang.String userpost;

    /**
    * 用户ID       db_column: USER_ID
    */
    private java.lang.String userId;

    /**
     * 登录账号       db_column: LOGIN_NAME
     */
    @Excel(name = "登录名称",code="loginName", orderNum = "1",align="left",width=10)
    private java.lang.String loginName;

    /**
     * 登录密码       db_column: PASSWORD
     */
    private java.lang.String password;

    /**
     * 姓名       db_column: USER_REALNAME
     */
    @Excel(name = "用户实名", code="userRealname",align="left",orderNum = "2",width=20)
    private java.lang.String userRealname;

    /**
     * userNo       db_column: USER_NO
     */
    private java.lang.String userNo;

    /**
     * 所属机构       db_column: ORG_ID
     */
    @Excel(name = "所属机构", code="orgId",orderNum = "3",align="right")
    private java.lang.String orgId;

    /**
     * deptId       db_column: DEPT_ID
     */
    private java.lang.String deptId;

    /**
     * 性别       db_column: SEX
     */
    @Excel(name = "姓别", code="sex",orderNum = "4")
    private java.lang.String sex;

    /**
     * 手机       db_column: MOBILETEL
     */
    @Excel(name = "手机号", align="right",code="mobiletel",dataformat="0",orderNum = "5")
    private java.lang.String mobiletel;

    /**
     * 传真       db_column: FAX
     */
    @Excel(name = "传真号码",code="fax", orderNum = "6")
    private java.lang.String fax;

    /**
     * 电子邮箱       db_column: EMAIL
     */
    @Excel(name = "电子邮箱",code="email", orderNum = "7")
    private java.lang.String email;

    /**
     * 邮编       db_column: ZIP
     */
    private java.lang.String zip;

    /**
     * 状态1:启用 0停用、2、调岗        db_column: STATE
     */
    private java.lang.String state;

    /**
     * 登陆次数       db_column: USER_LOGINCOUNT
     */
    private java.lang.Long userLogincount;

    /**
     * 用户类型       db_column: USER_TYPE
     */
    private java.lang.String userType;

    /**
     * 开通时间       db_column: DREDGE_TIME
     */
    
    private java.lang.String dredgeTime;

    /**
     * 排序号       db_column: SORT_NUM
     */
    private java.lang.Long sortNum;

    /**
     * 创建人       db_column: CREATE_MAN
     */
    private java.lang.String createMan;

    /**
     * 最后登录IP       db_column: LOGIN_IP
     */
    private java.lang.String loginIp;

    /**
     * 最后登录时间       db_column: LOGIN_DATE
     */
    private java.lang.String loginDate;

    /**
     * 创建时间       db_column: CREATE_DATE
     */
    @Excel(name = "创建时间 ",code="createDate", orderNum = "8")
    private java.lang.String createDate;

    /**
     * 用户的角色
     */
    private List<TsysRole> roleList;
    
    private String postName;
    private String postId;

    /**
     * 用户的部门
     */
    private List<TsysOrg> orgList;
    private TsysPost sysPost;
    private TuserPost userPost;
    private Set torgUsers = new HashSet(0);
    private Set tsysDataprivileges = new HashSet(0);
    private Set tuserPosts = new HashSet(0);

    //columns END
    public TsysUserinfo() {
    }

    public TsysUserinfo(java.lang.String userId) {
        this.userId = userId;
    }


    /*
     * 用户岗位
     * */
    public java.lang.String getUserpost() {
        return userpost;
    }

    public void setUserpost(java.lang.String userpost) {
        this.userpost = userpost;
    }

    public void setUserId(java.lang.String value) {
        this.userId = value;
    }

    public java.lang.String getUserId() {
        return this.userId;
    }

    public void setLoginName(java.lang.String value) {
        this.loginName = value;
    }

    public java.lang.String getLoginName() {
        return this.loginName;
    }

    public void setPassword(java.lang.String value) {
        this.password = value;
    }

    public java.lang.String getPassword() {
        return this.password;
    }

    public void setUserRealname(java.lang.String value) {
        this.userRealname = value;
    }

    public java.lang.String getUserRealname() {
        return this.userRealname;
    }

    public void setUserNo(java.lang.String value) {
        this.userNo = value;
    }

    public java.lang.String getUserNo() {
        return this.userNo;
    }

    public void setOrgId(java.lang.String value) {
        this.orgId = value;
    }

    public java.lang.String getOrgId() {
        return this.orgId;
    }

    public void setDeptId(java.lang.String value) {
        this.deptId = value;
    }

    public java.lang.String getDeptId() {
        return this.deptId;
    }

    public void setSex(java.lang.String value) {
        this.sex = value;
    }

    public java.lang.String getSex() {
        return this.sex;
    }

    public void setMobiletel(java.lang.String value) {
        this.mobiletel = value;
    }

    public java.lang.String getMobiletel() {
        return this.mobiletel;
    }

    public void setFax(java.lang.String value) {
        this.fax = value;
    }

    public java.lang.String getFax() {
        return this.fax;
    }

    public void setEmail(java.lang.String value) {
        this.email = value;
    }

    public java.lang.String getEmail() {
        return this.email;
    }

    public void setZip(java.lang.String value) {
        this.zip = value;
    }

    public java.lang.String getZip() {
        return this.zip;
    }

    public void setState(java.lang.String value) {
        this.state = value;
    }

    public java.lang.String getState() {
        return this.state;
    }

    public void setUserLogincount(java.lang.Long value) {
        this.userLogincount = value;
    }

    public java.lang.Long getUserLogincount() {
        return this.userLogincount;
    }

    public void setUserType(java.lang.String value) {
        this.userType = value;
    }

    public java.lang.String getUserType() {
        return this.userType;
    }

    public void setDredgeTime(java.lang.String value) {
        this.dredgeTime = value;
    }

    public java.lang.String getDredgeTime() {
        return this.dredgeTime;
    }

    public void setSortNum(java.lang.Long value) {
        this.sortNum = value;
    }

    public java.lang.Long getSortNum() {
        return this.sortNum;
    }

    public void setCreateMan(java.lang.String value) {
        this.createMan = value;
    }

    public java.lang.String getCreateMan() {
        return this.createMan;
    }

    public void setLoginIp(java.lang.String value) {
        this.loginIp = value;
    }

    public java.lang.String getLoginIp() {
        return this.loginIp;
    }

    public void setLoginDate(java.lang.String value) {
        this.loginDate = value;
    }

    public java.lang.String getLoginDate() {
        return this.loginDate;
    }

    public void setCreateDate(java.lang.String value) {
        this.createDate = value;
    }

    public java.lang.String getCreateDate() {
        return this.createDate;
    }

    public void setTorgUsers(Set<TorgUser> torgUser) {
        this.torgUsers = torgUser;
    }

    public Set<TorgUser> getTorgUsers() {
        return torgUsers;
    }

    public void setTsysDataprivileges(Set<TsysDataprivilege> tsysDataprivilege) {
        this.tsysDataprivileges = tsysDataprivilege;
    }

    public Set<TsysDataprivilege> getTsysDataprivileges() {
        return tsysDataprivileges;
    }

    public void setTuserPosts(Set<TuserPost> tuserPost) {
        this.tuserPosts = tuserPost;
    }

    public Set<TuserPost> getTuserPosts() {
        return tuserPosts;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("UserId",
            getUserId()).append("LoginName", getLoginName())
                                                                        .append("Password",
            getPassword()).append("UserRealname", getUserRealname())
                                                                        .append("UserNo",
            getUserNo()).append("OrgId", getOrgId())
                                                                        .append("DeptId",
            getDeptId()).append("Sex", getSex())
                                                                        .append("Mobiletel",
            getMobiletel()).append("Fax", getFax()).append("Email", getEmail())
                                                                        .append("Zip",
            getZip()).append("State", getState())
                                                                        .append("UserLogincount",
            getUserLogincount()).append("UserType", getUserType())
                                                                        .append("DredgeTime",
            getDredgeTime()).append("SortNum", getSortNum())
                                                                        .append("CreateMan",
            getCreateMan()).append("LoginIp", getLoginIp())
                                                                        .append("LoginDate",
            getLoginDate()).append("CreateDate", getCreateDate()).toString();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getUserId()).toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof TsysUserinfo == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        TsysUserinfo other = (TsysUserinfo) obj;

        return new EqualsBuilder().append(getUserId(), other.getUserId())
                                  .isEquals();
    }

    public List<TsysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<TsysRole> roleList) {
        this.roleList = roleList;
    }

    public List<TsysOrg> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<TsysOrg> orgList) {
        this.orgList = orgList;
    }


	public TsysPost getUserPost() {
		return sysPost;
	}

	public void setUserPost(TsysPost userPost) {
		this.sysPost = userPost;
	}

	public TuserPost getUserost() {
		return userPost;
	}

	public void setUserost(TuserPost userPost) {
		this.userPost = userPost;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}
}
