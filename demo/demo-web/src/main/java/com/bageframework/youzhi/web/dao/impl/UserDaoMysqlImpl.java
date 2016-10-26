package com.bageframework.demo.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.bageframework.dao.base.mysql.BaseMysqlDao;
import com.bageframework.demo.web.dao.UserDao;
import com.bageframework.demo.web.model.User;

@Repository
public class UserDaoMysqlImpl extends BaseMysqlDao<User> implements UserDao {

}
