package com.bageframework.demo.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.dao.base.IDAO;
import com.bageframework.demo.web.dao.UserDao;
import com.bageframework.demo.web.model.User;
import com.bageframework.demo.web.service.UserService;
import com.bageframework.demo.web.vo.UserVO;
import com.bageframework.demo.web.vo.admin.UserAdminVO;
import com.bageframework.service.base.BaseService;

@Service
public class UserServiceImpl extends BaseService<User, UserVO, UserAdminVO, String> implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public IDAO<User, String> getDao() {
		return userDao;
	}

	@Override
	public UserVO model2Vo(User bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAdminVO model2AdminVo(User bean) {
		return UserAdminVO.create(bean);
	}

}
