package com.bageframework.service.base;

import java.util.ArrayList;
import java.util.List;

import com.bageframework.data.base.IDAO;
import com.bageframework.data.beans.Page;
import com.bageframework.data.beans.Query;
import com.bageframework.service.IService;

/**
 * 
 * @author shixiangwen03@gmail.com
 * 
 * @param <BEAN>
 * @param <VO_BEAN>
 * @param <ADMIN_VO_BEAN>
 * @param <KEYTYPE>
 */
public abstract class BaseService<BEAN, VO_BEAN, ADMIN_VO_BEAN, KEYTYPE> implements IService<BEAN, VO_BEAN, ADMIN_VO_BEAN, KEYTYPE> {

	public abstract IDAO<BEAN, KEYTYPE> getDao();

	public abstract VO_BEAN model2Vo(BEAN bean);

	public abstract ADMIN_VO_BEAN model2AdminVo(BEAN bean);

	@Override
	public boolean add(BEAN bean) {
		return getDao().add(bean);
	}

	@Override
	public boolean update(BEAN bean) {
		return getDao().update(bean);
	}

	@Override
	public BEAN get(KEYTYPE key) {
		return getDao().get(key);
	}

	@Override
	public boolean delete(KEYTYPE key) {
		return getDao().delete(key);
	}

	@Override
	public List<BEAN> getList(int start, int size) {
		return getDao().getList(start, size);
	}

	@Override
	public List<BEAN> getList() {
		return this.getList(0, Integer.MAX_VALUE);
	}

	@Override
	public List<BEAN> getList(int parentId, int start, int size) {
		return getDao().getList(parentId, start, size);
	}

	@Override
	public List<VO_BEAN> getVOList(int start, int size) {
		return getVOList(0, start, size);
	}

	@Override
	public List<VO_BEAN> getVOList() {
		return getVOList(0, Integer.MAX_VALUE);
	}

	@Override
	public List<VO_BEAN> getVOList(int parentId, int start, int size) {
		List<VO_BEAN> voList = new ArrayList<VO_BEAN>();
		List<BEAN> list = null;
		if (parentId > 0) {
			list = getDao().getList(parentId, start, size);
		} else {
			list = getDao().getList(start, size);
		}
		for (BEAN bean : list) {
			VO_BEAN vo = model2Vo(bean);
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public int getCount(int parentId) {
		if (parentId > 0) {
			return getDao().getCount(parentId);
		} else {
			return getDao().getCount();
		}
	}

	@Override
	public Page<ADMIN_VO_BEAN> getPage(int start, int size) {
		Page<BEAN> page = getDao().getPage(start, size);
		return this.getPage(page);
	}

	@Override
	public Page<ADMIN_VO_BEAN> getPage(int parentid, int start, int size) {
		Page<BEAN> page = getDao().getPage(parentid, start, size);
		return this.getPage(page);
	}

	@Override
	public Page<ADMIN_VO_BEAN> getPage(Page<BEAN> page) {

		List<ADMIN_VO_BEAN> voList = new ArrayList<ADMIN_VO_BEAN>();
		List<BEAN> list = page.getData();
		for (int i = 0; i < list.size(); i++) {
			BEAN bean = list.get(i);
			ADMIN_VO_BEAN vo = model2AdminVo(bean);
			voList.add(vo);
		}

		Page<ADMIN_VO_BEAN> p = new Page<ADMIN_VO_BEAN>(voList, page.getCount());
		return p;
	}

	@Override
	public Page<ADMIN_VO_BEAN> getPage(Query query, int start, int size) {
		Page<BEAN> page = getDao().getPage(query, start, size);
		return this.getPage(page);
	}
}
