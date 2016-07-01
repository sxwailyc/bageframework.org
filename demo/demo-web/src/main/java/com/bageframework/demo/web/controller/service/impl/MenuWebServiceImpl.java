package com.bageframework.demo.web.controller.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.demo.web.controller.service.MenuWebService;
import com.bageframework.demo.web.model.Menu;
import com.bageframework.demo.web.service.MenuService;
import com.bageframework.demo.web.vo.MenuVO;
import com.bageframework.demo.web.vo.admin.MenuAdminVO;
import com.bageframework.service.IService;
import com.bageframework.service.web.base.BaseWebService;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Service
public class MenuWebServiceImpl extends BaseWebService<Menu, MenuVO, MenuAdminVO, Integer> implements MenuWebService {

	@Autowired
	private MenuService menuService;

	@Override
	public IService<Menu, MenuVO, MenuAdminVO, Integer> getService() {
		return menuService;
	}

	@Override
	public List<MenuAdminVO> getList() {
		return menuService.getAdminVOList();
	}
}
