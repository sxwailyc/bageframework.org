package com.bageframework.demo.web.service;

import com.bageframework.demo.web.vo.WeixinArticleVO;
import com.bageframework.demo.web.vo.admin.WeixinArticleAdminVO;
import com.bageframework.service.IService;
import com.bageframework.demo.web.model.WeixinArticle;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface WeixinArticleService extends IService<WeixinArticle, WeixinArticleVO, WeixinArticleAdminVO, Integer> {

	public boolean syncToArticle(int id, int categoryId);
}
