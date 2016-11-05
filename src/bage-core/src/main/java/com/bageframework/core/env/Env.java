package com.bageframework.core.env;

import org.springframework.util.StringUtils;

import com.bageframework.core.exception.BageInitException;

public class Env {

	public final static String DEV = "dev";

	public final static String PROD = "prod";

	public final static String TEST = "test";

	private final static String BAGE_ENV;

	static {

		try {
			String env = System.getenv("BAGE_ENV");
			if (StringUtils.isEmpty(env)) {
				env = DEV;
			}
			BAGE_ENV = env;
		} catch (Exception e) {
			throw new BageInitException(e.getMessage());
		}
	}

	/**
	 * 是否测试环境
	 * 
	 * @return
	 */
	public static boolean isTest() {
		if (StringUtils.isEmpty(BAGE_ENV)) {
			return false;
		}
		return TEST.equalsIgnoreCase(BAGE_ENV);
	}

	/**
	 * 是否开发环境
	 * 
	 * @return
	 */
	public static boolean isProd() {
		if (StringUtils.isEmpty(BAGE_ENV)) {
			return false;
		}
		return PROD.equalsIgnoreCase(BAGE_ENV);
	}

	/**
	 * 是否开发环境
	 * 
	 * @return
	 */
	public static boolean isDev() {
		if (StringUtils.isEmpty(BAGE_ENV)) {
			return true;
		}
		return false;
	}

	public static String getEnv() {
		return BAGE_ENV;
	}

	/**
	 * 获取log4j配置文件名
	 * 
	 * @return
	 */
	public static String getLog4jConfigFile() {
		if (isDev()) {
			return "log4j-dev.properties";
		} else if (isTest()) {
			return "log4j-test.properties";
		} else if (isProd()) {
			return "log4j-prod.properties";
		} else {
			return "log4j.properties";
		}
	}
}
