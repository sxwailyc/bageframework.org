package com.bageframework.demo.web.controller.service;

import com.bageframework.dao.beans.Page;
import com.bageframework.dao.beans.Query;
import com.bageframework.demo.web.model.Property;
import com.bageframework.demo.web.vo.admin.PropertyAdminVO;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface PropertyWebService{

    public Page<PropertyAdminVO> page(Query query, int pageNo, int pageSize);

	public Property get(Integer id);

	public boolean update(Property bean);

	public boolean add(Property bean);

	public boolean delete(Integer id);
}
