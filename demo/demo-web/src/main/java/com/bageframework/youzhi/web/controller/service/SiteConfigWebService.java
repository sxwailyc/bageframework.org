package com.bageframework.demo.web.controller.service;

import com.bageframework.demo.web.model.SiteConfig;
import com.bageframework.dao.beans.Query;
import com.bageframework.dao.beans.Page;
import com.bageframework.demo.web.vo.admin.SiteConfigAdminVO;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface SiteConfigWebService{

    public Page<SiteConfigAdminVO> page(Query query, int pageNo, int pageSize);

	public SiteConfig get(Integer id);

	public boolean update(SiteConfig bean);

	public boolean add(SiteConfig bean);

	public boolean delete(Integer id);
}
