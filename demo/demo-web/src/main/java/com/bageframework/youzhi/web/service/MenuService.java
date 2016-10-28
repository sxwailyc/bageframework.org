package com.bageframework.youzhi.web.service;

import java.util.List;

import com.bageframework.service.IService;
import com.bageframework.youzhi.web.model.Menu;
import com.bageframework.youzhi.web.vo.MenuVO;
import com.bageframework.youzhi.web.vo.admin.MenuAdminVO;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface MenuService extends IService<Menu, MenuVO, MenuAdminVO, Integer> {

	public List<MenuAdminVO> getAdminVOList();

}
