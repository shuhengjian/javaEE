package com.creatorblue.cbitedu.core.taglibs.securitytag;

import java.util.Map;

import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.creatorblue.cbitedu.core.constants.Constant;

/**
 * <p>
 * Title:自定义安全权限标签
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author zhujw
 * @version 1.0
 */

public class HihAuthTag extends RequestContextAwareTag {

	private static final long serialVersionUID = -7112194947977479337L;
	private String module_code;

	public String getModule_code() {
		return module_code;
	}

	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}

	public String getOperate_code() {
		return operate_code;
	}

	public void setOperate_code(String operate_code) {
		this.operate_code = operate_code;
	}

	private String operate_code;
	private String value;
	private String type;
	private String style;
	private String cssClass;
	private String id;
	private String icon;
	private String onclick;
	private String plain;

	public String getPlain() {
		return plain;
	}

	public void setPlain(String plain) {
		this.plain = plain;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	private static final String TYPE_BTN = "button";
	private static final String TYPE_DIVBTN = "divbutton";
	private static final String TYPE_LINK = "link";

	@Override
	protected int doStartTagInternal() throws Exception {
		JspWriter out = pageContext.getOut();
		//读取下属性文件
		value = PropertiesUtil.getValue(value, value);
		if (value == null) value = "";
		String tag="";
		if (!hasPermission()){
			return SKIP_BODY;
		}
		else{
		if (type == null) {
			type = TYPE_DIVBTN;
		}
		if (value == null)
			value = "";
		if (type.equalsIgnoreCase(TYPE_BTN)) {
			tag = "<input> ";
			tag = attr(tag, "icon", val(icon));
			tag = attr(tag, "id", val(id));
			tag = attr(tag, "plain", "true");
			tag = attr(tag, "type", val("button"));
			tag = attr(tag, "value", val(value));
			tag=attr(tag,"onclick",val(onclick));
			tag=attr(tag,"class",val(cssClass));
			tag = attr(tag, "style", val(style));
			tag = tag + "</input>";
			
		}
		if (type.equalsIgnoreCase(TYPE_LINK)) {

			tag = "<a>";
			tag = attr(tag, "class", val(cssClass));
			tag = attr(tag, "type", val("link"));
			tag = attr(tag, "plain", "true");
			tag = attr(tag, "icon", val(icon));
			tag=attr(tag,"onclick",val(onclick));
			tag = attr(tag, "id", val(id));
			// tag = attr(tag, "style", val(style));
			tag = tag + value + "</a>";
		}
		out.write(tag);

		return EVAL_PAGE;
		}
	}

	private String attr(String tag, String attr, String value) {
		return HtmlUtil.addAttribute(tag, attr, value);
	}

	@SuppressWarnings("unchecked")
	private boolean hasPermission() {
		boolean flag = false;
		if (module_code == null || operate_code == null)
			return flag;
		Map<String, Map<String, String>> roleSet = (Map<String, Map<String, String>>) pageContext
				.getSession().getAttribute(Constant.USER_PRIVILEGES_DATA);
		if (roleSet == null)
			return flag;
		Map<String, String> map = roleSet.get(operate_code+module_code);
		if (map != null && map.get(operate_code) != null) {
			flag = true;
		}
		return flag;
	}

	private String val(String val) {
		return val == null ? "" : val;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}
}
