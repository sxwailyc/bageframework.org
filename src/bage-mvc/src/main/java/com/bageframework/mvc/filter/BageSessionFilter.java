package com.bageframework.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bageframework.core.config.BageConfig;
import com.bageframework.dao.redis.Redis;
import com.bageframework.mvc.request.BageHttpRequestWrapper;
import com.bageframework.mvc.session.SessionDaoRedisImpl;
import com.bageframework.mvc.session.SessionService;

public class BageSessionFilter implements Filter {

	public static final Log logger = LogFactory.getLog(BageSessionFilter.class);

	private SessionService sessionService = null;

	private BageConfig bageMvcConfig;

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.debug("do filter");
		BageHttpRequestWrapper wrapper = new BageHttpRequestWrapper((HttpServletRequest) request, (HttpServletResponse) response, sessionService, bageMvcConfig);
		chain.doFilter(wrapper, response);
	}

	public void init(FilterConfig config) throws ServletException {

		ServletContext context = config.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);

		try {
			bageMvcConfig = ctx.getBean(BageConfig.class);
		} catch (BeansException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e.getMessage());
			}
		}

		if (bageMvcConfig == null) {
			bageMvcConfig = new BageConfig();
		}

		sessionService = ctx.getBean(SessionService.class);
		if (bageMvcConfig.isEnableDistributedSession()) {
			SessionDaoRedisImpl sessionDaoRedisImpl = ctx.getBean(SessionDaoRedisImpl.class);
			Redis session = null;
			try {
				session = (Redis) ctx.getBean("session");
			} catch (BeansException e) {
				if (logger.isDebugEnabled()) {
					logger.debug(e.getMessage());
				}
			}
			if (session == null) {
				throw new RuntimeException("启用分布式session，但未配置redis session数据源");
			}
			sessionDaoRedisImpl.setRedis(session);
		}
	}
}
