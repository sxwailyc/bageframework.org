package com.bageframework.coder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.coder.core.Config;
import com.bageframework.coder.dao.MetadataDao;
import com.bageframework.coder.dao.PropertyDao;
import com.bageframework.coder.helper.MetadataHelper;
import com.bageframework.coder.metadata.AdminControllerMetadata;
import com.bageframework.coder.metadata.AdminVOMetadata;
import com.bageframework.coder.metadata.BaseClassMetadata;
import com.bageframework.coder.metadata.DaoMetadata;
import com.bageframework.coder.metadata.DaoMysqlImplMetadata;
import com.bageframework.coder.metadata.Field;
import com.bageframework.coder.metadata.ModelMetadata;
import com.bageframework.coder.metadata.ServiceImplMetadata;
import com.bageframework.coder.metadata.ServiceMetadata;
import com.bageframework.coder.metadata.VOMetadata;
import com.bageframework.coder.metadata.ViewMetadata;
import com.bageframework.coder.metadata.WebServiceImplMetadata;
import com.bageframework.coder.metadata.WebServiceMetadata;
import com.bageframework.coder.model.ColModel;
import com.bageframework.coder.model.Column;
import com.bageframework.coder.model.Form;
import com.bageframework.coder.model.Metadata;
import com.bageframework.coder.model.Property;
import com.bageframework.coder.service.MetadataService;
import com.bageframework.coder.type.Formatter;
import com.bageframework.coder.type.Type;

@Service
public class MetadataServiceImpl implements MetadataService {

	@Autowired
	private MetadataDao metadataDao;

	@Autowired
	private PropertyDao propertyDao;

	private void handleColumn(BaseClassMetadata metadata, String table) {

		List<Column> columns = metadataDao.getColumns(table);
		for (Column column : columns) {
			Field field = MetadataHelper.column2Field(column);
			metadata.appendField(field);
			List<String> impts = MetadataHelper.getImport(column);
			for (String impt : impts) {
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

		String modelClassName = MetadataHelper.tableName2ClassName(table);

		metadata.setAuthor(config.getAuthor());
		metadata.setClassName(modelClassName + "AdminVO");
		metadata.setPackageName(config.getAdminVOPackage());

		metadata.setModelClassName(modelClassName);

		metadata.appendImport("org.springframework.beans.BeanUtils");
		metadata.appendImport(config.getModelPackage() + "." + metadata.getModelClassName());

		return metadata;
	}

	@Override
	public ServiceMetadata createServiceMetadata(Config config, String table) {

		ServiceMetadata metadata = new ServiceMetadata();

		metadata.setAuthor(config.getAuthor());
		metadata.setModelClassName(MetadataHelper.tableName2ClassName(table));
		metadata.setClassName(MetadataHelper.tableName2ClassName(table) + "Service");
		metadata.setPackageName(config.getServicePackage());

		metadata.appendImport("com.bageframework.service.IService");
		metadata.appendImport(config.getModelPackage() + "." + metadata.getModelClassName());
		metadata.appendImport(config.getVOPackage() + "." + metadata.getModelClassName() + "VO");
		metadata.appendImport(config.getAdminVOPackage() + "." + metadata.getModelClassName() + "AdminVO");

		String keyType = getKeyType(table);
		metadata.setKeyType(keyType);

		return metadata;
	}

	@Override
	public ServiceImplMetadata createServiceImplMetadata(Config config, String table) {

		ServiceImplMetadata metadata = new ServiceImplMetadata();

		metadata.setAuthor(config.getAuthor());
		metadata.setModelClassName(MetadataHelper.tableName2ClassName(table));
		metadata.setClassName(MetadataHelper.tableName2ClassName(table) + "ServiceImpl");
		metadata.setPackageName(config.getServiceImplPackage());

		metadata.appendImport("org.springframework.beans.factory.annotation.Autowired");
		metadata.appendImport("org.springframework.stereotype.Service");
		metadata.appendImport("com.bageframework.service.base.BaseService");
		metadata.appendImport("com.bageframework.dao.base.IDAO");
		metadata.appendImport(config.getModelPackage() + "." + metadata.getModelClassName());
		metadata.appendImport(config.getVOPackage() + "." + metadata.getModelClassName() + "VO");
		metadata.appendImport(config.getAdminVOPackage() + "." + metadata.getModelClassName() + "AdminVO");
		metadata.appendImport(config.getDaoPackage() + "." + metadata.getModelClassName() + "Dao");
		metadata.appendImport(config.getServicePackage() + "." + metadata.getModelClassName() + "Service");

		String daoClassName = metadata.getModelClassName() + "Dao";
		metadata.setDaoObjectName(daoClassName.substring(0, 1).toLowerCase() + daoClassName.substring(1));

		String keyType = getKeyType(table);
		metadata.setKeyType(keyType);

		return metadata;
	}

	@Override
	public WebServiceMetadata createWebServiceMetadata(Config config, String table) {

		WebServiceMetadata metadata = new WebServiceMetadata();

		metadata.setAuthor(config.getAuthor());
		metadata.setModelClassName(MetadataHelper.tableName2ClassName(table));
		metadata.setClassName(MetadataHelper.tableName2ClassName(table) + "WebService");
		metadata.setPackageName(config.getWebServicePackage());

		metadata.appendImport("com.bageframework.dao.beans.Page");
		metadata.appendImport("com.bageframework.dao.beans.Query");
		metadata.appendImport(config.getModelPackage() + "." + metadata.getModelClassName());
		metadata.appendImport(config.getAdminVOPackage() + "." + metadata.getModelClassName() + "AdminVO");

		String keyType = getKeyType(table);
		metadata.setKeyType(keyType);

		return metadata;
	}

	@Override
	public WebServiceImplMetadata createWebServiceImplMetadata(Config config, String table) {

		WebServiceImplMetadata metadata = new WebServiceImplMetadata();

		metadata.setAuthor(config.getAuthor());
		metadata.setModelClassName(MetadataHelper.tableName2ClassName(table));
		metadata.setClassName(MetadataHelper.tableName2ClassName(table) + "WebServiceImpl");
		metadata.setPackageName(config.getWebServiceImplPackage());

		metadata.appendImport("org.springframework.beans.factory.annotation.Autowired");
		metadata.appendImport("org.springframework.stereotype.Service");
		metadata.appendImport("com.bageframework.service.IService");
		metadata.appendImport("com.bageframework.service.web.base.BaseWebService");
		metadata.appendImport(config.getModelPackage() + "." + metadata.getModelClassName());
		metadata.appendImport(config.getVOPackage() + "." + metadata.getModelClassName() + "VO");
		metadata.appendImport(config.getAdminVOPackage() + "." + metadata.getModelClassName() + "AdminVO");
		metadata.appendImport(config.getServicePackage() + "." + metadata.getModelClassName() + "Service");
		metadata.appendImport(config.getWebServicePackage() + "." + metadata.getModelClassName() + "WebService");

		String serviceClassName = metadata.getModelClassName() + "Service";
		metadata.setServiceObjectName(serviceClassName.substring(0, 1).toLowerCase() + serviceClassName.substring(1));

		String keyType = getKeyType(table);
		metadata.setKeyType(keyType);

		return metadata;
	}

	@Override
	public AdminControllerMetadata createAdminControllerMetadata(Config config, String table) {

		AdminControllerMetadata metadata = new AdminControllerMetadata();

		String modelClassName = MetadataHelper.tableName2ClassName(table);

		metadata.setAuthor(config.getAuthor());
		metadata.setModelClassName(modelClassName);
		metadata.setClassName(modelClassName + "AdminController");
		metadata.setPackageName(config.getAdminControllerlPackage());

		metadata.appendImport("javax.servlet.http.HttpServletRequest");
		metadata.appendImport("org.apache.log4j.Logger");
		metadata.appendImport("org.springframework.stereotype.Controller");
		metadata.appendImport("org.springframework.web.bind.annotation.RequestMapping");
		metadata.appendImport("org.springframework.web.servlet.ModelAndView");

		metadata.setPathPrefix("/admin/" + modelClassName.substring(0, 1).toLowerCase() + modelClassName.substring(1));
		metadata.setTemplatePath("/admin/" + modelClassName.toLowerCase());

		return metadata;
	}

	@Override
	public DaoMetadata createDaoMetadata(Config config, String table) {

		DaoMetadata metadata = new DaoMetadata();

		metadata.setAuthor(config.getAuthor());
		metadata.setModelClassName(MetadataHelper.tableName2ClassName(table));
		metadata.setClassName(MetadataHelper.tableName2ClassName(table) + "Dao");
		metadata.setPackageName(config.getDaoPackage());

		metadata.appendImport("com.bageframework.dao.base.IDAO");
		metadata.appendImport(config.getModelPackage() + "." + metadata.getModelClassName());

		String keyType = getKeyType(table);
		metadata.setKeyType(keyType);

		return metadata;
	}

	@Override
	public DaoMysqlImplMetadata createDaoMysqlImplMetadata(Config config, String table) {

		DaoMysqlImplMetadata metadata = new DaoMysqlImplMetadata();

		metadata.setAuthor(config.getAuthor());
		metadata.setModelClassName(MetadataHelper.tableName2ClassName(table));
		metadata.setClassName(MetadataHelper.tableName2ClassName(table) + "DaoMysqlImpl");
		metadata.setPackageName(config.getDaoMysqlImplPackage());

		metadata.appendImport("org.springframework.stereotype.Repository");
		metadata.appendImport("com.bageframework.dao.base.mysql.BaseMysqlDao");
		metadata.appendImport(config.getModelPackage() + "." + metadata.getModelClassName());
		metadata.appendImport(config.getDaoPackage() + "." + metadata.getModelClassName() + "Dao");

		String keyType = getKeyType(table);
		metadata.setKeyType(keyType);

		return metadata;
	}

	@Override
	public ViewMetadata createViewMetadata(Config config, String table) {

		ViewMetadata viewMetadata = new ViewMetadata();

		Metadata metadata = metadataDao.get("table", table);
		if (metadata != null) {

			String modelClassName = metadata.getJavaClass();
			viewMetadata.setModelClassName(modelClassName);
			viewMetadata.setModelObjectName(modelClassName.substring(0, 1).toLowerCase() + modelClassName.substring(1));
			viewMetadata.setTitle(metadata.getRemark());
			viewMetadata.setViewName(modelClassName.toLowerCase() + "/list.jsp");

			List<Property> propertys = propertyDao.getList(metadata.getId());
			for (Property property : propertys) {

				Type type = Type.parse(property.getType());

				if (property.getShow() == 1) {

					String name = MetadataHelper.columnName2FieldName(property.getName());

					ColModel colModel = new ColModel();
					colModel.setEditable("false");
					colModel.setFormatter(Formatter.getFormatter(type));
					colModel.setIndex(name);
					colModel.setName(name);
					colModel.setLabel(property.getRemark());
					// colModel.setSorttype(sorttype);
					// colModel.setWidth(width);
					viewMetadata.addColModel(colModel);
				}

				if (property.getSearch() == 1 || property.getEdit() == 1) {

					Form form = new Form();
					form.setName(property.getName());
					form.setLabel(property.getRemark());
					form.setFormType(property.getFormType());
					if (property.getSearch() == 1) {
						viewMetadata.addSearchForm(form);
					}
					if (property.getEdit() == 1) {
						viewMetadata.addEditForm(form);
					}

				}

			}
		}

		return viewMetadata;
	}

	private String getKeyType(String table) {

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
			return uniKey;
		} else {
			return priKey;
		}

	}
}
