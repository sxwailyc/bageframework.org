package com.bageframework.dao.base.mysql;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bageframework.dao.beans.Page;
import com.bageframework.dao.beans.QueryFilter;
import com.bageframework.dao.helper.DBHelper;
import com.bageframework.dao.jdbc.Jdbc;
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
		SelectSQL select = DBHelper.createGetSql(entityClass, id);
		return jdbc.get(select.getSql(), entityClass, select.getParams());
	}

	public T get(Integer id) {
		return null;
	}

	public boolean delete(Integer key) {
		return false;
	}

	public boolean delete(String key) {
		return false;
	}

	public boolean delete(String table, String key) {
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> getList(int parentId, int start, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
