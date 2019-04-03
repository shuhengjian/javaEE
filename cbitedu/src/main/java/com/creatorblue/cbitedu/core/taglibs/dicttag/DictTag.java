package com.creatorblue.cbitedu.core.taglibs.dicttag;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.creatorblue.cbitedu.core.utils.SpringContextHolder;
import com.creatorblue.cbitedu.core.utils.StringHelpers;
import com.creatorblue.cbitedu.system.domain.TsysDict;

/**
 * Title:通过定义字典标签来获取字典表定义中的字典值
 * Description:
 * Copyright: Copyright (c) 2015
 * Company:chinacretor.co.,ltd
 * @author chinacretor.co.,ltd
 * @version 1.0
 */
public class DictTag extends RequestContextAwareTag {
	private static final long serialVersionUID = -1549277547687411683L;
	private static final String SELECT = "select";
	private static final String RADIO = "radio";
	private static final String CHECKBOX = "checkbox";

	private String name;
	private String type;
	private String id;
	private String defValue;
	private String style;
	private String cssClass;
	private String withEmpty;

	private Object items;

	protected int doStartTagInternal() throws Exception {
		JspWriter out = pageContext.getOut();
		StringBuffer html = new StringBuffer();
		if (StringHelpers.notNull(name)) {
			swtichType(html);
		}
		out.write(html.toString());
		return SKIP_BODY;
	}

	private void swtichType(StringBuffer html) {
		if (StringHelpers.isNull(type))
			type = SELECT;
		List<TsysDict> list = SpringContextHolder.getBean(DictUtil.class).getByType(name); // 从数据库中获取数据
		if (list != null) {
			Collections.sort(list, new Comparator<TsysDict>() {
				public int compare(TsysDict o1, TsysDict o2) {
					if (o1 == null && o2 == null)
						return 0;
					else if (o1 != null && o2 == null)
						return -1;
					else if (o1 == null && o2 != null)
						return 1;
					Long order1 = o1.getSortNum();
					Long order2 = o2.getSortNum();
					if (order1 == null && order2 == null)
						return 0;
					else if (order1 != null && order2 == null)
						return -1;
					else if (order1 == null && order2 != null)
						return 1;
					else
						return order1.compareTo(order2);
				}
			});
		}
		if (SELECT.equalsIgnoreCase(type)) {
			html.append("<select id=\"" + id + "\" name=\"" + id
					+ "\" style=\"" + style + "\" class=\"" + cssClass
					+ "\">\n");
			if ("true".equals(withEmpty)) {
				html.append("<option value=\"\">请选择</option>");
			}
			if (list != null) {
				for (TsysDict param : list) {
					String key = param.getDictValue();
					String val = param.getDictCode();
					html.append("<option value=\"" + val + "\"");
					if (isDefault(key, val))
						html.append(" selected");
					html.append(">" + key + "</option>\n");
				}
			}
			html.append("</select>");
		} else if (RADIO.equalsIgnoreCase(type)) {
			int i = 0;
			if (list != null) {
				for (TsysDict p : list) {
					String key = p.getDictValue();
					if ("null".equals(withEmpty)) {
						key="";
					}
					String val = p.getDictCode();
					String id = this.id + (i++);
					html.append("<input type=\"radio\" name=\"" + this.id
							+ "\" id=\"" + id + "\" value=\"" + val + "\"");
					if (isDefault(key, val))
						html.append(" checked");
					html.append("/>");
					html.append("<label for=\"" + id + "\">" + key
							+ "</label>\n");
				}
			}

		} else if (CHECKBOX.equalsIgnoreCase(type)) {
			int i = 0;
			if (list != null) {
				for (TsysDict p : list) {
					String key = p.getDictValue();
					if ("true".equals(withEmpty)) {
						key="";
					}
					String val = p.getDictCode();
					String id = this.id + (i++);
					html.append("<input type=\"checkbox\" name=\"" + this.id
							+ "\" id=\"" + id + "\" value=\"" + val + "\"");
					if (isDefault(key, val))
						html.append(" checked");
					html.append("/>");
					html.append("<label for=\"" + id + "\">" + key
							+ "</label>\n");
				}
			}
		}

	}

	private boolean isDefault(String key, String val) {
		if (StringHelpers.isNull(defValue))
			return false;
		String[] values = defValue.split(",");
		for (String v : values) {
			if (val.equalsIgnoreCase(v) || key.equalsIgnoreCase(v))
				return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		try {
			if (items == null)
				items = "";
			this.items = ExpressionEvaluatorManager.evaluate("val",
					items.toString(), Object.class, this, pageContext);
		} catch (JspException e) {
			this.items = "";
		}
	}

	public String getDefValue() {
		return defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}

	public String getWithEmpty() {
		return withEmpty;
	}

	public void setWithEmpty(String withEmpty) {
		this.withEmpty = withEmpty;
	}

}
