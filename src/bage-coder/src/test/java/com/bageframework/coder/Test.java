package com.bageframework.coder;

import com.bageframework.coder.core.Config;
import com.bageframework.coder.factory.BeanFactory;
import com.bageframework.coder.metadata.ModelMetadata;
import com.bageframework.coder.render.Render;
import com.bageframework.coder.render.RenderFactory;
import com.bageframework.coder.service.MetadataService;
import com.bageframework.coder.service.impl.MetadataServiceImpl;

public class Test {

	public static void main(String[] args) {

		String author = "shixiangwen03@gmail.com";

		Config config = Config.create().author(author).packagePrefix("com.bageframework.demo");

		MetadataService metadataService = BeanFactory.getInstance().getBean(MetadataServiceImpl.class);
		ModelMetadata modelMetadata = metadataService.createModelMetadata(config, "user");

		Render render = RenderFactory.getInstance().getRender();

		String content = render.doRender("template/model.ftl", modelMetadata);
		System.out.println(content);

	}
}
