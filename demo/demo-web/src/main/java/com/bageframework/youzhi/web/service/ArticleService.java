package com.bageframework.youzhi.web.service;

import java.util.List;

import com.bageframework.service.IService;
import com.bageframework.youzhi.web.model.Article;
import com.bageframework.youzhi.web.vo.ArticleVO;
import com.bageframework.youzhi.web.vo.admin.ArticleAdminVO;

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
