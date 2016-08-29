package com.bageframework.demo.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.dao.base.IDAO;
import com.bageframework.demo.web.dao.ArticleDao;
import com.bageframework.demo.web.dao.CategoryDao;
import com.bageframework.demo.web.model.Article;
import com.bageframework.demo.web.model.Category;
import com.bageframework.demo.web.service.ArticleService;
import com.bageframework.demo.web.vo.ArticleVO;
import com.bageframework.demo.web.vo.admin.ArticleAdminVO;
import com.bageframework.service.base.BaseService;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */

@Service
public class ArticleServiceImpl extends BaseService<Article, ArticleVO, ArticleAdminVO, Integer> implements ArticleService {

	@Autowired
	private ArticleDao articleDaoDao;

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public IDAO<Article, Integer> getDao() {
		return articleDaoDao;
	}

	@Override
	public ArticleVO model2Vo(Article bean) {
		ArticleVO vo = ArticleVO.create(bean);
		Category category = categoryDao.get(bean.getCategory());
		if (category != null) {
			vo.setCategoryName(category.getRemark());
		}
		return vo;
	}

	@Override
	public ArticleAdminVO model2AdminVo(Article bean) {
		return ArticleAdminVO.create(bean);
	}

	private List<ArticleVO> toVOList(List<Article> list) {
		List<ArticleVO> voList = new ArrayList<ArticleVO>();
		for (Article article : list) {
			voList.add(model2Vo(article));
		}
		return voList;
	}

	@Override
	public List<ArticleVO> getRecommendList(int category, int size) {
		List<Article> list = articleDaoDao.getRecommendList(category, size);
		return toVOList(list);
	}

	@Override
	public List<ArticleVO> getHotList(int category, int size) {
		List<Article> list = articleDaoDao.getHotList(category, size);
		return toVOList(list);
	}

	@Override
	public List<ArticleVO> getNewList(int category, int size) {
		List<Article> list = articleDaoDao.getNewList(category, size);
		return toVOList(list);
	}

	@Override
	public ArticleVO getByName(String name) {
		Article article = articleDaoDao.getByName(name);
		if (article != null) {
			return model2Vo(article);
		}
		return null;
	}

}
