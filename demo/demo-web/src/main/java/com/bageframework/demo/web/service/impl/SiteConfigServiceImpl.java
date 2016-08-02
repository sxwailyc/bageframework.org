package com.bageframework.demo.web.service.impl;

import com.bageframework.demo.web.model.SiteConfig;
import com.bageframework.demo.web.vo.SiteConfigVO;
import com.bageframework.dao.base.IDAO;
import org.springframework.stereotype.Service;
import com.bageframework.demo.web.dao.SiteConfigDao;
import com.bageframework.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.bageframework.demo.web.vo.admin.SiteConfigAdminVO;
import com.bageframework.demo.web.service.SiteConfigService;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */

@Service
public class SiteConfigServiceImpl extends BaseService<SiteConfig, SiteConfigVO, SiteConfigAdminVO, Integer>
		implements SiteConfigService {

	@Autowired
	private SiteConfigDao siteConfigDaoDao;

	@Override
	public IDAO<SiteConfig, Integer> getDao() {
		return siteConfigDaoDao;
	}

	@Override
	public SiteConfigVO model2Vo(SiteConfig bean) {
		return null;
	}

	@Override
	public SiteConfigAdminVO model2AdminVo(SiteConfig bean) {
		return SiteConfigAdminVO.create(bean);
	}

	@Override
	public SiteConfig get(Integer key) {
		SiteConfig siteConfig = siteConfigDaoDao.get(key);
		if (siteConfig == null) {
			siteConfig = new SiteConfig();
			siteConfig.setId(key);
			siteConfig.setDescription("网站描述");
			siteConfig.setDomain("网站域名");
			siteConfig.setName("网站名称");
			siteConfig.setTitle("网站标题");
			siteConfig.setKeyword("网站关键字");
			add(siteConfig);
		}
		return siteConfig;
	}

}
