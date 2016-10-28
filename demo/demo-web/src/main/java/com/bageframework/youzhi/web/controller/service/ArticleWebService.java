package com.bageframework.youzhi.web.controller.service;

import java.util.Date;

import com.bageframework.dao.beans.Page;
import com.bageframework.dao.beans.Query;
import com.bageframework.youzhi.web.model.Article;
import com.bageframework.youzhi.web.vo.admin.ArticleAdminVO;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface ArticleWebService {

	public Page<ArticleAdminVO> page(Query query, int pageNo, int pageSize);

	public Article get(Integer id);

	public boolean update(Article bean);

	public boolean add(Article bean);

	public boolean delete(Integer id);

	public String getStaticName(Date createdTime, String title);
}
