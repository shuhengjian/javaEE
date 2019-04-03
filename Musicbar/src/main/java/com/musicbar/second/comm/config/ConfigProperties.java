package com.musicbar.second.comm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix="musicbar")
@PropertySource("classpath:configProperties.properties")
public class ConfigProperties {
	private String path;
	private String typePath;
	private String goodsPath;
	private String userPath;
	private String activePath;
	private String accessPath;
	private Integer pageSize;
	private Integer goodsSize;
	private String suthenticationLogout;
	private String suthenticationdoLogin;
	private String suthenticationauthc;
	private String suthenticationUrl;

	
	
	public String getSuthenticationLogout() {
		return suthenticationLogout;
	}
	public void setSuthenticationLogout(String suthenticationLogout) {
		this.suthenticationLogout = suthenticationLogout;
	}
	public String getSuthenticationdoLogin() {
		return suthenticationdoLogin;
	}
	public void setSuthenticationdoLogin(String suthenticationdoLogin) {
		this.suthenticationdoLogin = suthenticationdoLogin;
	}
	public String getSuthenticationauthc() {
		return suthenticationauthc;
	}
	public void setSuthenticationauthc(String suthenticationauthc) {
		this.suthenticationauthc = suthenticationauthc;
	}
	public String getSuthenticationUrl() {
		return suthenticationUrl;
	}
	public void setSuthenticationUrl(String suthenticationUrl) {
		this.suthenticationUrl = suthenticationUrl;
	}
	public Integer getGoodsSize() {
		return goodsSize;
	}
	public void setGoodsSize(Integer goodsSize) {
		this.goodsSize = goodsSize;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTypePath() {
		return typePath;
	}
	public void setTypePath(String typePath) {
		this.typePath = typePath;
	}
	public String getGoodsPath() {
		return goodsPath;
	}
	public void setGoodsPath(String goodsPath) {
		this.goodsPath = goodsPath;
	}
	public String getUserPath() {
		return userPath;
	}
	public void setUserPath(String userPath) {
		this.userPath = userPath;
	}
	public String getActivePath() {
		return activePath;
	}
	public void setActivePath(String activePath) {
		this.activePath = activePath;
	}
	public String getAccessPath() {
		return accessPath;
	}
	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}
}
