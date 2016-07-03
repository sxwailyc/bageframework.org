package com.bageframework.demo.web.service.impl;

import com.bageframework.demo.web.dao.CategoryDao;
import com.bageframework.demo.web.model.Category;
import com.bageframework.service.base.BaseService;
import org.springframework.stereotype.Service;
import com.bageframework.demo.web.vo.CategoryVO;
import com.bageframework.demo.web.service.CategoryService;
import com.bageframework.demo.web.vo.admin.CategoryAdminVO;
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
		return null;
	}

	@Override
	public CategoryAdminVO model2AdminVo(Category bean) {
		return CategoryAdminVO.create(bean);
	}

}
