package com.creatorblue.cbitedu.core.baseclass.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.creatorblue.cbitedu.core.interceptor.AConfig;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.utils.DateUtils;

/**
 * Title: 面向对象的值对象传递：
 * 所有实体类的基类 Description:公共属性 Copyright: Copyright (c) 2015
 * Company:hihsoft.co.,ltd
 * 
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class BaseDomain implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private String contextPath;
	protected static final String DATE_FORMAT = "yyyy-MM-dd";
	protected static final String TIME_FORMAT = "HH:mm:ss";
	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	protected Page page;// 分页属性
	protected String sortname;// 排序字段
	protected String sortorder;// 排序方式

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	protected List<AConfig> AConfigs;// 权限配置

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(final String contextPath) {
		this.contextPath = contextPath;
	}

	/**
	 * dto对象的复制
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String createTimeStamp() {
		final SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return format.format(DateUtils.getNowDate());
	}

	public static String date2String(java.util.Date date, String dateFormat) {
		return DateUtils.format(date, dateFormat);
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<AConfig> getAConfigs() {
		return AConfigs;
	}

	public void setAConfigs(List<AConfig> aConfigs) {
		AConfigs = aConfigs;
	}

	public void addAConfigs(AConfig aConfig) {
		if (this.AConfigs == null) {
			this.AConfigs = new ArrayList<AConfig>();
		}
		this.AConfigs.add(aConfig);
	}
}
