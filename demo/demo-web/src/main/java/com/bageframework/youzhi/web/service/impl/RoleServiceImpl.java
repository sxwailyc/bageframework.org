package com.bageframework.youzhi.web.service.impl;

import com.bageframework.service.base.BaseService;
import com.bageframework.youzhi.web.dao.RoleDao;
import com.bageframework.youzhi.web.model.Role;
import com.bageframework.youzhi.web.service.RoleService;
import com.bageframework.youzhi.web.vo.RoleVO;
import com.bageframework.youzhi.web.vo.admin.RoleAdminVO;

import org.springframework.stereotype.Service;

import com.bageframework.dao.base.IDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
 
@Service
public class RoleServiceImpl extends BaseService<Role, RoleVO, RoleAdminVO, String> implements RoleService {

	@Autowired
	private RoleDao roleDaoDao;

	@Override
	public IDAO<Role, String> getDao() {
		return roleDaoDao;
	}

	@Override
	public RoleVO model2Vo(Role bean) {
		return null;
	}

	@Override
	public RoleAdminVO model2AdminVo(Role bean) {
		return RoleAdminVO.create(bean);
	}

}
