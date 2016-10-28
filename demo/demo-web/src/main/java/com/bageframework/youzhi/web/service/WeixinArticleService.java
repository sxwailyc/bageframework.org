package com.bageframework.youzhi.web.service;

import com.bageframework.service.IService;
import com.bageframework.youzhi.web.model.WeixinArticle;
import com.bageframework.youzhi.web.vo.WeixinArticleVO;
import com.bageframework.youzhi.web.vo.admin.WeixinArticleAdminVO;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface WeixinArticleService extends IService<WeixinArticle, WeixinArticleVO, WeixinArticleAdminVO, Integer> {

	public boolean syncToArticle(int id, int categoryId);
}
