package com.bageframework.youzhi.web.helper;

public class PageHelper {

	public static String getName(String name) {
		name = name.replace(".html", "");
		if (name.indexOf("-") > 0) {
			String[] datas = name.split("-");
			return datas[0];
		}
		return name;
	}

	public static int getPage(String name) {
		name = name.replace(".html", "");
		if (name.indexOf("-") > 0) {
			String[] datas = name.split("-");
			return Integer.parseInt(datas[datas.length - 1]);
		}
		return 1;
	}
}
