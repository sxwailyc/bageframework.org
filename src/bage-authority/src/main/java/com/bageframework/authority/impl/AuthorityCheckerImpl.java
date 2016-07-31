package com.bageframework.authority.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;

import com.bageframework.authority.annotation.Authority;
import com.bageframework.authority.exception.NotLoginException;
import com.bageframework.authority.model.User;
import com.bageframework.authority.service.AuthorityService;
import com.bageframework.core.authority.AuthorityChecker;
import com.bageframework.core.config.BageConfig;

public class AuthorityCheckerImpl implements AuthorityChecker {

	public static final Log logger = LogFactory.getLog(AuthorityCheckerImpl.class);

	//@Autowired
	private AuthorityService authorityService;

	@Override
	public boolean check(HttpServletRequest request, HttpServletResponse response, Object handler, BageConfig bageConfig) throws IOException {

		String uri = request.getRequestURI();

		// 权限验证
//		if (bageConfig.isNeedLogin(uri)) {
//			User user = (User) request.getSession().getAttribute("user");
//			if (user == null) {
//				response.sendRedirect(bageConfig.getLoginUrl() + "?rdrs=" + uri);
//				return false;
//			}
//		} else {
//			logger.debug("不需要登陆验证.uri[" + uri + "]");
//		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		if (handlerMethod.getMethod().isAnnotationPresent(Authority.class)) {
			Authority authority = handlerMethod.getMethod().getAnnotation(Authority.class);
			String actionCode = authority.code();
			User user = (User) request.getSession().getAttribute("user");
			if (user == null) {
				throw new NotLoginException();
			}
			//authorityService.hasPrivilege(user.getUserId(), uri, actionCode);
		}

		logger.debug("authority checker impl");

		return true;
	}
}
