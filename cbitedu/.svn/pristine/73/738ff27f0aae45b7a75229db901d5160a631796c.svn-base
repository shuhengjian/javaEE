package com.creatorblue.cbitedu.core.baseclass.controller;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.taglibs.dicttag.DictUtil;
import com.creatorblue.cbitedu.core.taglibs.prametertag.ParamUtil;
import com.creatorblue.cbitedu.core.utils.BeanUtils;
import com.creatorblue.cbitedu.core.utils.ConvertRegisterHelper;
import com.creatorblue.cbitedu.core.utils.DateUtils;
import com.creatorblue.cbitedu.core.utils.SpringContextHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Title: 前端MVC控制层基类 Description:公共属性 Copyright: Copyright (c) 2015
 * Company:hihsoft.co.,ltd
 * 
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class BaseController extends MultiActionController {
	protected final static String CREATED_SUCCESS = "创建成功";
	protected final static String UPDATE_SUCCESS = "更新成功";
	protected final static String MODIFY_SUCCESS = "修改成功";
	protected final static String DELETE_SUCCESS = "删除成功";
	protected final static String CREATED_FAILED = "创建失败";
	protected final static String UPDATE_FAILED = "更新失败";
	protected final static String MODIFY_FAILED = "修改失败";
	protected final static String DELETE_FAILED = "删除失败";

	static {
		// 注册converters
		ConvertRegisterHelper.registerConverters();
	}

	/** The Constant log. */
	protected final Logger log = Logger.getLogger(this.getClass());
	protected final Map<String, Object> json = new HashMap<String, Object>();
    /**
     * 分页处理，获取flexigrid的分页参数及排序列
     * @param req
     * @return
     */
	public Page getPage(HttpServletRequest req) {
		Page page = new Page();

		if (req.getParameter("page") != null) {
			page.setCurrentPage(Integer.parseInt(req.getParameter("page")));
		}

		if (req.getParameter("rp") != null) {
			page.setPageSize(Integer.parseInt(req.getParameter("rp")));
		}

		if (req.getParameter("sortname") != null) {
			page.setSortName(req.getParameter("sortname"));
		}

		if (req.getParameter("sortorder") != null) {
			page.setSortOrder(req.getParameter("sortorder"));
		}

		return page;
	}

	/**
	 * 把信息放入session.
	 * 
	 * @param request
	 *            the request
	 * @param attributeKey
	 *            the attribute key
	 * @param obj
	 *            the obj
	 */
	public void putSession(final HttpServletRequest request,
			final String attributeKey, final Object obj) {
		request.getSession().setAttribute(attributeKey, obj);
	}
	/**
	 * 返回操作信息
	 * @param response
	 * @param flag
	 * @param msg
	 */
	public void renderOperateInfo( HttpServletResponse response,boolean flag,String msg){
		String content = "{\"msg\":\"" + msg + "\",\"flag\":" + flag + "}";
		this.renderJson(response, content);
	}

	/**
	 * 从session中取得信息.
	 * 
	 * @param request
	 *            the request
	 * @param attributeKey
	 *            the attribute key
	 * @return the session
	 */
	public Object getSession(final HttpServletRequest request,
			final String attributeKey) {
		return request.getSession().getAttribute(attributeKey);
	}

	/**
	 * 从request或session中移除对象
	 * 
	 * @param request
	 * @param key
	 */
	public void remove(HttpServletRequest request, String key) {
		request.removeAttribute(key);
		request.getSession().removeAttribute(key);
	}

	/**
	 * 清空Session
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	public void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<String> attributeNames = session.getAttributeNames();

		while (attributeNames.hasMoreElements()) {
			session.removeAttribute(attributeNames.nextElement());
		}
	}

	/**
	 * 回调函数，声明CommandName--对象的名字,默认为首字母小写的类名.
	 * 
	 * @see #bindObject(HttpServletRequest, Object)
	 */
	@Override
	protected String getCommandName(final Object command) {
		return StringUtils.uncapitalize(command.getClass().getSimpleName());
	}

	/**
	 * 不通过view渲染直接输出纯字符串
	 */
	public void renderText(final HttpServletResponse response,
			final String content) {
		try {
			response.setContentType("text/plain;charset=UTF-8");
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (final IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 不通过view渲染直接输出纯HTML
	 */
	public void renderHtml(final HttpServletResponse response,
			final String content) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(content);
		} catch (final IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 不通过view渲染直接输出纯XML
	 */
	public void renderXML(final HttpServletResponse response,
			final String content) {
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.getWriter().write(content);
		} catch (final IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 不通过view渲染直接输出json
	 */
	public void renderJson(final HttpServletResponse response,
			final String content) {
		try {
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().write(content);
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 不通过view渲染直接输出json
	 * 
	 * @throws JsonProcessingException
	 */
	public void renderJson(List list, Page page, HttpServletResponse response)
			 {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalRows());
		map.put("page", page.getCurrentPage());
		map.put("rows", list);
		ObjectMapper mapper = new ObjectMapper();
		String result;
		try {
			result = mapper.writeValueAsString(map);
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().write(result);
		} catch (JsonProcessingException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * Convenience method for getting a i18n key's value. Calling
	 * getMessageSourceAccessor() is used because the RequestContext variable is
	 * not set in unit tests b/c there's no DispatchServlet Request.
	 * 
	 * @param msgKey
	 * @return
	 */
	public String getText(final String msgKey) {
		return getMessageSourceAccessor().getMessage(msgKey);
	}

	/**
	 * Convenient method for getting a i18n key's value with a single string
	 * argument.
	 * 
	 * @param msgKey
	 * @param arg
	 * @return
	 */
	public String getText(final String msgKey, final String arg) {
		return getText(msgKey, new Object[] { arg });
	}

	/**
	 * Convenience method for getting a i18n key's value with arguments.
	 * 
	 * @param msgKey
	 * @param args
	 * @return
	 */
	public String getText(final String msgKey, final Object[] args) {
		return getMessageSourceAccessor().getMessage(msgKey, args);
	}

	public static void copyProperties(Object target, Object source) {
		BeanUtils.copyProperties(target, source);
	}

	public static <T> T copyProperties(Class<T> destClass, Object orig) {
		return BeanUtils.copyProperties(destClass, orig);
	}

	/**
	 * 初始化binder的回调函数.
	 * 
	 * @see MultiActionController#createBinder(HttpServletRequest,Object)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Short.class, new CustomNumberEditor(
				Short.class, true));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(
				Long.class, true));
		binder.registerCustomEditor(Float.class, new CustomNumberEditor(
				Float.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(
				Double.class, true));
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(
				BigDecimal.class, true));
		binder.registerCustomEditor(BigInteger.class, new CustomNumberEditor(
				BigInteger.class, true));

		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	public static <T> T getOrCreateRequestAttribute(HttpServletRequest request,
			String key, Class<T> clazz) {
		Object value = request.getAttribute(key);

		if (value == null) {
			try {
				value = clazz.newInstance();
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}

			request.setAttribute(key, value);
		}

		return (T) value;
	}

	/**
	 * 将request中的参数设置到javabean中
	 * 
	 * @param request
	 * @param obj
	 */
	protected <T> void setValue(HttpServletRequest request, T obj) {
		Method[] methods = obj.getClass().getDeclaredMethods();

		for (Method m : methods) {
			String name = m.getName();

			if (!name.startsWith("set")) {
				continue;
			}

			name = name.substring(3).toLowerCase();

			Object value = getParam(request, name);
			
            
			if (value != null) {
				try {
					
					if (value.equals("") || (value == null)) {
						value = null;
					} else {
						Class<?> type = m.getParameterTypes()[0];

						if (Number.class.isAssignableFrom(type)) {
							Constructor<?> con = type
									.getConstructor(String.class);

							if (con != null) {
								value = con.newInstance(value);
							}
						} else if (int.class.isAssignableFrom(type)) {
							Constructor<?> con = type
									.getConstructor(String.class);

							if (con != null) {
								value = con.newInstance(value);
							}
						} else if (Date.class.isAssignableFrom(type)) {
							String v = (String) value;

							if (v.indexOf(":") != -1) {
								value = DateUtils.parse(v,
										DateUtils.FM_DATE_AND_TIME);
							} else {
								value = DateUtils.parse(value.toString());
							}
						}
						//peng.huang add
						else if(String.class.isAssignableFrom(type)){
							value=value.toString().trim();
						}
					}

					m.invoke(obj, value);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}
	}

	/**
	 * 在request中查找参数值，解决参数名大小写不一致的问题。
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String getParam(HttpServletRequest request, String name) {
		String val = request.getParameter(name);

		if (val != null) {
			return val;
		}

		Enumeration<String> names = request.getParameterNames();

		while (names.hasMoreElements()) {
			String n = names.nextElement();

			if (n.equalsIgnoreCase(name)) {
				return request.getParameter(n);
			}
		}

		return null;
	}

	/**
	 * 将集合中的编码字段转换为显示值
	 * 
	 * @param <T>
	 * @param datas
	 *            要转换的数据列表
	 * @param params
	 *            其实key为列表中的字段,必须与javabean中字段名一致.value为参数表中的参数类型
	 */
	protected <T> void convertParam(List<T> datas, Map<String, String> params) {
		if ((datas == null) || (params == null) || params.isEmpty()
				|| datas.isEmpty()) {
			return;
		}

		Set<String> keys = params.keySet();

		try {
			for (String field : keys) {
				Method setter = null;
				Method getter = null;
				String type = params.get(field);
				field = field.substring(0, 1).toUpperCase()
						+ field.substring(1);

				for (T t : datas) {
					if (getter == null) {
						getter = t.getClass().getDeclaredMethod("get" + field);
					}

					if (setter == null) {
						setter = t.getClass().getDeclaredMethod("set" + field,
								getter.getReturnType());
					}

					if ((getter == null) || (setter == null)) {
						break;
					}

					String val = (String) getter.invoke(t);
					String key = SpringContextHolder.getBean(ParamUtil.class).getKeyByVal(type, val);
					setter.invoke(t, key);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	/**
	 * 将集合中的编码字段转换为显示值
	 * 
	 * @param <T>
	 * @param datas
	 *            要转换的数据列表
	 * @param params
	 *            其实key为列表中的字段,必须与javabean中字段名一致.value为参数表中的参数类型
	 */
	protected <T> void convertDict(List<T> datas, Map<String, String> params) {
		if ((datas == null) || (params == null) || params.isEmpty()
				|| datas.isEmpty()) {
			return;
		}

		Set<String> keys = params.keySet();

		try {
			for (String field : keys) {
				Method setter = null;
				Method getter = null;
				String type = params.get(field);
				field = field.substring(0, 1).toUpperCase()
						+ field.substring(1);

				for (T t : datas) {
					if (getter == null) {
						getter = t.getClass().getDeclaredMethod("get" + field);
					}

					if (setter == null) {
						setter = t.getClass().getDeclaredMethod("set" + field,
								getter.getReturnType());
					}

					if ((getter == null) || (setter == null)) {
						break;
					}

					String val = (String) getter.invoke(t);
					String key = SpringContextHolder.getBean(DictUtil.class).getKeyByVal(type, val);
					setter.invoke(t, key);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * 将集合中的编码字段转换为显示值
	 * 
	 * @param <T>
	 * @param datas
	 *            要转换的数据列表
	 * @param field
	 *            对象中要转换的字段名，必须与javabean中字段名一致
	 * @param paramType
	 *            参数类型，对应数据库中的PARA_TYPE字段
	 */
	protected <T> void convertParam(List<T> datas, String field,
			String paramType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(field, paramType);
		convertParam(datas, params);
	}
	
	/**
	 * 将集合中的编码字段转换为显示值
	 * 
	 * @param <T>
	 * @param datas
	 *            要转换的数据列表
	 * @param field
	 *            对象中要转换的字段名，必须与javabean中字段名一致
	 * @param paramType
	 *            参数类型，对应数据库中的PARA_TYPE字段
	 */
	protected <T> void convertDict(List<T> datas, String field,
			String paramType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(field, paramType);
		convertDict(datas, params);
	}

	/**
	 * 将单个对象中的编码字段转换为显示值
	 * 
	 * @param <T>
	 * @param t
	 *            要转换的数据对象
	 * @param field
	 *            对象中要转换的字段名，必须与javabean中字段名一致
	 * @param paramType
	 *            参数类型，对应数据库中的PARA_TYPE字段
	 */
	protected <T> void convertParam(T t, String field, String paramType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(field, paramType);

		List<T> datas = new ArrayList<T>();
		datas.add(t);
		convertParam(datas, params);
	}
	
	/**
	 * 将单个对象中的编码字段转换为显示值
	 * 
	 * @param <T>
	 * @param t
	 *            要转换的数据对象
	 * @param field
	 *            对象中要转换的字段名，必须与javabean中字段名一致
	 * @param paramType
	 *            参数类型，对应数据库中的PARA_TYPE字段
	 */
	protected <T> void convertDict(T t, String field, String paramType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(field, paramType);

		List<T> datas = new ArrayList<T>();
		datas.add(t);
		convertDict(datas, params);
	}


	protected <T> void convertParam(T t, Map<String, String> map) {
		List<T> datas = new ArrayList<T>();
		datas.add(t);
		convertParam(datas, map);
	}
	protected <T> void convertDict(T t, Map<String, String> map) {
		List<T> datas = new ArrayList<T>();
		datas.add(t);
		convertDict(datas, map);
	}
	
	/**
	 * 把request.getParameter的所有参数设置到一个Map中，方便在页面上取参数
	 * @param request
	 * @return
	 */
	public Map copyMap(HttpServletRequest request) {
        Map paramMap = new HashMap();
        Enumeration enumeration = request.getParameterNames();

        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String object = (String) enumeration.nextElement();
                String[] val = request.getParameterValues(object);

                if (val.length == 1) {
                    if ((val[0] != null) && !"".equals(val[0])) {
                        paramMap.put(object, ConvertUtils.convert(val[0]));
                    }
                } else {
                    paramMap.put(object, val);
                }
            }
        }

        return paramMap;
    }
}
