package com.bageframework.demo.web.controller.service.impl;

import com.bageframework.demo.web.model.SiteConfig;
import com.bageframework.demo.web.vo.SiteConfigVO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.bageframework.demo.web.vo.admin.SiteConfigAdminVO;
import com.bageframework.service.IService;
import com.bageframework.service.web.base.BaseWebService;
import com.bageframework.demo.web.service.SiteConfigService;
import com.bageframework.demo.web.controller.service.SiteConfigWebService;

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

