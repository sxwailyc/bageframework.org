package com.bageframework.coder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.coder.core.Config;
import com.bageframework.coder.dao.MetadataDao;
import com.bageframework.coder.helper.MetadataHelper;
import com.bageframework.coder.metadata.Field;
import com.bageframework.coder.metadata.ModelMetadata;
import com.bageframework.coder.model.Column;
import com.bageframework.coder.service.MetadataService;

@Service
public class MetadataServiceImpl implements MetadataService {

	@Autowired
	private MetadataDao metadataDao;

	@Override
	public ModelMetadata createModelMetadata(Config config, String table) {

		ModelMetadata modelMetadata = new ModelMetadata();
		List<Column> columns = metadataDao.getColumns(table);
		for (Column column : columns) {
			Field field = MetadataHelper.column2Field(column);
			modelMetadata.appendField(field);
		}

		modelMetadata.setAuthor(config.getAuthor());
		modelMetadata.setClassName(MetadataHelper.tableName2ClassName(table));
		modelMetadata.setPackageName(config.getModelPackage());

		return modelMetadata;

	}
}
