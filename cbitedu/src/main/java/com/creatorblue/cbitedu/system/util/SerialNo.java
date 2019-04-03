package com.creatorblue.cbitedu.system.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creatorblue.cbitedu.system.service.SerialNumber;

/**
 * <p> Title: 公共流水号接口</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author yuncai.zhu
 * @version 1.0
 */
public class SerialNo {
	private static Logger logger = LoggerFactory.getLogger(SerialNo.class);
	/*
	 	* @param secound_name	规则别名   
	    * @return String
	    * @throws Exception
	 */
	public static String getSerialNo(String secound_name) throws Exception{
		SerialNumber serialNumber=SerialNumber.newInstance(secound_name);	
		return serialNumber.SerialNextNo();		
	}

}
