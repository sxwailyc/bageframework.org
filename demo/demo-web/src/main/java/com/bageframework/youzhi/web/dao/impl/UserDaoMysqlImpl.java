package com.bageframework.youzhi.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.bageframework.dao.base.mysql.BaseMysqlDao;
import com.bageframework.youzhi.web.dao.UserDao;
import com.bageframework.youzhi.web.model.User;

@Repository
public class UserDaoMysqlImpl extends BaseMysqlDao<User> implements UserDao {

}
