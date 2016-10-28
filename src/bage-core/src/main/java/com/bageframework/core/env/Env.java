package com.bageframework.core.env;

import org.springframework.util.StringUtils;

import com.bageframework.core.exception.BageInitException;

public class Env {

	public final static String DEV = "dev";

	public final static String PROP = "prop";

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

	public static String getEnv() {
		return BAGE_ENV;
	}
}
