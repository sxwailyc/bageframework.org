package com.bageframework.demo.web.controller.service.impl;

import com.bageframework.demo.web.controller.service.MetadataWebService;
import com.bageframework.demo.web.service.MetadataService;
import com.bageframework.demo.web.vo.MetadataVO;
import com.bageframework.demo.web.vo.admin.MetadataAdminVO;
import com.bageframework.service.web.base.BaseWebService;
import com.bageframework.demo.web.model.Metadata;
import com.bageframework.service.IService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Service
public class MetadataWebServiceImpl extends BaseWebService<Metadata, MetadataVO, MetadataAdminVO, String> implements MetadataWebService {

	@Autowired
	private MetadataService metadataService;

	@Override
	public IService<Metadata, MetadataVO, MetadataAdminVO, String> getService() {
		return metadataService;
	}

	@Override
	public boolean syncFromDb(String id) {
		return metadataService.syncFromDb(id);
	}

}
