package com.bageframework.youzhi.web.controller.service;

import com.bageframework.dao.beans.Query;
import com.bageframework.youzhi.web.model.SiteConfig;
import com.bageframework.youzhi.web.vo.admin.SiteConfigAdminVO;
import com.bageframework.dao.beans.Page;

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
