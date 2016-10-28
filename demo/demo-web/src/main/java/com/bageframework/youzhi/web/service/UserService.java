package com.bageframework.youzhi.web.service;

import com.bageframework.service.IService;
import com.bageframework.youzhi.web.model.User;
import com.bageframework.youzhi.web.vo.UserVO;
import com.bageframework.youzhi.web.vo.admin.UserAdminVO;

public interface UserService extends IService<User, UserVO, UserAdminVO, String> {

	public User login(String username, String password);
}
