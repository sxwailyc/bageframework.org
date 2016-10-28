package com.bageframework.youzhi.web.controller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.service.IService;
import com.bageframework.service.web.base.BaseWebService;
import com.bageframework.youzhi.web.controller.service.UserWebService;
import com.bageframework.youzhi.web.model.User;
import com.bageframework.youzhi.web.service.UserService;
import com.bageframework.youzhi.web.vo.UserVO;
import com.bageframework.youzhi.web.vo.admin.UserAdminVO;

@Service
public class UserWebServiceImpl extends BaseWebService<User, UserVO, UserAdminVO, String> implements UserWebService {

	@Autowired
	private UserService userService;

	@Override
	public IService<User, UserVO, UserAdminVO, String> getService() {
		return userService;
	}

}
