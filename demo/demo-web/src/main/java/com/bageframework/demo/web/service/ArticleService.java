package com.bageframework.demo.web.service;

import com.bageframework.demo.web.vo.admin.ArticleAdminVO;
import com.bageframework.demo.web.vo.ArticleVO;
import com.bageframework.demo.web.model.Article;
import com.bageframework.service.IService;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface ArticleService extends IService<Article, ArticleVO, ArticleAdminVO, Integer> {

}
