package com.bageframework.youzhi.web.controller.service;

import java.util.List;

import com.bageframework.dao.beans.Page;
import com.bageframework.dao.beans.Query;
import com.bageframework.youzhi.web.model.Category;
import com.bageframework.youzhi.web.vo.admin.CategoryAdminVO;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface CategoryWebService {

	public Page<CategoryAdminVO> page(Query query, int pageNo, int pageSize);

	public Category get(Integer id);

	public boolean update(Category bean);

	public boolean add(Category bean);

	public boolean delete(Integer id);

	public List<Category> getList();
}
