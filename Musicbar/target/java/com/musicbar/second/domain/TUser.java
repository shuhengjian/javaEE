package com.musicbar.second.domain;

import java.util.Date;
import java.util.List;

public class TUser {
    private String userId;

    private String userName;

    private String userMobile;
    
    private String userIdcard;

    private String userPassword;

    private String createTime;

    private Date updateTime;

    private String userState;
    
    private TUserRole userRole;
    
    private TRole role;
    
    private List<TAttach> attach;
    
    private TParameter parameter;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserIdcard() {
		return userIdcard;
	}

	public void setUserIdcard(String userIdcard) {
		this.userIdcard = userIdcard;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public TUserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(TUserRole userRole) {
		this.userRole = userRole;
	}

	public TRole getRole() {
		return role;
	}

	public void setRole(TRole role) {
		this.role = role;
	}

	public List<TAttach> getAttach() {
		return attach;
	}

	public void setAttach(List<TAttach> attach) {
		this.attach = attach;
	}

	public TParameter getParameter() {
		return parameter;
	}

	public void setParameter(TParameter parameter) {
		this.parameter = parameter;
	}

}