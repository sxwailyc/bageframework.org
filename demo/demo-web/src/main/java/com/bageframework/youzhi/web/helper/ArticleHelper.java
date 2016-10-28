package com.bageframework.youzhi.web.helper;

import java.util.Date;

import com.bageframework.util.DateTimeUtil;
import com.bageframework.util.PinyinUtil;

public class ArticleHelper {

	public static String getStaticName(Date createdTime, String title) {
		return DateTimeUtil.date2String(createdTime, DateTimeUtil.SHORT_FORMAT_STRING) + "/" + PinyinUtil.toPinyin(title) + ".html";
	}

}
