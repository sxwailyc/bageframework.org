package com.bageframework.demo.web.controller.service.impl;

import com.bageframework.demo.web.service.ArticleService;
import com.bageframework.demo.web.vo.admin.ArticleAdminVO;
import org.springframework.stereotype.Service;
import com.bageframework.demo.web.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import com.bageframework.demo.web.model.Article;
import com.bageframework.service.IService;
import com.bageframework.service.web.base.BaseWebService;
import com.bageframework.demo.web.controller.service.ArticleWebService;

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
}

