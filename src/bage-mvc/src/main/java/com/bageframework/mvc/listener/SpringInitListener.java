package com.bageframework.mvc.listener;

import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringInitListener implements ServletContextListener {

	public static final Log logger = LogFactory.getLog(SpringInitListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		Map<String, Listener> listeners = context.getBeansOfType(Listener.class);
		for (Listener listener : listeners.values()) {
			listener.onStart(context);
		}
	}

}
