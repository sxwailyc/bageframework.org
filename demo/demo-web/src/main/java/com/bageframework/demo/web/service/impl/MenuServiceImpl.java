package com.bageframework.demo.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.dao.base.IDAO;
import com.bageframework.demo.web.dao.MenuDao;
import com.bageframework.demo.web.model.Menu;
import com.bageframework.demo.web.service.MenuService;
import com.bageframework.demo.web.vo.MenuVO;
import com.bageframework.demo.web.vo.admin.MenuAdminVO;
import com.bageframework.service.base.BaseService;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */

@Service
public class MenuServiceImpl extends BaseService<Menu, MenuVO, MenuAdminVO, Integer> implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public IDAO<Menu, Integer> getDao() {
		return menuDao;
	}

	@Override
	public MenuVO model2Vo(Menu bean) {
		return null;
	}

	@Override
	public MenuAdminVO model2AdminVo(Menu bean) {
		return MenuAdminVO.create(bean);
	}

	/**
	 * 
	 * @param depth
	 * @return
	 */
	private List<MenuAdminVO> getAdminVOList(int parentId, int depth) {
		List<Menu> list = menuDao.getList(parentId, 0, Integer.MAX_VALUE);
		List<MenuAdminVO> voList = new ArrayList<MenuAdminVO>();
		for (Menu menu : list) {
			MenuAdminVO vo = MenuAdminVO.create(menu);
			vo.setDepth(depth);
			if (depth <= 2) {
				vo.getNodes().addAll(this.getAdminVOList(menu.getId(), depth + 1));
			}
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public List<MenuAdminVO> getAdminVOList() {
		return getAdminVOList(0, 0);
	}

}
