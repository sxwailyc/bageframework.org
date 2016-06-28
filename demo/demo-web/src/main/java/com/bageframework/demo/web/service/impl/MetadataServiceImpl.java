package com.bageframework.demo.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.coder.model.Column;
import com.bageframework.coder.type.FormType;
import com.bageframework.coder.type.Type;
import com.bageframework.dao.base.IDAO;
import com.bageframework.demo.web.dao.MetadataDao;
import com.bageframework.demo.web.dao.PropertyDao;
import com.bageframework.demo.web.model.Metadata;
import com.bageframework.demo.web.model.Property;
import com.bageframework.demo.web.service.MetadataService;
import com.bageframework.demo.web.vo.MetadataVO;
import com.bageframework.demo.web.vo.admin.MetadataAdminVO;
import com.bageframework.service.base.BaseService;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */

@Service
public class MetadataServiceImpl extends BaseService<Metadata, MetadataVO, MetadataAdminVO, String> implements MetadataService {

	@Autowired
	private MetadataDao metadataDaoDao;

	@Autowired
	private PropertyDao propertyDao;

	@Override
	public IDAO<Metadata, String> getDao() {
		return metadataDaoDao;
	}

	@Override
	public MetadataVO model2Vo(Metadata bean) {
		return null;
	}

	@Override
	public MetadataAdminVO model2AdminVo(Metadata bean) {
		return MetadataAdminVO.create(bean);
	}

	@Override
	public boolean syncFromDb(String id) {

		Metadata metadata = metadataDaoDao.get(id);

		propertyDao.getList(id);

		List<Column> columns = metadataDaoDao.getColumns(metadata.getTable());
		for (Column column : columns) {
			Property property = new Property();
			property.setEdit(1);
			property.setFormType(FormType.TEXT.getValue());
			property.setMetadataId(id);
			property.setName(column.getField());
			property.setRemark(column.getComment());
			property.setSearch(1);
			property.setShow(1);
			property.setSort(1);
			property.setType(Type.parse(column.getType()).getValue());
			propertyDao.add(property);
		}

		return true;
	}
}
