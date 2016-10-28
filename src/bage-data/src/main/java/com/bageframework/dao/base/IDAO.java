package com.bageframework.dao.base;

import java.util.List;

import com.bageframework.dao.beans.Page;
import com.bageframework.dao.beans.Query;

public interface IDAO<BEAN, KEYTYPE> {

	public boolean add(BEAN bean);

	public boolean update(BEAN bean);

	public BEAN get(KEYTYPE key);

	public BEAN get(String keyName, String key);

	public boolean delete(KEYTYPE key);

	public Page<BEAN> getPage(int start, int size);

	public Page<BEAN> getPage(int parentId, int start, int size);

	public Page<BEAN> getPage(Query query, int start, int size);

	public List<BEAN> getList(int start, int size);

	public List<BEAN> getList(int parentId, int start, int size);

	public List<BEAN> getList(int parentId);

	public int getCount();

	public int getCount(int parentId);

	public List<BEAN> getList(String parentId);

	public List<BEAN> getList();
}
