package com.bageframework.youzhi.web.controller.service.impl;

import com.bageframework.service.web.base.BaseWebService;
import com.bageframework.youzhi.web.controller.service.CategoryWebService;
import com.bageframework.youzhi.web.model.Category;
import com.bageframework.youzhi.web.service.CategoryService;
import com.bageframework.youzhi.web.vo.CategoryVO;
import com.bageframework.youzhi.web.vo.admin.CategoryAdminVO;
import com.bageframework.service.IService;
import org.springframework.stereotype.Service;

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
