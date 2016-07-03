package com.bageframework.demo.web.service;

import com.bageframework.demo.web.model.Category;
import com.bageframework.service.IService;
import com.bageframework.demo.web.vo.CategoryVO;
import com.bageframework.demo.web.vo.admin.CategoryAdminVO;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface CategoryService extends IService<Category, CategoryVO, CategoryAdminVO, Integer> {

}
