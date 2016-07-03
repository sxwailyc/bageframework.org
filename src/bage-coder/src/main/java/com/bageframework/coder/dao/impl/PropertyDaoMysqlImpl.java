package com.bageframework.coder.dao.impl;

import org.springframework.stereotype.Repository;

import com.bageframework.coder.dao.PropertyDao;
import com.bageframework.coder.model.Property;
import com.bageframework.dao.base.mysql.BaseMysqlDao;

@Repository
public class PropertyDaoMysqlImpl extends BaseMysqlDao<Property> implements PropertyDao {

}
