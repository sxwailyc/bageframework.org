package com.bageframework.demo.web.service.impl;

import com.bageframework.demo.web.vo.admin.RoleAdminVO;
import com.bageframework.demo.web.model.Role;
import com.bageframework.service.base.BaseService;
import com.bageframework.demo.web.dao.RoleDao;
import com.bageframework.demo.web.vo.RoleVO;
import org.springframework.stereotype.Service;
import com.bageframework.demo.web.service.RoleService;
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
