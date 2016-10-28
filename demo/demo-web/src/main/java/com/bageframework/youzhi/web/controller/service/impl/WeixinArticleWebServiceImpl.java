package com.bageframework.youzhi.web.controller.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.bageframework.service.IService;
import com.bageframework.service.web.base.BaseWebService;
import com.bageframework.youzhi.web.controller.service.WeixinArticleWebService;
import com.bageframework.youzhi.web.model.WeixinArticle;
import com.bageframework.youzhi.web.service.WeixinArticleService;
import com.bageframework.youzhi.web.vo.WeixinArticleVO;
import com.bageframework.youzhi.web.vo.admin.WeixinArticleAdminVO;

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
