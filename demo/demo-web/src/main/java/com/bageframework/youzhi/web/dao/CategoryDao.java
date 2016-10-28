package com.bageframework.youzhi.web.dao;

import com.bageframework.dao.base.IDAO;
import com.bageframework.youzhi.web.model.Category;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface CategoryDao extends IDAO<Category, Integer> {

	public Category getByName(String name);
}
