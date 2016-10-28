package com.bageframework.youzhi.web.service;

import com.bageframework.service.IService;
import com.bageframework.youzhi.web.model.Metadata;
import com.bageframework.youzhi.web.vo.MetadataVO;
import com.bageframework.youzhi.web.vo.admin.MetadataAdminVO;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface MetadataService extends IService<Metadata, MetadataVO, MetadataAdminVO, String> {

	public boolean syncFromDb(String id);
}
