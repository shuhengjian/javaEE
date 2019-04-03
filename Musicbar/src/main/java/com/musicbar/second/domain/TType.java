package com.musicbar.second.domain;

import java.util.Date;

public class TType {
    private String typeId;

    private String typeName;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String typeState;

    private Integer typeSort;
    
    private TAttach attch;
    
    private TParameter parameter;

    public TParameter getParameter() {
		return parameter;
	}

	public void setParameter(TParameter parameter) {
		this.parameter = parameter;
	}

	public TAttach getAttch() {
		return attch;
	}

	public void setAttch(TAttach attch) {
		this.attch = attch;
	}

	public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTypeState() {
        return typeState;
    }

    public void setTypeState(String typeState) {
        this.typeState = typeState;
    }

    public Integer getTypeSort() {
        return typeSort;
    }

    public void setTypeSort(Integer typeSort) {
        this.typeSort = typeSort;
    }
}