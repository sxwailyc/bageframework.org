package com.bageframework.data.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bageframework.data.annotation.AutoDate;
import com.bageframework.data.annotation.AutoUUID;
import com.bageframework.data.annotation.Ignore;
import com.bageframework.data.annotation.IgnoreInsert;
import com.bageframework.data.annotation.IgnoreUpdate;
import com.bageframework.data.annotation.OrderAsc;
import com.bageframework.data.annotation.OrderDesc;
import com.bageframework.data.annotation.ParentID;
import com.bageframework.data.annotation.PrimaryKey;
import com.bageframework.data.annotation.TableHash;
import com.bageframework.data.beans.Query;
import com.bageframework.data.exception.ParentIdFieldNotFoundException;
import com.bageframework.data.exception.PrimaryKeyNotFoundException;
import com.bageframework.data.sql.DeleteSQL;
import com.bageframework.data.sql.InsertSQL;
import com.bageframework.data.sql.Order;
import com.bageframework.data.sql.SelectSQL;
import com.bageframework.data.sql.UpdateSQL;
import com.bageframework.util.DateTimeUtil;
import com.bageframework.util.UUIDGenerator;

public class SQLHelper {

	protected static Log logger = LogFactory.getLog(SQLHelper.class);

	/**
	 * 分表字段缓存
	 */
	private static Map<Class<?>, String> SPLIT_TABLE_CACHE = new ConcurrentHashMap<Class<?>, String>();

	public static SelectSQL createGetSql(Class<?> cls, Object id) {
		String table = getTable(cls);
		return createGetSql(cls, table, id);
	}

	public static SelectSQL createGetSqlWithKeyName(Class<?> cls, String keyName, Object key) {
		String table = getTable(cls);
		SelectSQL sql = SelectSQL.create(table);
		sql.equal(keyName, key);
		return sql;
	}

	public static SelectSQL createGetSql(Class<?> cls, String table, Object id) {

		SelectSQL sql = SelectSQL.create(table);

		Field[] fields = cls.getDeclaredFields();

		boolean hasPrimaryKey = false;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(PrimaryKey.class)) {
				sql.equal(DBHelper.fieldName2ColumnName(field.getName()), id);
				// 主键只有一个
				hasPrimaryKey = true;
				break;
			}
		}

		if (!hasPrimaryKey) {
			throw new PrimaryKeyNotFoundException(table);
		}

		return sql;
	}

	/**
	 * 获取删除sql
	 * 
	 * @param cls
	 * @return
	 */
	public static DeleteSQL createDeleteSql(Class<?> cls, Object id) {
		String table = getTable(cls);
		return createDeleteSql(cls, table, id);
	}

	public static DeleteSQL createDeleteSql(Class<?> cls, String table, Object id) {

		DeleteSQL sql = DeleteSQL.create(table);

		Field[] fields = cls.getDeclaredFields();

		boolean hasPrimaryKey = false;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(PrimaryKey.class)) {
				sql.equal(DBHelper.fieldName2ColumnName(field.getName()), id);
				// 主键只有一个
				hasPrimaryKey = true;
				break;
			}
		}

		if (!hasPrimaryKey) {
			throw new PrimaryKeyNotFoundException(table);
		}

		return sql;
	}

	public static SelectSQL createQueryListSql(Class<?> cls) {
		return createQueryListSql(cls, null);
	}

	/**
	 * 获取查询列表sql
	 * 
	 * @param cls
	 * @return
	 */
	public static SelectSQL createQueryListSql(Class<?> cls, Query filter) {

		String table = getTable(cls);
		SelectSQL select = SelectSQL.create(table);

		List<OrderSort> l = new ArrayList<OrderSort>();

		Field[] fields = cls.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {

			Field field = fields[i];
			String column = DBHelper.fieldName2ColumnName(field.getName());

			if ("serialVersionUID".equals(field.getName())) {
				continue;
			}
			int mod = field.getModifiers();
			if (Modifier.isFinal(mod)) {
				continue;
			}

			if (field.isAnnotationPresent(OrderAsc.class)) {
				OrderAsc orderAsc = BeanHelper.getAnnotation(cls, field.getName(), OrderAsc.class);
				l.add(new OrderSort(column, Order.ASC.getValue(), orderAsc.index()));
			} else if (field.isAnnotationPresent(OrderDesc.class)) {
				OrderDesc orderDesc = BeanHelper.getAnnotation(cls, field.getName(), OrderDesc.class);
				l.add(new OrderSort(column, Order.DESC.getValue(), orderDesc.index()));
			}

		}

		setOrder(select, l);

		return select;
	}

	/**
	 * 设置排序
	 * 
	 * @param select
	 * @param l
	 */
	private static void setOrder(SelectSQL select, List<OrderSort> l) {

		Collections.sort(l, new Comparator<OrderSort>() {

			@Override
			public int compare(OrderSort o1, OrderSort o2) {
				if (o1.index > o2.index) {
					return 1;
				} else if (o1.index < o2.index) {
					return -1;
				}
				return 0;
			}
		});

		for (OrderSort orderSort : l) {
			if (orderSort.type == Order.ASC.getValue()) {
				select.asc(orderSort.column);
			} else {
				select.desc(orderSort.column);
			}
		}

	}

	public static UpdateSQL createUpdateSQL(Object obj) {
		return createUpdateSQL(obj, getTable(obj));
	}

	/**
	 * 创建更新Sql
	 * 
	 * @param obj
	 * @param table
	 * @return
	 */
	public static UpdateSQL createUpdateSQL(Object obj, String table) {

		UpdateSQL update = UpdateSQL.create(table);

		Field[] fields = obj.getClass().getDeclaredFields();

		boolean hasPrimaryKey = false;
		for (int i = 0; i < fields.length; i++) {

			Field field = fields[i];
			if ("serialVersionUID".equals(field.getName())) {
				continue;
			}
			int mod = field.getModifiers();
			if (Modifier.isFinal(mod)) {
				continue;
			}

			Object value = BeanHelper.getValueByField(obj, field.getName());

			String column = DBHelper.fieldName2ColumnName(field.getName());
			if (field.isAnnotationPresent(PrimaryKey.class)) {
				update.equal(column, value);
				hasPrimaryKey = true;
			} else {
				if (field.isAnnotationPresent(Ignore.class) || field.isAnnotationPresent(IgnoreUpdate.class)) {
					continue;
				}

				update.set(column, value);
			}

		}

		if (!hasPrimaryKey) {
			throw new PrimaryKeyNotFoundException(table);
		}

		return update;
	}

	public static InsertSQL createInsertSQL(Object obj) {
		return createInsertSQL(obj, getTable(obj));
	}

	/**
	 * 创建添加sql
	 * 
	 * @param obj
	 * @param table
	 * @return
	 */
	public static InsertSQL createInsertSQL(Object obj, String table) {

		InsertSQL insert = InsertSQL.create(table);

		Field[] fields = obj.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {

			Field field = fields[i];
			if ("serialVersionUID".equals(field.getName())) {
				continue;
			}
			int mod = field.getModifiers();
			if (Modifier.isFinal(mod)) {
				continue;
			}

			if (field.isAnnotationPresent(Ignore.class) || field.isAnnotationPresent(IgnoreInsert.class)) {
				continue;
			}

			Object value = BeanHelper.getValueByField(obj, field.getName());

			if (field.isAnnotationPresent(AutoUUID.class) && (value == null || StringUtils.isEmpty(value.toString()))) {
				value = UUIDGenerator.getUUID();
			}

			if (field.isAnnotationPresent(AutoDate.class) && value == null) {
				value = DateTimeUtil.now();
			}

			String column = DBHelper.fieldName2ColumnName(field.getName());
			insert.set(column, value);

		}

		return insert;
	}

	/**
	 * 根据class获取表名
	 * 
	 * @param cls
	 * @return
	 */
	public static String getTable(Class<?> cls) {
		String table = DBHelper.className2TableName(cls);
		return "`" + table + "`";
	}

	public static String getTable(String table, String id, int tableCount) {
		long index = 0;// StringUtil.getHashCode(id) % tableCount;
		return table + "_" + index;
	}

	/**
	 * 根据对象获取表
	 * 
	 * @param obj
	 */
	private static String getTable(Object obj) {

		String table = DBHelper.className2TableName(obj.getClass());

		if (SPLIT_TABLE_CACHE.containsKey(obj.getClass())) {
			String field = SPLIT_TABLE_CACHE.get(obj.getClass());
			// if (StringUtils.isEmpty(field)) {
			// return "`" + table + "`";
			// }
			String id = (String) BeanHelper.getValueByField(obj, field);
			TableHash tableHash = BeanHelper.getAnnotation(obj, field, TableHash.class);
			return "`" + getTable(table, id, tableHash.tableCount()) + "`";
		} else {

			Field[] fields = obj.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {

				Field field = fields[i];
				if ("serialVersionUID".equals(field.getName())) {
					continue;
				}
				int mod = field.getModifiers();
				if (Modifier.isFinal(mod)) {
					continue;
				}

				if (field.isAnnotationPresent(TableHash.class)) {
					SPLIT_TABLE_CACHE.put(obj.getClass(), field.getName());
					TableHash tableHash = field.getAnnotation(TableHash.class);
					String id = (String) BeanHelper.getValueByField(obj, field.getName());
					return "`" + getTable(table, id, tableHash.tableCount()) + "`";
				}
			}
		}

		return "`" + table + "`";

	}

	/**
	 * 获取父表字段
	 * 
	 * @param cls
	 * @return
	 */
	public static String getParentColumn(Class<?> cls) {

		Field[] fields = cls.getDeclaredFields();

		String parentColumn = "";
		boolean hasParentColumn = false;
		for (int i = 0; i < fields.length; i++) {

			Field field = fields[i];
			if ("serialVersionUID".equals(field.getName())) {
				continue;
			}
			int mod = field.getModifiers();
			if (Modifier.isFinal(mod)) {
				continue;
			}

			String column = DBHelper.fieldName2ColumnName(field.getName());
			if (field.isAnnotationPresent(ParentID.class)) {
				parentColumn = column;
				hasParentColumn = true;
			}
		}

		if (!hasParentColumn) {
			throw new ParentIdFieldNotFoundException(cls.getName());
		}

		return parentColumn;
	}

	static class OrderSort {

		public OrderSort(String column, String type, int index) {
			this.type = type;
			this.column = column;
			this.index = index;
		}

		public String type;

		public String column;

		public int index;

	}

}
