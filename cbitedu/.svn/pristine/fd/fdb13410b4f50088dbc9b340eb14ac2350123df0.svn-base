package com.creatorblue.cbitedu.core.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
public class PropertiesUtil extends PropertyPlaceholderConfigurer {
    private static Map ctxPropertiesMap;
    Locale locale = Locale.getDefault();

	static ResourceBundle rb = ResourceBundle
			.getBundle("resources.propertiescfg.SystemGlobals");
	static ResourceBundle app = ResourceBundle
			.getBundle("resources.propertiescfg.ApplicationResources");

	public static String getValue(String key) {
		return rb.containsKey(key) ? rb.getString(key) : null;
	}
	
	public static String getValue(String key, String defaultValue) {
		String val = getValue(key);
		if (val == null) val = defaultValue;
		return val;
	}
	public static Integer getIntValue(String key) {
		try {
			String s = getValue(key);
			return StringHelpers.isNull(s) ? null : Integer.valueOf(s);
		} catch (final Exception ex) {
			return null;
		}
	}
	
	public static String getValueByFile(String key, String fileName) {
		try {
			return ResourceBundle.getBundle(fileName).getString(key);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getAppValue(String key, String defaultValue) {
		try {
			String val = app.getString(key);
			return StringHelpers.isNull(val) ? defaultValue : val;
		} catch (Exception e) {
			return defaultValue;
		}
	}
 
    @Override
    protected void processProperties(
            ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        ctxPropertiesMap = new HashMap();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }
    public static Object getProperty(String name) {
        return ctxPropertiesMap.get(name);
    }
}