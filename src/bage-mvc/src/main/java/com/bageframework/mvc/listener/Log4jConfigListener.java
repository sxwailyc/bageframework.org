package com.bageframework.mvc.listener;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.bageframework.core.env.Env;

public class Log4jConfigListener implements ServletContextListener {

	public static final Log logger = LogFactory.getLog(Log4jConfigListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// log4j config
		initLog4j();
	}

	private void initLog4j() {
		try {
			Properties p = new Properties();
			logger.info("load log4j config file:" + Env.getLog4jConfigFile());
			URL url = this.getClass().getClassLoader().getResource(Env.getLog4jConfigFile());
			p.load(url.openStream());
			PropertyConfigurator.configure(p);
		} catch (IOException ie) {
			logger.error(ie.getMessage(), ie);
		}
	}
}
