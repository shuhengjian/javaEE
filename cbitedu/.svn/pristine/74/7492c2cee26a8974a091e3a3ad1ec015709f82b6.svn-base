package com.creatorblue.cbitedu.core.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 不同对象间的转换
 * 
 * 
 */
public class ObjSwitchUtils {
	/**
	 * 将javaBean转换成Map
	 * 
	 * @param javaBean
	 *            javaBean
	 * @return Map对象
	 */
	public static Map<String, Object> toMap(Object javaBean) {
		Map<String, Object> result = new HashMap<String, Object>();

		// 获取父类参数
		Class<?> superClazz = javaBean.getClass().getSuperclass();
		int i = 1;
		while (superClazz != Object.class) {
			Method[] methods = javaBean.getClass().getSuperclass()
					.getDeclaredMethods();

			for (Method method : methods) {
				try {
					if (method.getName().startsWith("get")) {
						String field = method.getName();
						field = field.substring(field.indexOf("get") + 3);
						field = field.toLowerCase().charAt(0)
								+ field.substring(1);

						Object value = method.invoke(javaBean, (Object[]) null);
						result.put(field, null == value ? "" : value);
					}
				} catch (Exception e) {
				}
			}

			superClazz = superClazz.getClass().getSuperclass();

		}

		Method[] methods = javaBean.getClass().getDeclaredMethods();

		for (Method method : methods) {
			try {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);

					Object value = method.invoke(javaBean, (Object[]) null);
					result.put(field, null == value ? "" : value);
				}
			} catch (Exception e) {
			}
		}

		return result;
	}
	  /** 
     * 将map转换成Javabean 
     * 
     * @param javabean javaBean 
     * @param data map数据 
     */ 
    public static Object toJavaBean(Object javabean, Map<String, String> data) 
    { 
        Method[] methods = javabean.getClass().getDeclaredMethods(); 
        for (Method method : methods) 
        { 
            try 
            { 
                if (method.getName().startsWith("set")) 
                { 
                    String field = method.getName(); 
                    field = field.substring(field.indexOf("set") + 3); 
                    field = field.toLowerCase().charAt(0) + field.substring(1); 
                    method.invoke(javabean, new Object[] 
                    { 
                        data.get(field) 
                    }); 
                } 
            } 
            catch (Exception e) 
            { 
            } 
        } 

        return javabean; 
    } 

}