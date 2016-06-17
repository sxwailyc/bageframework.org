package com.bageframework.coder.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.coder.core.Config;
import com.bageframework.coder.helper.CoderHelper;
import com.bageframework.coder.metadata.AdminVOMetadata;
import com.bageframework.coder.metadata.DaoMetadata;
import com.bageframework.coder.metadata.DaoMysqlImplMetadata;
import com.bageframework.coder.metadata.ModelMetadata;
import com.bageframework.coder.metadata.ServiceImplMetadata;
import com.bageframework.coder.metadata.ServiceMetadata;
import com.bageframework.coder.metadata.VOMetadata;
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

		// generate model
		ModelMetadata metadata = metadataService.createModelMetadata(config, table);
		generateModel(config, metadata);

		// generate dao
		DaoMetadata daoMetadata = metadataService.createDaoMetadata(config, table);
		generateDao(config, daoMetadata);

		// generate dao mysql impl
		DaoMysqlImplMetadata daoMysqlImplMetadata = metadataService.createDaoMysqlImplMetadata(config, table);
		generateDaoMysqlImpl(config, daoMysqlImplMetadata);

		// generate vo
		VOMetadata voMetadata = metadataService.createVOMetadata(config, table);
		generateVO(config, voMetadata);

		// generate model
		AdminVOMetadata adminVoMetadata = metadataService.createAdminVOMetadata(config, table);
		generateAdminVO(config, adminVoMetadata);

		// generate service
		ServiceMetadata serviceMetadata = metadataService.createServiceMetadata(config, table);
		generateService(config, serviceMetadata);

		// generate service impl
		ServiceImplMetadata serviceImplMetadata = metadataService.createServiceImplMetadata(config, table);
		generateServiceImpl(config, serviceImplMetadata);
	}

	private void generateModel(Config config, ModelMetadata metadata) {

		Render render = RenderFactory.getInstance().getRender();
		String content = render.doRender(config.getModelTemplate(), metadata);

		String savePath = config.getModelClassPath(metadata.getClassName());
		logger.debug("save class[" + metadata.getClassName() + "], to[" + savePath + "]");
		CoderHelper.save(savePath, content);
	}

	private void generateVO(Config config, VOMetadata metadata) {

		Render render = RenderFactory.getInstance().getRender();
		String content = render.doRender(config.getVOTemplate(), metadata);

		String savePath = config.getVOClassPath(metadata.getClassName());
		logger.debug("save class[" + metadata.getClassName() + "], to[" + savePath + "]");
		CoderHelper.save(savePath, content);
	}

	private void generateAdminVO(Config config, AdminVOMetadata metadata) {

		Render render = RenderFactory.getInstance().getRender();
		String content = render.doRender(config.getAdminVOTemplate(), metadata);

		String savePath = config.getAdminVOClassPath(metadata.getClassName());
		logger.debug("save class[" + metadata.getClassName() + "], to[" + savePath + "]");
		CoderHelper.save(savePath, content);
	}

	private void generateService(Config config, ServiceMetadata metadata) {

		Render render = RenderFactory.getInstance().getRender();
		String content = render.doRender(config.getServiceTemplate(), metadata);

		String savePath = config.getServiceClassPath(metadata.getClassName());
		logger.debug("save class[" + metadata.getClassName() + "], to[" + savePath + "]");
		CoderHelper.save(savePath, content);
	}

	private void generateServiceImpl(Config config, ServiceImplMetadata metadata) {

		Render render = RenderFactory.getInstance().getRender();
		String content = render.doRender(config.getServiceImplTemplate(), metadata);

		String savePath = config.getServiceImplClassPath(metadata.getClassName());
		logger.debug("save class[" + metadata.getClassName() + "], to[" + savePath + "]");
		CoderHelper.save(savePath, content);
	}

	private void generateDao(Config config, DaoMetadata metadata) {

		Render render = RenderFactory.getInstance().getRender();
		String content = render.doRender(config.getDaoTemplate(), metadata);

		String savePath = config.getDaoClassPath(metadata.getClassName());
		logger.debug("save class[" + metadata.getClassName() + "], to[" + savePath + "]");
		CoderHelper.save(savePath, content);
	}

	private void generateDaoMysqlImpl(Config config, DaoMysqlImplMetadata metadata) {

		Render render = RenderFactory.getInstance().getRender();
		String content = render.doRender(config.getDaoMysqlImplTemplate(), metadata);

		String savePath = config.getDaoMysqlImplClassPath(metadata.getClassName());
		logger.debug("save class[" + metadata.getClassName() + "], to[" + savePath + "]");
		CoderHelper.save(savePath, content);
	}

}
