package com.musicbar.second.comm.param;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.musicbar.core.aop.LogAspect;


/**
 * 参数装换类
 * @author Administrator
 *
 */
@Component
public class ParamTransformation {
	private Logger log = Logger.getLogger(ParamTransformation.class);
	@Autowired
	private ParamUtil paramUtil;
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
	public <T> void convertParam(List<T> datas, String field,
			String paramType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(field, paramType);//要转换的集合的javabean中的字段名为key，值为数据库的字段
		convertParam(datas, params);//传入需要转换的list集合，以及params集合
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
				String type = params.get(field);//通过要转换的集合的javabean字段获取数据库字段
				field = field.substring(0, 1).toUpperCase()
						+ field.substring(1);//将要转换的集合的javabean字段转化为首字母大写

				for (T t : datas) {//循环获取需要装换的list集合的数据
					if (getter == null) {
						getter = t.getClass().getDeclaredMethod("get" + field);//生成要转换的集合的javabean字段getter方法
					}
					if (setter == null) {
						setter = t.getClass().getDeclaredMethod("set" + field,
								getter.getReturnType());//生成要转换的集合的javabean字段setter方法，该setter参数为getter返回值类型
					}

					if ((getter == null) || (setter == null)) {
						break;
					}
					
					String val = (String) getter.invoke(t);//通过反射拿到t类型的field字段的值
					String key = paramUtil.getKeyByVal(type, val);
					setter.invoke(t, key);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
	}
}
