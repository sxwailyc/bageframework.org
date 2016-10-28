package com.bageframework.youzhi.web.service.impl;

import com.bageframework.dao.base.IDAO;
import org.springframework.stereotype.Service;

import com.bageframework.service.base.BaseService;
import com.bageframework.youzhi.web.dao.SpiderTaskDao;
import com.bageframework.youzhi.web.model.SpiderTask;
import com.bageframework.youzhi.web.service.SpiderTaskService;
import com.bageframework.youzhi.web.vo.SpiderTaskVO;
import com.bageframework.youzhi.web.vo.admin.SpiderTaskAdminVO;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
 
@Service
public class SpiderTaskServiceImpl extends BaseService<SpiderTask, SpiderTaskVO, SpiderTaskAdminVO, Integer> implements SpiderTaskService {

	@Autowired
	private SpiderTaskDao spiderTaskDaoDao;

	@Override
	public IDAO<SpiderTask, Integer> getDao() {
		return spiderTaskDaoDao;
	}

	@Override
	public SpiderTaskVO model2Vo(SpiderTask bean) {
		return null;
	}

	@Override
	public SpiderTaskAdminVO model2AdminVo(SpiderTask bean) {
		return SpiderTaskAdminVO.create(bean);
	}

}
