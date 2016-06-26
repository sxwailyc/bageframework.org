package com.bageframework.demo.web.service;

import com.bageframework.demo.web.vo.MetadataVO;
import com.bageframework.demo.web.vo.admin.MetadataAdminVO;
import com.bageframework.demo.web.model.Metadata;
import com.bageframework.service.IService;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface MetadataService extends IService<Metadata, MetadataVO, MetadataAdminVO, String> {

	public boolean syncFromDb(String id);
}
