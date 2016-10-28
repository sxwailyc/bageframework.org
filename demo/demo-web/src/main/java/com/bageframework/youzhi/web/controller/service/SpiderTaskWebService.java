package com.bageframework.youzhi.web.controller.service;

import com.bageframework.dao.beans.Query;
import com.bageframework.youzhi.web.model.SpiderTask;
import com.bageframework.youzhi.web.vo.admin.SpiderTaskAdminVO;
import com.bageframework.dao.beans.Page;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface SpiderTaskWebService{

    public Page<SpiderTaskAdminVO> page(Query query, int pageNo, int pageSize);

	public SpiderTask get(Integer id);

	public boolean update(SpiderTask bean);

	public boolean add(SpiderTask bean);

	public boolean delete(Integer id);
}
