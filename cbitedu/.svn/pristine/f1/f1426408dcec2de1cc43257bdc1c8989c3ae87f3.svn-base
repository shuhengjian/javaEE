package com.creatorblue.cbitedu.core.taglibs.securitytag;

import java.util.Locale;
import java.util.ResourceBundle;

import com.creatorblue.cbitedu.core.utils.StringHelpers;

public class PropertiesUtil {
	Locale locale = Locale.getDefault();

	static ResourceBundle rb = ResourceBundle
			.getBundle("resources.propertiescfg.SystemGlobals");

	public static String getValue(String key) {
		return rb.containsKey(key) ? rb.getString(key) : null;
	}

	public static String getValue(String key, String defaultValue) {
		String val = getValue(key);
		if (val == null)
			val = defaultValue;
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

}
