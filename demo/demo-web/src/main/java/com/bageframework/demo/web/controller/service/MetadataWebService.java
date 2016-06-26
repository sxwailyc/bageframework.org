package com.bageframework.demo.web.controller.service;

import com.bageframework.dao.beans.Page;
import com.bageframework.dao.beans.Query;
import com.bageframework.demo.web.vo.admin.MetadataAdminVO;
import com.bageframework.demo.web.model.Metadata;

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
