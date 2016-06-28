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
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bageframework.mvc.request.BageHttpRequestWrapper;
import com.bageframework.mvc.session.SessionService;

public class BageSessionFilter implements Filter {

	public static final Log logger = LogFactory.getLog(BageSessionFilter.class);

	private SessionService sessionService;

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.debug("do filter");
		BageHttpRequestWrapper wrapper = new BageHttpRequestWrapper((HttpServletRequest) request, (HttpServletResponse) response, sessionService);
		chain.doFilter(wrapper, response);
	}

	public void init(FilterConfig config) throws ServletException {

		ServletContext context = config.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		sessionService = ctx.getBean(SessionService.class);

	}

}
