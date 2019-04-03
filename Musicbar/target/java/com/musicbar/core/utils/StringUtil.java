package com.musicbar.core.utils;

import java.util.Date;
import java.util.UUID;

/**
 * 字符串工具类
 * @author Administrator
 *
 */
public final class StringUtil {
	private StringUtil() {}
	/**
	 * 获取32位的uuid值，连续的16进制值
	 */
	public static final String getUUIDValue() {
		return getUUID().replaceAll("-", "");

	}
	/**
	 * 获取36位长度的UUID值，连续的16进制与-的组合
	 */
	public static final String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 流水号
	 * @param str 输入最大流水号，传出新流水号
	 * @return
	 */
	public static final String getSerialNumber(String str) {
		String date = DateUtils.fotmatDateToymd(new Date());//获取当前年月日
		String st = String.valueOf(date).concat("00000001");
		if(str != null && str != "" && !str.isEmpty()) {
			String _str = str.substring(0, 8);
			st = date.equals(_str) ? String.valueOf(Long.parseLong(str) + 1) : st;
		}
		return st;
	}
}
