package com.bageframework.demo.web.service.impl;

import com.bageframework.demo.web.dao.PropertyDao;
import com.bageframework.demo.web.model.Property;
import com.bageframework.service.base.BaseService;
import com.bageframework.demo.web.vo.admin.PropertyAdminVO;
import com.bageframework.demo.web.service.PropertyService;
import com.bageframework.demo.web.vo.PropertyVO;
import org.springframework.stereotype.Service;
import com.bageframework.dao.base.IDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
 
@Service
public class PropertyServiceImpl extends BaseService<Property, PropertyVO, PropertyAdminVO, Integer> implements PropertyService {

	@Autowired
	private PropertyDao propertyDaoDao;

	@Override
	public IDAO<Property, Integer> getDao() {
		return propertyDaoDao;
	}

	@Override
	public PropertyVO model2Vo(Property bean) {
		return null;
	}

	@Override
	public PropertyAdminVO model2AdminVo(Property bean) {
		return PropertyAdminVO.create(bean);
	}

}
