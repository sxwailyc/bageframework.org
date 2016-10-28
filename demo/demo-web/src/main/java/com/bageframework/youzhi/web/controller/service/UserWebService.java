package com.bageframework.youzhi.web.controller.service;

import com.bageframework.dao.beans.Page;
import com.bageframework.dao.beans.Query;
import com.bageframework.youzhi.web.model.User;
import com.bageframework.youzhi.web.vo.admin.UserAdminVO;

public interface UserWebService {

	public Page<UserAdminVO> page(Query query, int pageNo, int pageSize);

	public User get(String id);

	public boolean update(User user);

	public boolean add(User user);

	public boolean delete(String id);
}
