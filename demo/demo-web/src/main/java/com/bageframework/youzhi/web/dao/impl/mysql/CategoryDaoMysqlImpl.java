package com.bageframework.youzhi.web.dao.impl.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bageframework.dao.base.mysql.BaseMysqlDao;
import com.bageframework.dao.jdbc.Jdbc;
import com.bageframework.dao.jdbc.SqlParameter;
import com.bageframework.youzhi.web.dao.CategoryDao;
import com.bageframework.youzhi.web.model.Category;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Repository
public class CategoryDaoMysqlImpl extends BaseMysqlDao<Category> implements CategoryDao {

	@Autowired
	private Jdbc jdbc;

	@Override
	public Category getByName(String name) {
		String sql = "SELECT * FROM category WHERE name = ? or remark = ? LIMIT 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(name);
		parameter.setString(name);
		return jdbc.get(sql, Category.class, parameter);
	}

}
