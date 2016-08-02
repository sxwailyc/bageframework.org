package com.bageframework.demo.web.service.impl;

import com.bageframework.demo.web.service.ArticleService;
import com.bageframework.demo.web.vo.admin.ArticleAdminVO;
import com.bageframework.demo.web.dao.ArticleDao;
import com.bageframework.dao.base.IDAO;
import org.springframework.stereotype.Service;
import com.bageframework.demo.web.vo.ArticleVO;
import com.bageframework.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.bageframework.demo.web.model.Article;

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

	@Override
	public IDAO<Article, Integer> getDao() {
		return articleDaoDao;
	}

	@Override
	public ArticleVO model2Vo(Article bean) {
		return null;
	}

	@Override
	public ArticleAdminVO model2AdminVo(Article bean) {
		return ArticleAdminVO.create(bean);
	}

}
