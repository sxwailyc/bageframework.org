package com.bageframework.youzhi.web.service.impl;

import com.bageframework.service.base.BaseService;
import com.bageframework.youzhi.web.dao.CategoryDao;
import com.bageframework.youzhi.web.model.Category;
import com.bageframework.youzhi.web.service.CategoryService;
import com.bageframework.youzhi.web.vo.CategoryVO;
import com.bageframework.youzhi.web.vo.admin.CategoryAdminVO;

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
public class CategoryServiceImpl extends BaseService<Category, CategoryVO, CategoryAdminVO, Integer> implements CategoryService {

	@Autowired
	private CategoryDao categoryDaoDao;

	@Override
	public IDAO<Category, Integer> getDao() {
		return categoryDaoDao;
	}

	@Override
	public CategoryVO model2Vo(Category bean) {
		return CategoryVO.create(bean);
	}

	@Override
	public CategoryAdminVO model2AdminVo(Category bean) {
		return CategoryAdminVO.create(bean);
	}

}
