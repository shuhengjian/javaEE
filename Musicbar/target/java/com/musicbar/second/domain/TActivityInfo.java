package com.musicbar.second.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TActivityInfo {
	/**
	 * 活动编号
	 */
    private String activId;
    /**
     * 活动主题
     */
    private String activTheme;
    /**
     * 活动简介
     */
    private String activIntro;
    /**
     * 活动开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date activStartTime;
    /**
     * 活动结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date activEndTime;
    /**
     * 活动说明
     */
    private String activExplain;
    /**
     * 活动要求
     */
    private String activRequest;
    /**
     * 活动状态
     */
    private String activState;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private String modifier;
    /**
     * 修改时间
     */
    private Date modifiTime;
    /**
     * 参数类
     */
    private TParameter parameter;
    /**
     * 资源类
     */
    private TAttach attch;
    
    public TAttach getAttch() {
		return attch;
	}

	public void setAttch(TAttach attch) {
		this.attch = attch;
	}
    
   	public TParameter getParameter() {
   		return parameter;
   	}

   	public void setParameter(TParameter parameter) {
   		this.parameter = parameter;
   	}
    
    public String getActivId() {
        return activId;
    }

    public void setActivId(String activId) {
        this.activId = activId;
    }

    public String getActivTheme() {
        return activTheme;
    }

    public void setActivTheme(String activTheme) {
        this.activTheme = activTheme;
    }

    public String getActivIntro() {
        return activIntro;
    }

    public void setActivIntro(String activIntro) {
        this.activIntro = activIntro;
    }
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    public Date getActivStartTime() {
        return activStartTime;
    }

    public void setActivStartTime(Date activStartTime) {
        this.activStartTime = activStartTime;
    }
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    public Date getActivEndTime() {
        return activEndTime;
    }

    public void setActivEndTime(Date activEndTime) {
        this.activEndTime = activEndTime;
    }

    public String getActivExplain() {
        return activExplain;
    }

    public void setActivExplain(String activExplain) {
        this.activExplain = activExplain;
    }

    public String getActivRequest() {
        return activRequest;
    }

    public void setActivRequest(String activRequest) {
        this.activRequest = activRequest;
    }

    public String getActivState() {
        return activState;
    }

    public void setActivState(String activState) {
        this.activState = activState;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifiTime() {
        return modifiTime;
    }

    public void setModifiTime(Date modifiTime) {
        this.modifiTime = modifiTime;
    }
}