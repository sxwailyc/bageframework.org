package com.bageframework.demo.web.controller.service.impl;

import com.bageframework.demo.web.vo.SpiderTaskVO;
import com.bageframework.demo.web.controller.service.SpiderTaskWebService;
import org.springframework.stereotype.Service;
import com.bageframework.demo.web.vo.admin.SpiderTaskAdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import com.bageframework.service.IService;
import com.bageframework.service.web.base.BaseWebService;
import com.bageframework.demo.web.model.SpiderTask;
import com.bageframework.demo.web.service.SpiderTaskService;

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

