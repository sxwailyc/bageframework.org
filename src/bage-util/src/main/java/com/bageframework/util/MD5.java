package com.bageframework.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {

	/**
	 * 获取MD5的16进制32位串
	 * 
	 * @param content
	 * @return
	 */
	public static String md5(byte[] content) {
		String md5 = DigestUtils.md5Hex(content);
		return md5;
	}

	/**
	 * 获取MD5的16进制32位串
	 * 
	 * @param content
	 * @return
	 */
	public static String md5(String content) {
		String md5 = DigestUtils.md5Hex(content);
		return md5;
	}

}
