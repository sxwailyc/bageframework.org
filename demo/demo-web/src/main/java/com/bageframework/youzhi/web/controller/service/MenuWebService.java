package com.bageframework.youzhi.web.controller.service;

import java.util.List;

import com.bageframework.dao.beans.Page;
import com.bageframework.dao.beans.Query;
import com.bageframework.youzhi.web.model.Menu;
import com.bageframework.youzhi.web.vo.admin.MenuAdminVO;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface MenuWebService {

	public Page<MenuAdminVO> page(Query query, int pageNo, int pageSize);

	public Menu get(Integer id);

	public boolean update(Menu bean);

	public boolean add(Menu bean);

	public boolean delete(Integer id);

	public List<MenuAdminVO> getList();
}
