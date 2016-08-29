package com.bageframework.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

public class PinyinUtil {

	private static final Log logger = LogFactory.getLog(PinyinUtil.class);

	public static boolean isChineseChar(char oneChar) {
		if ((oneChar >= '\u4e00' && oneChar <= '\u9fa5') || (oneChar >= '\uf900' && oneChar <= '\ufa2d')) {
			return true;
		}
		return false;
	}

	public static String getChinese(String s) {
		StringBuilder sb = new StringBuilder();
		char[] chs = s.toCharArray();
		for (char c : chs) {
			if (isChineseChar(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String toPinyin(String str) {
		try {
			return PinyinHelper.convertToPinyinString(getChinese(str), "-", PinyinFormat.WITHOUT_TONE);
		} catch (PinyinException e) {
			logger.error(e.getMessage(), e);
			return str;
		}
	}

	public static void main(String[] args) {
		System.out.println(toPinyin("中国 sdfsdf"));
	}
}
