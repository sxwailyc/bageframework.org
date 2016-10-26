package com.bageframework.demo.web.service;

import java.util.List;

import com.bageframework.demo.web.vo.MenuVO;
import com.bageframework.demo.web.model.Menu;
import com.bageframework.demo.web.vo.admin.MenuAdminVO;
import com.bageframework.service.IService;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface MenuService extends IService<Menu, MenuVO, MenuAdminVO, Integer> {

	public List<MenuAdminVO> getAdminVOList();

}
