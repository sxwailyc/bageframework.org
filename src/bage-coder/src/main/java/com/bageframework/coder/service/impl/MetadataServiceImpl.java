package com.bageframework.coder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.coder.core.Config;
import com.bageframework.coder.dao.MetadataDao;
import com.bageframework.coder.helper.MetadataHelper;
import com.bageframework.coder.metadata.AdminVOMetadata;
import com.bageframework.coder.metadata.BaseClassMetadata;
import com.bageframework.coder.metadata.Field;
import com.bageframework.coder.metadata.ModelMetadata;
import com.bageframework.coder.metadata.ServiceMetadata;
import com.bageframework.coder.metadata.VOMetadata;
import com.bageframework.coder.model.Column;
import com.bageframework.coder.service.MetadataService;

@Service
public class MetadataServiceImpl implements MetadataService {

	@Autowired
	private MetadataDao metadataDao;

	private void handleColumn(BaseClassMetadata metadata, String table) {

		List<Column> columns = metadataDao.getColumns(table);
		for (Column column : columns) {
			Field field = MetadataHelper.column2Field(column);
			metadata.appendField(field);
			String impt = MetadataHelper.getImport(column);
			if (impt != null) {
				metadata.appendImport(impt);
			}
		}

	}

	@Override
	public ModelMetadata createModelMetadata(Config config, String table) {

		ModelMetadata modelMetadata = new ModelMetadata();

		handleColumn(modelMetadata, table);

		modelMetadata.setAuthor(config.getAuthor());
		modelMetadata.setClassName(MetadataHelper.tableName2ClassName(table));
		modelMetadata.setPackageName(config.getModelPackage());

		return modelMetadata;

	}

	@Override
	public VOMetadata createVOMetadata(Config config, String table) {

		VOMetadata metadata = new VOMetadata();

		handleColumn(metadata, table);

		metadata.setAuthor(config.getAuthor());
		metadata.setClassName(MetadataHelper.tableName2ClassName(table) + "VO");
		metadata.setPackageName(config.getVOPackage());

		return metadata;
	}

	@Override
	public AdminVOMetadata createAdminVOMetadata(Config config, String table) {

		AdminVOMetadata metadata = new AdminVOMetadata();

		handleColumn(metadata, table);

		metadata.setAuthor(config.getAuthor());
		metadata.setClassName(MetadataHelper.tableName2ClassName(table) + "AdminVO");
		metadata.setPackageName(config.getAdminVOPackage());

		return metadata;
	}

	@Override
	public ServiceMetadata createServiceMetadata(Config config, String table) {

		ServiceMetadata metadata = new ServiceMetadata();

		metadata.setAuthor(config.getAuthor());
		metadata.setModelClassName(MetadataHelper.tableName2ClassName(table));
		metadata.setClassName(MetadataHelper.tableName2ClassName(table) + "Service");
		metadata.setPackageName(config.getServicePackage());

		List<Column> columns = metadataDao.getColumns(table);
		String priKey = null;
		String uniKey = null;
		for (Column column : columns) {
			Field field = MetadataHelper.column2Field(column);
			if ("UNI".equals(column.getKey())) {
				uniKey = field.getType();
			} else if ("PRI".equals(column.getKey())) {
				priKey = field.getType();
			}
		}

		if (uniKey != null) {
			metadata.setKeyType(uniKey);
		} else {
			metadata.setKeyType(priKey);
		}

		metadata.appendImport("com.bageframework.service.IService");
		metadata.appendImport(config.getModelPackage() + "." + metadata.getModelClassName());
		metadata.appendImport(config.getVOPackage() + "." + metadata.getModelClassName() + "VO");
		metadata.appendImport(config.getAdminVOPackage() + "." + metadata.getModelClassName() + "AdminVO");

		return metadata;
	}
}
