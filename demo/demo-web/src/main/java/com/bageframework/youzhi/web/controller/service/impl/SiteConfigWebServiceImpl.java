package com.bageframework.youzhi.web.controller.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bageframework.service.IService;
import com.bageframework.service.web.base.BaseWebService;
import com.bageframework.youzhi.web.controller.service.SiteConfigWebService;
import com.bageframework.youzhi.web.model.SiteConfig;
import com.bageframework.youzhi.web.service.SiteConfigService;
import com.bageframework.youzhi.web.vo.SiteConfigVO;
import com.bageframework.youzhi.web.vo.admin.SiteConfigAdminVO;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Service
public class SiteConfigWebServiceImpl extends BaseWebService<SiteConfig, SiteConfigVO, SiteConfigAdminVO, Integer> implements SiteConfigWebService {
   
    @Autowired
	private SiteConfigService siteConfigService;
	
	@Override
	public IService<SiteConfig, SiteConfigVO, SiteConfigAdminVO, Integer> getService() {
		return siteConfigService;
	}
}

