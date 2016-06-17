package com.bageframework.coder.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.coder.core.Config;
import com.bageframework.coder.helper.CoderHelper;
import com.bageframework.coder.metadata.Metadata;
import com.bageframework.coder.render.Render;
import com.bageframework.coder.render.RenderFactory;
import com.bageframework.coder.service.CoderService;
import com.bageframework.coder.service.MetadataService;

@Service
public class CoderServiceImpl implements CoderService {

	protected static Log logger = LogFactory.getLog(CoderServiceImpl.class);

	@Autowired
	private MetadataService metadataService;

	@Override
	public void generate(Config config, String table) {

		Metadata metadata = metadataService.createMetadata(config, table);

		// generate model
		generateModel(config, metadata);
	}

	private void generateModel(Config config, Metadata metadata) {

		Render render = RenderFactory.getInstance().getRender();
		String content = render.doRender(config.getModelTemplate(), metadata);

		String savePath = config.getModelClassPath(metadata.getClassName());
		logger.debug("save class[" + metadata.getClassName() + "], to[" + savePath + "]");
		CoderHelper.save(savePath, content);
	}

}
