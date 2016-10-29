package com.bageframework.data;

import org.springframework.stereotype.Repository;

import com.bageframework.data.base.mysql.BaseMysqlDao;
import com.bageframework.model.User;

@Repository
public class UserDaoMysqlImpl extends BaseMysqlDao<User> implements UserDao {

}
