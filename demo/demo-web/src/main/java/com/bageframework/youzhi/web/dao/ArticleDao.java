package com.bageframework.demo.web.dao;

import java.util.List;

import com.bageframework.dao.base.IDAO;
import com.bageframework.demo.web.model.Article;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface ArticleDao extends IDAO<Article, Integer> {

	public List<Article> getRecommendList(int category, int size);

	public List<Article> getHotList(int category, int size);

	public List<Article> getNewList(int category, int size);

	public Article getByName(String name);
}
