package com.creatorblue.cbitedu.core.baseclass.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.utils.StringHelpers;

/**
 * <p>
 * Title:所有页面控制类的 基类，可以考虑继承它、
 * 获取更多系統中约定的功能(系统管理公共部分)
 * Description:基于命名规则的CRUD Controller基类
 */
@Controller
public class HihBaseController extends BaseController {
	/**
	 * 转换单个对象的指定属性
	 * @param request
	 * @param bean
	 * @param source
	 * @param fieldName
	 * @return
	 */
	public <T> T convertBean(T bean, Map<String, ?> source, String sourceField, String... fieldName) {
		List<T> list = new ArrayList<T>();
		list.add(bean);
		convertList(list, source, sourceField, fieldName);
		return bean;
	}
	/**
	 * 转换多个对象的指定属性
	 * @param request
	 * @param datas
	 * @param source
	 * @param fieldName
	 */
	public <T> void convertList(List<?> datas, Map<String, T> source, String sourceField, String... fieldNames) {
		if (source == null || fieldNames == null || fieldNames.length == 0) return;
		for (String fieldName : fieldNames) {
			String mname = StringHelpers.fistCapital(fieldName);
			Method setter = null;
			Method getter = null;
			for (Object o : datas) {
				try {
					if (setter == null) {
						try {
							setter = o.getClass().getDeclaredMethod("set" + mname, String.class);
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						}
					}
					if (getter == null) {
						try {
							getter = o.getClass().getDeclaredMethod("get" + mname);
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						}
					}
					if (setter != null && getter != null) {
						T t = null;
						try {
							t = (T) source.get(getter.invoke(o));
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
						String m = StringHelpers.fistCapital(sourceField);
						if (t != null) {
							Method gt = null;
							try {
								gt = t.getClass().getDeclaredMethod("get" + m);
							} catch (SecurityException e) {
								e.printStackTrace();
							} catch (NoSuchMethodException e) {
								e.printStackTrace();
							}
							if (gt != null)
								try {
									setter.invoke(o, gt.invoke(t));
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									e.printStackTrace();
								}
						}
					}
				} catch (ControllerException e) {
				}
			}
		}
	}
	
	 /**
     * 向页面输出一个字节流
     *
     * @param response
     *            HttpServletResponse
     * @param cntStr
     *            要向页面输出的字符串类型的信息
     **/
    protected void writeWeb(HttpServletResponse response, String cntStr)
        throws IOException {
        response.reset(); // reset
        response.setContentType("text/html; charset=GBK"); // 设置该信息的类别,该类别可以是js、html、文本等信息
        ServletOutputStream servletOS = null;
        InputStream inputStream = null;

        try {
            servletOS = response.getOutputStream();
            inputStream = new ByteArrayInputStream(cntStr.getBytes("GBK"));

            byte[] buf = new byte[1024]; // 缓存大小,也可以看成一次读取信息的大小,
            int readLength;

            while (((readLength = inputStream.read(buf)) != -1)) {
                servletOS.write(buf, 0, readLength); // 将读取的信息写入页面
            }
        } catch (IOException e) {
            log.equals(e);
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

            if (servletOS != null) {
                servletOS.flush();
                servletOS.close();
            }
        }
    }


}
