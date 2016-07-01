package com.bageframework.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.bageframework.core.authority.AuthorityChecker;
import com.bageframework.core.config.BageConfig;

public class AuthorityInterceptor implements HandlerInterceptor {

	public static final Log logger = LogFactory.getLog(AuthorityInterceptor.class);

	@Autowired
	private BageConfig bageConfig;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (handler instanceof ResourceHttpRequestHandler) {
			return true;
		}

		if (logger.isDebugEnabled()) {
			logger.debug(request.getRequestURI());
		}

		// 权限验证
		AuthorityChecker authorityChecker = bageConfig.getAuthorityChecker();
		if (authorityChecker != null) {
			if (!authorityChecker.check(request, response, handler, bageConfig)) {
				return false;
			}
		}

		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
