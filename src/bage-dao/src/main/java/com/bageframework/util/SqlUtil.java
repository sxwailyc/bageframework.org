package com.bageframework.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * sql帮助类
 * 
 * @author jacky
 * 
 */
public class SqlUtil {

	/**
	 * 拼接lsit成字符串 [a, b] => 'a','b'
	 * 
	 * @param list
	 * @return
	 */
	public static String join(List<String> list) {
		List<String> l = new ArrayList<String>();
		for (String s : list) {
			l.add("'" + s + "'");
		}
		return StringUtils.join(l, ",");
	}

	public static String joinInteger(List<Integer> list) {
		List<String> l = new ArrayList<String>();
		for (Integer s : list) {
			l.add(String.valueOf(s));
		}
		return StringUtils.join(l, ",");
	}

	/**
	 * 连接字段名
	 * 
	 * @param list
	 * @return
	 */
	public static String joinColumn(List<String> list) {
		List<String> l = new ArrayList<String>();
		for (String s : list) {
			l.add(getSafeName(s));
		}
		return StringUtils.join(l, ",");
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static String getSafeName(String name) {
		if (name.startsWith("`")) {
			return name;
		}
		return "`" + name + "`";
	}

	/**
	 * 
	 * @param size
	 * @param placeholder
	 * @return
	 */
	public static String getPlaceholder(int size, char placeholder) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			if (sb.length() > 0) {
				sb.append(',');
			}
			sb.append(placeholder);
		}
		return sb.toString();
	}
}
