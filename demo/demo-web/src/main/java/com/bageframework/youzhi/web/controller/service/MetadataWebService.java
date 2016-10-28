package com.bageframework.youzhi.web.controller.service;

import com.bageframework.dao.beans.Page;
import com.bageframework.dao.beans.Query;
import com.bageframework.youzhi.web.model.Metadata;
import com.bageframework.youzhi.web.vo.admin.MetadataAdminVO;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface MetadataWebService {

	public Page<MetadataAdminVO> page(Query query, int pageNo, int pageSize);

	public Metadata get(String id);

	public boolean update(Metadata bean);

	public boolean add(Metadata bean);

	public boolean delete(String id);

	public boolean syncFromDb(String id);
}
