package com.bageframework.mvc.interceptor;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.bageframework.core.authority.AuthorityChecker;
import com.bageframework.core.config.BageConfig;
import com.bageframework.mvc.processor.ContextProcessor;

public class BageFrameworkInterceptor implements HandlerInterceptor {

	public static final Log logger = LogFactory.getLog(BageFrameworkInterceptor.class);

	@Autowired
	private BageConfig bageConfig;

	@Autowired
	private ApplicationContext applicationContext;

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

		Map<String, ContextProcessor> processors = applicationContext.getBeansOfType(ContextProcessor.class);
		for (Entry<String, ContextProcessor> entry : processors.entrySet()) {
			logger.debug("start to call context process.'" + entry.getKey() + "'");
			try {
				ContextProcessor processor = entry.getValue();
				processor.process(request, response, modelAndView);
			} catch (Exception e) {
				logger.error("context process error." + e.getMessage());
			}
		}

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
