package com.bageframework.demo.web.controller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.demo.web.controller.service.UserWebService;
import com.bageframework.demo.web.model.User;
import com.bageframework.demo.web.service.UserService;
import com.bageframework.demo.web.vo.UserVO;
import com.bageframework.demo.web.vo.admin.UserAdminVO;
import com.bageframework.service.IService;
import com.bageframework.service.web.base.BaseWebService;

@Service
public class UserWebServiceImpl extends BaseWebService<User, UserVO, UserAdminVO, String> implements UserWebService {

	@Autowired
	private UserService userService;

	@Override
	public IService<User, UserVO, UserAdminVO, String> getService() {
		return userService;
	}

}
