package com.bageframework.demo.web.controller.service.impl;

import com.bageframework.demo.web.controller.service.WeixinArticleWebService;
import com.bageframework.demo.web.vo.WeixinArticleVO;
import org.springframework.stereotype.Service;
import com.bageframework.demo.web.vo.admin.WeixinArticleAdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import com.bageframework.service.IService;
import com.bageframework.service.web.base.BaseWebService;
import com.bageframework.demo.web.model.WeixinArticle;
import com.bageframework.demo.web.service.WeixinArticleService;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Service
public class WeixinArticleWebServiceImpl extends BaseWebService<WeixinArticle, WeixinArticleVO, WeixinArticleAdminVO, Integer> implements WeixinArticleWebService {

	@Autowired
	private WeixinArticleService weixinArticleService;

	@Override
	public IService<WeixinArticle, WeixinArticleVO, WeixinArticleAdminVO, Integer> getService() {
		return weixinArticleService;
	}

	@Override
	public boolean syncToArticle(int id, int categoryId) {
		return weixinArticleService.syncToArticle(id, categoryId);
	}

}
