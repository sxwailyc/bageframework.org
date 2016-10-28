package com.bageframework.youzhi.web.controller.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.service.IService;
import com.bageframework.service.web.base.BaseWebService;
import com.bageframework.youzhi.web.controller.service.ArticleWebService;
import com.bageframework.youzhi.web.helper.ArticleHelper;
import com.bageframework.youzhi.web.model.Article;
import com.bageframework.youzhi.web.service.ArticleService;
import com.bageframework.youzhi.web.vo.ArticleVO;
import com.bageframework.youzhi.web.vo.admin.ArticleAdminVO;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Service
public class ArticleWebServiceImpl extends BaseWebService<Article, ArticleVO, ArticleAdminVO, Integer> implements ArticleWebService {

	@Autowired
	private ArticleService articleService;

	@Override
	public IService<Article, ArticleVO, ArticleAdminVO, Integer> getService() {
		return articleService;
	}

	@Override
	public String getStaticName(Date createdTime, String title) {
		return ArticleHelper.getStaticName(createdTime, title);
	}
}
