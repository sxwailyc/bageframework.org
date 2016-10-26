package com.bageframework.demo.web.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.dao.base.IDAO;
import com.bageframework.demo.web.dao.WeixinArticleDao;
import com.bageframework.demo.web.helper.ArticleHelper;
import com.bageframework.demo.web.model.Article;
import com.bageframework.demo.web.model.WeixinArticle;
import com.bageframework.demo.web.service.ArticleService;
import com.bageframework.demo.web.service.WeixinArticleService;
import com.bageframework.demo.web.vo.WeixinArticleVO;
import com.bageframework.demo.web.vo.admin.WeixinArticleAdminVO;
import com.bageframework.service.base.BaseService;
import com.bageframework.util.DateTimeUtil;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */

@Service
public class WeixinArticleServiceImpl extends BaseService<WeixinArticle, WeixinArticleVO, WeixinArticleAdminVO, Integer> implements WeixinArticleService {

	@Autowired
	private WeixinArticleDao weixinArticleDaoDao;

	@Autowired
	private ArticleService articleService;

	@Override
	public IDAO<WeixinArticle, Integer> getDao() {
		return weixinArticleDaoDao;
	}

	@Override
	public WeixinArticleVO model2Vo(WeixinArticle bean) {
		return null;
	}

	@Override
	public WeixinArticleAdminVO model2AdminVo(WeixinArticle bean) {
		return WeixinArticleAdminVO.create(bean);
	}

	@Override
	public boolean syncToArticle(int id, int categoryId) {
		WeixinArticle weixinArticle = get(id);
		Article article = new Article();
		article.setCategory(categoryId);
		article.setContent(weixinArticle.getContent());
		article.setStaticName(ArticleHelper.getStaticName(DateTimeUtil.now(), weixinArticle.getTitle()));
		article.setSummary(weixinArticle.getSummary());
		if (StringUtils.isNotEmpty(weixinArticle.getThumbnail())) {
			article.setIsImg(1);
		}
		article.setKeyword("");
		article.setThumbnail(weixinArticle.getThumbnail());
		article.setTitle(weixinArticle.getTitle());
		article.setCreatedTime(DateTimeUtil.now());
		article.setPublisher(weixinArticle.getPublisher());
		articleService.add(article);

		weixinArticle.setStatus(1);
		update(weixinArticle);

		return true;
	}

}
