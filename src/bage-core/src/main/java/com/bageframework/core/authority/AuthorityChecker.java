package com.bageframework.core.authority;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bageframework.core.config.BageConfig;

public interface AuthorityChecker {

	public boolean check(HttpServletRequest request, HttpServletResponse response, Object handler, BageConfig bageConfig) throws IOException;
}
