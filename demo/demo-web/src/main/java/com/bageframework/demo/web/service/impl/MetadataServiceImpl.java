package com.bageframework.demo.web.service.impl;

import com.bageframework.demo.web.dao.MetadataDao;
import com.bageframework.demo.web.service.MetadataService;
import com.bageframework.demo.web.vo.MetadataVO;
import com.bageframework.demo.web.vo.admin.MetadataAdminVO;
import com.bageframework.service.base.BaseService;
import com.bageframework.demo.web.model.Metadata;

import org.springframework.stereotype.Service;

import com.bageframework.dao.base.IDAO;

import org.springframework.beans.factory.annotation.Autowired;

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
		System.out.println(id);
		return true;
	}
}
