package com.bageframework.dao.base;

import java.util.List;

import com.bageframework.dao.beans.Page;
import com.bageframework.dao.beans.Query;

public interface IDAO<BEAN, KEYTYPE> {

	public boolean add(BEAN bean);

	public boolean update(BEAN bean);

	public BEAN get(KEYTYPE key);

	public boolean delete(KEYTYPE key);

	public Page<BEAN> getPage(int start, int size);

	public Page<BEAN> getPage(int parentId, int start, int size);

	public Page<BEAN> getPage(Query filter, int start, int size);

	public List<BEAN> getList(int start, int size);

	public List<BEAN> getList(int parentId, int start, int size);
}
