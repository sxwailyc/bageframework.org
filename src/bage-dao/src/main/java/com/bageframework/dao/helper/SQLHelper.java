package com.bageframework.dao.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bageframework.dao.annotation.Ignore;
import com.bageframework.dao.annotation.IgnoreInsert;
import com.bageframework.dao.annotation.IgnoreUpdate;
import com.bageframework.dao.annotation.OrderAsc;
import com.bageframework.dao.annotation.OrderDesc;
import com.bageframework.dao.annotation.ParentID;
import com.bageframework.dao.annotation.PrimaryKey;
import com.bageframework.dao.annotation.TableHash;
import com.bageframework.dao.beans.QueryFilter;
import com.bageframework.dao.exception.PrimaryKeyNotFoundException;
import com.bageframework.dao.sql.InsertSQL;
import com.bageframework.dao.sql.SelectSQL;
import com.bageframework.dao.sql.UpdateSQL;

public class DBHelper {

	protected static Log logger = LogFactory.getLog(DBHelper.class);

	/**
	 * 分表字段缓存
	 */
	private static Map<Class<?>, String> SPLIT_TABLE_CACHE = new ConcurrentHashMap<Class<?>, String>();

	/**
	 * 查询列表sql缓存
	 */
	private static Map<Class<?>, QueryObject> QUERY_LIST_SQL_CACHE = new ConcurrentHashMap<Class<?>, QueryObject>();

	/**
	 * 创建sql
	 * 
	 * @param op
	 * @param cls
	 * @return
	 */
	private static String createSql(String op, Class<?> cls) {
		String table = getTable(cls);
		return createSql(op, cls, table);
	}

	public static SelectSQL createGetSql(Class<?> cls, Object id) {
		String table = getTable(cls);
		return createGetSql(cls, table, id);
	}

	public static SelectSQL createGetSql(Class<?> cls, String table, Object id) {

		SelectSQL sql = SelectSQL.create(table);

		Field[] fields = cls.getDeclaredFields();

		boolean hasPrimaryKey = false;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(PrimaryKey.class)) {
				sql.equal(fieldName2ColumnName(field.getName()), id);
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
	 * 
	 * @param op
	 * @param cls
	 * @param table
	 * @return
	 */
	private static String createSql(String op, Class<?> cls, String table) {

		Field[] fields = cls.getDeclaredFields();

		String where = null;

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(PrimaryKey.class)) {
				if (where == null) {
					where = fieldName2ColumnName(field.getName()) + " = ? ";
				} else {
					where = where + " AND " + fieldName2ColumnName(field.getName()) + " = ? ";
				}
			}
		}

		String sql = op + table + " WHERE " + where;

		return sql;
	}

	/**
	 * 获取删除sql
	 * 
	 * @param cls
	 * @return
	 */
	public static String createDeleteSql(Class<?> cls) {
		return createSql("DELETE FROM ", cls);
	}

	public static String createDeleteSql(Class<?> cls, String table) {
		return createSql("DELETE FROM ", cls, table);
	}

	/**
	 * 获取查询sql
	 * 
	 * @param cls
	 * @return
	 */
	public static String createQuerySql(Class<?> cls) {
		return createSql("SELECT * FROM ", cls);
	}

	public static QueryObject createQueryListSql(Class<?> cls) {
		return createQueryListSql(cls, null);
	}

	/**
	 * 获取查询列表sql
	 * 
	 * @param cls
	 * @return
	 */
	public static QueryObject createQueryListSql(Class<?> cls, QueryFilter filter) {

		String sql;
		StringBuilder order = new StringBuilder();
		String parentId = null;

		if (QUERY_LIST_SQL_CACHE.containsKey(cls)) {
			return QUERY_LIST_SQL_CACHE.get(cls);
		} else {

			String table = getTable(cls);

			sql = "SELECT * FROM " + table;

			Field[] fields = cls.getDeclaredFields();

			for (int i = 0; i < fields.length; i++) {

				Field field = fields[i];
				if ("serialVersionUID".equals(field.getName())) {
					continue;
				}
				int mod = field.getModifiers();
				if (Modifier.isFinal(mod)) {
					continue;
				}

				if (field.isAnnotationPresent(ParentID.class)) {
					parentId = fieldName2ColumnName(field.getName());
				}

				if (field.isAnnotationPresent(OrderAsc.class)) {
					if (order.length() > 0) {
						order.append(",");
					}
					order.append(fieldName2ColumnName(field.getName()) + " ASC ");
				} else if (field.isAnnotationPresent(OrderDesc.class)) {
					if (order.length() > 0) {
						order.append(",");
					}
					order.append(fieldName2ColumnName(field.getName()) + " DESC ");
				}

			}

		}

		logger.debug("query sql[" + sql + "]");

		QueryObject queryObject = new QueryObject(sql, order.toString(), parentId);
		QUERY_LIST_SQL_CACHE.put(cls, queryObject);

		return queryObject;
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

			String column = fieldName2ColumnName(field.getName());
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

	/**
	 * 创建更新builder
	 * 
	 * @param obj
	 * @return
	 */
	// public static UpdateBuilder createUpdateBuilder(Object obj) {
	// return createUpdateBuilder(obj, getTable(obj));
	// }

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

			String column = fieldName2ColumnName(field.getName());
			insert.set(column, value);

		}

		return insert;
	}

	/**
	 * 设置builder参数
	 * 
	 * @param builder
	 * @param field
	 * @param value
	 */
	// private static void set(AbstractSqlBuilder builder, Field field, Object
	// value) {
	// String columnName = fieldName2ColumnName(field.getName());
	// if (field.getType() == String.class) {
	// builder.setString(columnName, (String) value);
	// } else if (field.getType() == Long.class || field.getType() ==
	// long.class) {
	// builder.setLong(columnName, (Long) value);
	// } else if (field.getType() == Double.class || field.getType() ==
	// double.class) {
	// builder.setDouble(columnName, (Double) value);
	// } else if (field.getType() == Integer.class || field.getType() ==
	// int.class) {
	// builder.setInt(columnName, (Integer) value);
	// } else if (field.getType() == Float.class || field.getType() ==
	// float.class) {
	// builder.setFloat(columnName, (Float) value);
	// } else if (field.getType() == Date.class) {
	// builder.setDate(columnName, (Date) value);
	// } else if (field.getType() == Timestamp.class) {
	// builder.setTimestamp(columnName, (Timestamp) value);
	// } else {
	// throw new RuntimeException("未知的参数类型[" + field.getType() + "]");
	// }
	// }

	/**
	 * 设置where参数
	 * 
	 * @param builder
	 * @param field
	 * @param value
	 */
	// private static void setWhere(UpdateBuilder builder, Field field, Object
	// value) {
	// String columnName = fieldName2ColumnName(field.getName());
	// if (field.getType() == String.class) {
	// builder.where.setString(columnName, (String) value);
	// } else if (field.getType() == Long.class || field.getType() ==
	// long.class) {
	// builder.where.setLong(columnName, (Long) value);
	// } else if (field.getType() == Double.class || field.getType() ==
	// double.class) {
	// builder.where.setDouble(columnName, (Double) value);
	// } else if (field.getType() == Integer.class || field.getType() ==
	// int.class) {
	// builder.where.setInt(columnName, (Integer) value);
	// } else if (field.getType() == Float.class || field.getType() ==
	// float.class) {
	// builder.where.setFloat(columnName, (Float) value);
	// } else if (field.getType() == Date.class) {
	// builder.where.setDate(columnName, (Date) value);
	// } else if (field.getType() == Timestamp.class) {
	// builder.where.setTimestamp(columnName, (Timestamp) value);
	// } else {
	// throw new RuntimeException("未知的参数类型[" + field.getType() + "]");
	// }
	// }

	// /**
	// * 创建添加builder
	// *
	// * @param obj
	// * @return
	// */

	/**
	 * 根据class获取表名
	 * 
	 * @param cls
	 * @return
	 */
	public static String getTable(Class<?> cls) {
		String table = className2TableName(cls);
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

		String table = className2TableName(obj.getClass());

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
	 * 属性名(对象)转字段名(db)
	 * 
	 * @param fieldName
	 * @return
	 */
	private static String fieldName2ColumnName(String fieldName) {

		char[] chs = fieldName.toCharArray();

		StringBuffer columnName = new StringBuffer();
		columnName.append(chs[0]);
		for (int i = 1; i < chs.length; i++) {
			byte bt = (byte) chs[i];
			if (bt >= 65 && bt <= 90) {
				columnName.append("_");
				columnName.append(chs[i]);
			} else {
				columnName.append(chs[i]);
			}
		}
		return columnName.toString().toLowerCase();
	}

	/**
	 * 类名转表名
	 * 
	 * @param cls
	 * @return
	 */
	private static String className2TableName(Class<?> cls) {

		String className = cls.getName();
		className = className.substring(className.lastIndexOf(".") + 1, className.length());

		char[] chs = className.toCharArray();

		StringBuffer tableName = new StringBuffer();
		tableName.append(chs[0]);
		for (int i = 1; i < chs.length; i++) {
			byte bt = (byte) chs[i];
			if (bt >= 65 && bt <= 90) {
				tableName.append("_");
				tableName.append(chs[i]);
			} else {
				tableName.append(chs[i]);
			}
		}

		return tableName.toString().toLowerCase();
	}

}
