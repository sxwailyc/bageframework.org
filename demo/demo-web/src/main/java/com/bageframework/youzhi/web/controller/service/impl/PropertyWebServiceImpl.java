package com.bageframework.youzhi.web.controller.service.impl;

import com.bageframework.service.web.base.BaseWebService;
import com.bageframework.youzhi.web.controller.service.PropertyWebService;
import com.bageframework.youzhi.web.model.Property;
import com.bageframework.youzhi.web.service.PropertyService;
import com.bageframework.youzhi.web.vo.PropertyVO;
import com.bageframework.youzhi.web.vo.admin.PropertyAdminVO;
import com.bageframework.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Service
public class PropertyWebServiceImpl extends BaseWebService<Property, PropertyVO, PropertyAdminVO, Integer> implements PropertyWebService {
   
    @Autowired
	private PropertyService propertyService;
	
	@Override
	public IService<Property, PropertyVO, PropertyAdminVO, Integer> getService() {
		return propertyService;
	}
}

