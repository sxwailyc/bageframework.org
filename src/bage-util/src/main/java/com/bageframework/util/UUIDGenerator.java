package com.bageframework.util;

import java.util.UUID;

public class UUIDGenerator {
	public UUIDGenerator() {
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static void main(String[] args) {
		System.out.println(getUUID().length());
	}

	// 用户id加密规则
	// 0->0
	// 1->5
	// 2->7
	// 3->1
	// 4->9
	// 5->6
	// 6->2
	// 7->4
	// 8->3
	// 9->8
	private static final Integer userRuleArr[] = { 0, 5, 7, 1, 9, 6, 2, 4, 3, 8 };

	/**
	 * userId加密+随机三位数
	 * 
	 * @param id
	 * @return
	 */
	public static String getUserEncryptId(long id) {
		String envalue = encrypt(id, userRuleArr);
		int rand = (int) (Math.random() * 900) + 100;
		return envalue + rand;
	}

	private static String encrypt(long id, Integer[] ruleArr) {
		String idStr = String.valueOf(id);
		StringBuffer sbf = new StringBuffer();
		for (int i = 1; i <= idStr.length(); i++) {
			char c = idStr.charAt(i - 1);
			String str = String.valueOf(c);
			int index = Integer.parseInt(str);
			sbf.append(ruleArr[index]);
		}
		return sbf.toString();
	}

}
