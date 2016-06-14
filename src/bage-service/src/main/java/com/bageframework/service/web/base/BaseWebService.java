package com.bageframework.service.web.base;

import com.bageframework.dao.beans.Page;
import com.bageframework.dao.beans.Query;
import com.bageframework.service.IService;
import com.bageframework.util.PageUtil;

public abstract class BaseWebService<BEAN, VO_BEAN, ADMIN_VO_BEAN, KEYTYPE> {

	public abstract IService<BEAN, VO_BEAN, ADMIN_VO_BEAN, KEYTYPE> getService();

	public Page<ADMIN_VO_BEAN> page(Query query, int pageNo, int pageSize) {
		int start = PageUtil.getPageStart(pageNo, pageSize);
		return getService().getPage(query, start, pageSize);
	}

	public BEAN get(KEYTYPE key) {
		return getService().get(key);
	}

	public boolean update(BEAN bean) {
		return getService().update(bean);
	}

	public boolean add(BEAN bean) {
		return getService().add(bean);
	}

	public boolean delete(KEYTYPE key) {
		return getService().delete(key);
	}

}
