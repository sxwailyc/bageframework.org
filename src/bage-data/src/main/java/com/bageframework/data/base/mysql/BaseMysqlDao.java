package com.bageframework.data.base.mysql;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bageframework.data.DB;
import com.bageframework.data.beans.Page;
import com.bageframework.data.beans.Query;
import com.bageframework.data.beans.QueryItem;
import com.bageframework.data.helper.SQLHelper;
import com.bageframework.data.jdbc.Jdbc;
import com.bageframework.data.jdbc.SqlParameter;
import com.bageframework.data.sql.DeleteSQL;
import com.bageframework.data.sql.SelectSQL;

public class BaseMysqlDao<T> {

	protected static Log logger = LogFactory.getLog(BaseMysqlDao.class);

	private final DB db = DB.MYSQL;

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
		return jdbc.get(select.getSql(db), entityClass, select.getParams());
	}

	public T get(String keyName, String key) {
		SelectSQL select = SQLHelper.createGetSqlWithKeyName(entityClass, keyName, key);
		return jdbc.get(select.getSql(db), entityClass, select.getParams());
	}

	public T get(Integer id) {
		SelectSQL select = SQLHelper.createGetSql(entityClass, id);
		return jdbc.get(select.getSql(db), entityClass, select.getParams());
	}

	public boolean delete(Integer key) {
		DeleteSQL delete = SQLHelper.createDeleteSql(entityClass, key);
		return jdbc.update(delete.getSql(db), delete.getParams()) > 0;
	}

	public boolean delete(String key) {
		DeleteSQL delete = SQLHelper.createDeleteSql(entityClass, key);
		return jdbc.update(delete.getSql(db), delete.getParams()) > 0;
	}

	public boolean delete(String table, String key) {
		DeleteSQL delete = SQLHelper.createDeleteSql(entityClass, table, key);
		return jdbc.update(delete.getSql(db), delete.getParams()) > 0;
	}

	public Page<T> getPage(int start, int size) {
		SelectSQL select = SQLHelper.createQueryListSql(entityClass);
		select.limit(start, size);
		List<T> data = jdbc.getList(select.getSql(db), entityClass, select.getParams());
		int count = jdbc.getInt(select.getCountSql(db), select.getParams());
		return new Page<T>(data, count);
	}

	public Page<T> getPage(int parentId, int start, int size) {
		SelectSQL select = SQLHelper.createQueryListSql(entityClass);
		String parentColumn = SQLHelper.getParentColumn(entityClass);
		select.equal(parentColumn, parentId);
		select.limit(start, size);
		List<T> data = jdbc.getList(select.getSql(db), entityClass, select.getParams());
		int count = jdbc.getInt(select.getCountSql(db), select.getParams());
		return new Page<T>(data, count);
	}

	public Page<T> getPage(Query query, int start, int size) {
		SelectSQL select = SQLHelper.createQueryListSql(entityClass);
		select.limit(start, size);
		Iterator<QueryItem> iterator = query.iterator();
		while (iterator.hasNext()) {
			QueryItem queryItem = iterator.next();
			select.condition(queryItem.getColumn(), queryItem.getOperate(), queryItem.getValue());
		}
		List<T> data = jdbc.getList(select.getSql(db), entityClass, select.getParams());
		int count = jdbc.getInt(select.getCountSql(db), select.getParams());
		return new Page<T>(data, count);
	}

	public int getCount(int parentId) {
		SelectSQL select = SQLHelper.createQueryListSql(entityClass);
		String parentColumn = SQLHelper.getParentColumn(entityClass);
		select.equal(parentColumn, parentId);
		int count = jdbc.getInt(select.getCountSql(db), select.getParams());
		return count;
	}

	public int getCount() {
		SelectSQL select = SQLHelper.createQueryListSql(entityClass);
		int count = jdbc.getInt(select.getCountSql(db), select.getParams());
		return count;
	}

	public List<T> getList(int start, int size) {
		SelectSQL select = SQLHelper.createQueryListSql(entityClass);
		select.limit(start, size);
		return jdbc.getList(select.getSql(db), entityClass, select.getParams());
	}

	public List<T> getList(int parentId, int start, int size) {
		SelectSQL select = SQLHelper.createQueryListSql(entityClass);
		String parentColumn = SQLHelper.getParentColumn(entityClass);
		select.equal(parentColumn, parentId);
		select.limit(start, size);
		return jdbc.getList(select.getSql(db), entityClass, select.getParams());
	}

	public List<T> getList() {
		return this.getList(0, Integer.MAX_VALUE);
	}

	public List<T> getList(int parentId) {
		return this.getList(parentId, 0, Integer.MAX_VALUE);
	}

	public List<T> getList(String parentId, int start, int size) {
		SelectSQL select = SQLHelper.createQueryListSql(entityClass);
		String parentColumn = SQLHelper.getParentColumn(entityClass);
		select.equal(parentColumn, parentId);
		select.limit(start, size);
		return jdbc.getList(select.getSql(db), entityClass, select.getParams());
	}

	public List<T> getList(String parentId) {
		return this.getList(parentId, 0, Integer.MAX_VALUE);
	}

	public int getMaxId() {
		String table = SQLHelper.getTable(entityClass);
		String keyColumn = SQLHelper.getKeyColumnName(entityClass);
		keyColumn = keyColumn == null ? "id" : keyColumn;
		String sql = "SELECT MAX(" + keyColumn + ") FROM " + table;
		if (db == DB.MYSQL) {
			sql = sql.replaceAll("@\\[", "`").replaceAll("@\\]", "`");
		}
		return jdbc.getInt(sql, new SqlParameter());
	}

}
