package com.bageframework.demo.web.controller.service;

import com.bageframework.dao.beans.Query;
import com.bageframework.demo.web.vo.admin.WeixinArticleAdminVO;
import com.bageframework.dao.beans.Page;
import com.bageframework.demo.web.model.WeixinArticle;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface WeixinArticleWebService {

	public Page<WeixinArticleAdminVO> page(Query query, int pageNo, int pageSize);

	public WeixinArticle get(Integer id);

	public boolean update(WeixinArticle bean);

	public boolean add(WeixinArticle bean);

	public boolean delete(Integer id);

	public boolean syncToArticle(int id, int categoryId);
}
