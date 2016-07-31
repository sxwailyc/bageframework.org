package com.bageframework.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	/**
	 * 
	 * Cookie的存活时间
	 */
	public static final int COOKIE_LIFE_TIME = 14 * 24 * 60 * 60;

	public static String getCookie(HttpServletRequest request, String name) {

		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie != null && name.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param time
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
			int time) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(time);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
