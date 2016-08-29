package com.bageframework.demo.web.service;

import java.util.List;

import com.bageframework.demo.web.model.Article;
import com.bageframework.demo.web.vo.ArticleVO;
import com.bageframework.demo.web.vo.admin.ArticleAdminVO;
import com.bageframework.service.IService;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface ArticleService extends IService<Article, ArticleVO, ArticleAdminVO, Integer> {

	public ArticleVO getByName(String name);

	public List<ArticleVO> getRecommendList(int category, int size);

	public List<ArticleVO> getHotList(int category, int size);

	public List<ArticleVO> getNewList(int category, int size);
}
