package com.szf.cms.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @ClassName: Md5Util 
 * @Description: 密码加密工具类
 * @author: 宋圳峰
 * @date: 2020年6月7日 上午10:37:36
 */
public class Md5Util {
	private static String salt ="a1b1";
	
	public	static String Md5Encode(String password) {
		return  DigestUtils.md5Hex(password+salt);
	}
	
}
