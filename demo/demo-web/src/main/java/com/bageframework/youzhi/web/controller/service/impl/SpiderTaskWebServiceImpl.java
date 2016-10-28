package com.bageframework.youzhi.web.controller.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.bageframework.service.IService;
import com.bageframework.service.web.base.BaseWebService;
import com.bageframework.youzhi.web.controller.service.SpiderTaskWebService;
import com.bageframework.youzhi.web.model.SpiderTask;
import com.bageframework.youzhi.web.service.SpiderTaskService;
import com.bageframework.youzhi.web.vo.SpiderTaskVO;
import com.bageframework.youzhi.web.vo.admin.SpiderTaskAdminVO;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Service
public class SpiderTaskWebServiceImpl extends BaseWebService<SpiderTask, SpiderTaskVO, SpiderTaskAdminVO, Integer> implements SpiderTaskWebService {
   
    @Autowired
	private SpiderTaskService spiderTaskService;
	
	@Override
	public IService<SpiderTask, SpiderTaskVO, SpiderTaskAdminVO, Integer> getService() {
		return spiderTaskService;
	}
}

