package com.bageframework.youzhi.web.service.impl;

import com.bageframework.service.base.BaseService;
import com.bageframework.youzhi.web.dao.PropertyDao;
import com.bageframework.youzhi.web.model.Property;
import com.bageframework.youzhi.web.service.PropertyService;
import com.bageframework.youzhi.web.vo.PropertyVO;
import com.bageframework.youzhi.web.vo.admin.PropertyAdminVO;

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
