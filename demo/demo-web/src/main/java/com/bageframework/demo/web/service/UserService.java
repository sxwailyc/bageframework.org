package com.bageframework.demo.web.service;

import com.bageframework.demo.web.model.User;
import com.bageframework.demo.web.vo.UserVO;
import com.bageframework.demo.web.vo.admin.UserAdminVO;
import com.bageframework.service.IService;

public interface UserService extends IService<User, UserVO, UserAdminVO, String> {

	public User login(String username, String password);
}
