package com.bageframework.demo.web.dao.impl.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bageframework.dao.base.mysql.BaseMysqlDao;
import com.bageframework.dao.jdbc.Jdbc;
import com.bageframework.dao.jdbc.SqlParameter;
import com.bageframework.demo.web.dao.ArticleDao;
import com.bageframework.demo.web.model.Article;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Repository
public class ArticleDaoMysqlImpl extends BaseMysqlDao<Article> implements ArticleDao {

	@Autowired
	private Jdbc jdbc;

	@Override
	public Article getByName(String name) {
		String sql = "SELECT * FROM article WHERE static_name = ? LIMIT 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(name);
		return jdbc.get(sql, Article.class, parameter);
	}

	@Override
	public List<Article> getRecommendList(int category, int size) {
		String where = " is_recom = 1 ";
		if (category > 0) {
			where += " AND category=" + category;
		}
		String sql = "SELECT * FROM article WHERE " + where + " ORDER BY created_time DESC LIMIT ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(size);

		return jdbc.getList(sql, Article.class, parameter);
	}

	@Override
	public List<Article> getHotList(int category, int size) {

		String where = " 1=1 ";
		if (category > 0) {
			where = " category=" + category;
		}
		String sql = "SELECT * FROM article WHERE " + where + " ORDER BY view_count DESC LIMIT ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(size);

		return jdbc.getList(sql, Article.class, parameter);
	}

	@Override
	public List<Article> getNewList(int category, int size) {

		String where = " is_img = 1 ";
		if (category > 0) {
			where += " AND category=" + category;
		}
		String sql = "SELECT * FROM article WHERE " + where + " ORDER BY created_time DESC LIMIT ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(size);

		return jdbc.getList(sql, Article.class, parameter);
	}

}
