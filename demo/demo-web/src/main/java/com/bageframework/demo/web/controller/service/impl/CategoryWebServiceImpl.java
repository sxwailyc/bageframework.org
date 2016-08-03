package com.bageframework.demo.web.controller.service.impl;

import com.bageframework.demo.web.model.Category;
import com.bageframework.service.web.base.BaseWebService;
import com.bageframework.demo.web.controller.service.CategoryWebService;
import com.bageframework.service.IService;
import org.springframework.stereotype.Service;
import com.bageframework.demo.web.vo.CategoryVO;
import com.bageframework.demo.web.service.CategoryService;
import com.bageframework.demo.web.vo.admin.CategoryAdminVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Service
public class CategoryWebServiceImpl extends BaseWebService<Category, CategoryVO, CategoryAdminVO, Integer> implements CategoryWebService {

	@Autowired
	private CategoryService categoryService;

	@Override
	public IService<Category, CategoryVO, CategoryAdminVO, Integer> getService() {
		return categoryService;
	}

	@Override
	public List<Category> getList() {
		return categoryService.getList();
	}

}
