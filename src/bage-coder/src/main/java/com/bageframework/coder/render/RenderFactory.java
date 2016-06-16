package com.bageframework.coder.render;

import org.apache.log4j.Logger;

import com.bageframework.coder.factory.BeanFactory;

public class RenderFactory {

	private static Logger logger = Logger.getLogger(BeanFactory.class);

	private Render render;

	private static RenderFactory instance;

	private RenderFactory() {
		render = new FreemarkerRender();
	}

	public static RenderFactory getInstance() {

		if (instance == null) {
			instance = new RenderFactory();
		}
		return instance;
	}

	public Render getRender() {
		return render;
	}

}
