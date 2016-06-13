package com.bageframework.dao.base.mysql;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bageframework.dao.beans.Page;
import com.bageframework.dao.beans.QueryFilter;
import com.bageframework.dao.helper.SQLHelper;
import com.bageframework.dao.jdbc.Jdbc;
import com.bageframework.dao.sql.DeleteSQL;
import com.bageframework.dao.sql.SelectSQL;

public class BaseMysqlDao<T> {

	protected static Log logger = LogFactory.getLog(BaseMysqlDao.class);

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public BaseMysqlDao() {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Autowired
	private Jdbc jdbc;

	public boolean add(T t) {
		return jdbc.insert(t) > 0;
	}

	public boolean update(T t) {
		return jdbc.update(t) > 0;
	}

	public T get(String id) {
		SelectSQL select = SQLHelper.createGetSql(entityClass, id);
		return jdbc.get(select.getSql(), entityClass, select.getParams());
	}

	public T get(Integer id) {
		SelectSQL select = SQLHelper.createGetSql(entityClass, id);
		return jdbc.get(select.getSql(), entityClass, select.getParams());
	}

	public boolean delete(Integer key) {
		DeleteSQL delete = SQLHelper.createDeleteSql(entityClass, key);
		return jdbc.update(delete.getSql(), delete.getParams()) > 0;
	}

	public boolean delete(String key) {
		DeleteSQL delete = SQLHelper.createDeleteSql(entityClass, key);
		return jdbc.update(delete.getSql(), delete.getParams()) > 0;
	}

	public boolean delete(String table, String key) {
		DeleteSQL delete = SQLHelper.createDeleteSql(entityClass, table, key);
		return jdbc.update(delete.getSql(), delete.getParams()) > 0;
	}

	public Page<T> getPage(int start, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<T> getPage(int parentId, int start, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<T> getPage(QueryFilter filter, int start, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> getList(int start, int size) {
		SelectSQL select = SQLHelper.createQueryListSql(entityClass);
		select.limit(start, size);
		return jdbc.getList(select.getSql(), entityClass, select.getParams());
	}

	public List<T> getList(int parentId, int start, int size) {
		SelectSQL select = SQLHelper.createQueryListSql(entityClass);
		String parentColumn = SQLHelper.getParentColumn(entityClass);
		select.equal(parentColumn, parentId);
		select.limit(start, size);
		return jdbc.getList(select.getSql(), entityClass, select.getParams());
	}

}
