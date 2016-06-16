package com.bageframework.coder.factory;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactory {

	private static Logger logger = Logger.getLogger(BeanFactory.class);

	private static BeanFactory instance;

	private ApplicationContext applicationContext;

	private BeanFactory() {

		logger.info("初始化上下文相关数据");
		String[] locations = { "bage-coder/applicationContext.xml" };
		applicationContext = new ClassPathXmlApplicationContext(locations);
	}

	public static BeanFactory getInstance() {

		if (instance == null) {
			instance = new BeanFactory();
		}
		return instance;
	}

	/**
	 * 获取一个bean
	 * 
	 * @param <T>
	 * @param cls
	 * @return
	 */
	public <T> T getBean(Class<T> cls) {
		return applicationContext.getBean(cls);
	}

}
