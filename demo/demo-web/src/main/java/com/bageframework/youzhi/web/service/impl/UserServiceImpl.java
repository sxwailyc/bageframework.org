package com.bageframework.youzhi.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.dao.base.IDAO;
import com.bageframework.service.base.BaseService;
import com.bageframework.youzhi.web.dao.UserDao;
import com.bageframework.youzhi.web.model.User;
import com.bageframework.youzhi.web.service.UserService;
import com.bageframework.youzhi.web.vo.UserVO;
import com.bageframework.youzhi.web.vo.admin.UserAdminVO;

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

	@Override
	public User login(String username, String password) {
		User user = userDao.get("username", username);
		return user;
	}

}
