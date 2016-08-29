package com.bageframework.coder;

import com.bageframework.coder.core.Config;
import com.bageframework.coder.factory.BeanFactory;
import com.bageframework.coder.service.CoderService;
import com.bageframework.coder.service.impl.CoderServiceImpl;

public class Test {

	public static void main(String[] args) {

		String author = "shixiangwen03@gmail.com";

		Config config = Config.create().author(author).packagePrefix("com.bageframework.demo.web");

		CoderService coderService = BeanFactory.getInstance().getBean(CoderServiceImpl.class);

		String projectDir = "D:/develop/workspace_java/bageframework/bageframework/demo/demo-web/";

		config.projectDir(projectDir);

		//coderService.generate(config, "role");
		coderService.generate(config, "spider_task");
		//coderService.generate(config, "property");
	}
}
