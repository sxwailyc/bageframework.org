package com.bageframework.demo.web.controller.service;

import com.bageframework.dao.beans.Query;
import com.bageframework.demo.web.vo.admin.ArticleAdminVO;
import com.bageframework.dao.beans.Page;
import com.bageframework.demo.web.model.Article;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface ArticleWebService{

    public Page<ArticleAdminVO> page(Query query, int pageNo, int pageSize);

	public Article get(Integer id);

	public boolean update(Article bean);

	public boolean add(Article bean);

	public boolean delete(Integer id);
}
