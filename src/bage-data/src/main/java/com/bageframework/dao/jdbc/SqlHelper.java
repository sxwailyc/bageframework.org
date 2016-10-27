package com.bageframework.dao.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public class SqlHelper {

	/**
	 * 创建批量insert对象sql语句，以及参数列表
	 * 
	 * @param objs
	 * @return
	 */
	public static <T> BeansSQL crateBeanSql(List<T> objs) {

		BeansSQL beansSql = new BeansSQL();

		Object first = objs.get(0);

		StringBuffer insert = new StringBuffer();
		StringBuffer attribute = new StringBuffer();
		StringBuffer parameters = new StringBuffer();

		insert.append("INSERT INTO ");
		String tableName = className2TableName(first);

		insert.append("`" + tableName + "`");

		Field[] fields = first.getClass().getDeclaredFields();

		for (int i = 0; i < objs.size(); i++) {

			Object obj = objs.get(i);

			SqlParameter parameter = new SqlParameter();

			for (int j = 0; j < fields.length; j++) {

				Field field = fields[j];
				if ("serialVersionUID".equals(field.getName())) {
					continue;
				}
				int mod = field.getModifiers();
				if (Modifier.isFinal(mod)) {
					continue;
				}

				Object value = getValueByField(obj, field.getName());

				if (i == 0 && value != null) {
					attribute.append(",");
					attribute.append(fieldName2ColumnName(field.getName()));
					parameters.append(",?");
				}

				if (value != null) {
					parameter.setObject(value);
				}
			}

			beansSql.AddParam(parameter);
		}

		String attributeStr = attribute.substring(1, attribute.length());
		String parametersStr = parameters.substring(1, parameters.length());

		insert.append("(");
		insert.append(attributeStr);
		insert.append(")");
		insert.append(" VALUES ");
		insert.append("(");
		insert.append(parametersStr);
		insert.append(")");

		beansSql.setSql(insert.toString());

		return beansSql;
	}

	public static BeanSQL createBeanSql(Object obj) {
		return createBeanSql(obj, "INSERT");
	}

	/**
	 * 根据所给Bean创建对应的SQL
	 * 
	 * @param obj
	 * @return
	 */
	public static BeanSQL createBeanSql(Object obj, String operate) {

		BeanSQL beanSql = new BeanSQL();

		StringBuffer insert = new StringBuffer();
		StringBuffer attribute = new StringBuffer();
		StringBuffer parameters = new StringBuffer();

		insert.append(operate + " INTO ");
		String tableName = className2TableName(obj);

		insert.append("`" + tableName + "`");

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

			Object value = getValueByField(obj, field.getName());

			if (value != null) {
				attribute.append(",");
				attribute.append(fieldName2ColumnName(field.getName()));
				parameters.append(",?");
				beanSql.AddParam(value);
			}
		}

		String attributeStr = attribute.substring(1, attribute.length());
		String parametersStr = parameters.substring(1, parameters.length());

		insert.append("(");
		insert.append(attributeStr);
		insert.append(")");
		insert.append(" VALUES ");
		insert.append("(");
		insert.append(parametersStr);
		insert.append(")");

		beanSql.setSql(insert.toString());

		return beanSql;

	}

	/**
	 * 创建批量insert对象sql语句，以及参数列表 指定表名
	 * 
	 * @param objs
	 * @return
	 */
	public static <T> BeansSQL crateBeanSql(String tableName, List<T> objs) {

		BeansSQL beansSql = new BeansSQL();

		Object first = objs.get(0);

		StringBuffer insert = new StringBuffer();
		StringBuffer attribute = new StringBuffer();
		StringBuffer parameters = new StringBuffer();

		insert.append("INSERT INTO ");

		insert.append("`" + tableName + "`");

		Field[] fields = first.getClass().getDeclaredFields();

		for (int i = 0; i < objs.size(); i++) {

			Object obj = objs.get(i);

			SqlParameter parameter = new SqlParameter();

			for (int j = 0; j < fields.length; j++) {

				Field field = fields[j];
				if ("serialVersionUID".equals(field.getName())) {
					continue;
				}
				int mod = field.getModifiers();
				if (Modifier.isFinal(mod)) {
					continue;
				}

				Object value = getValueByField(obj, field.getName());

				if (i == 0 && value != null) {
					attribute.append(",");
					attribute.append(fieldName2ColumnName(field.getName()));
					parameters.append(",?");
				}

				if (value != null) {
					parameter.setObject(value);
				}
			}

			beansSql.AddParam(parameter);
		}

		String attributeStr = attribute.substring(1, attribute.length());
		String parametersStr = parameters.substring(1, parameters.length());

		insert.append("(");
		insert.append(attributeStr);
		insert.append(")");
		insert.append(" VALUES ");
		insert.append("(");
		insert.append(parametersStr);
		insert.append(")");

		beansSql.setSql(insert.toString());

		return beansSql;
	}

	/**
	 * 根据所给Bean创建对应的SQL 指定表
	 * 
	 * @param obj
	 * @return
	 */
	public static BeanSQL createBeanSql(String tableName, Object obj) {

		BeanSQL beanSql = new BeanSQL();

		StringBuffer insert = new StringBuffer();
		StringBuffer attribute = new StringBuffer();
		StringBuffer parameters = new StringBuffer();

		insert.append("INSERT INTO ");

		insert.append("`" + tableName + "`");

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

			Object value = getValueByField(obj, field.getName());

			if (value != null) {
				attribute.append(",");
				attribute.append(fieldName2ColumnName(field.getName()));
				parameters.append(",?");
				beanSql.AddParam(value);
			}
		}

		String attributeStr = attribute.substring(1, attribute.length());
		String parametersStr = parameters.substring(1, parameters.length());

		insert.append("(");
		insert.append(attributeStr);
		insert.append(")");
		insert.append(" VALUES ");
		insert.append("(");
		insert.append(parametersStr);
		insert.append(")");

		beanSql.setSql(insert.toString());

		return beanSql;

	}

	/**
	 * 类名转成表名
	 * 
	 * @param obj
	 * @return
	 */
	public static String className2TableName(Object obj) {

		String className = obj.getClass().getName();
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

	/**
	 * 属性名转成字段名
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String fieldName2ColumnName(String fieldName) {

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
		return "`" + columnName.toString().toLowerCase() + "`";
	}

	public static String getShortName(Object obj) {

		String className = obj.getClass().getName();
		className = className.substring(className.lastIndexOf(".") + 1, className.length());
		return className;

	}

	public static Object getValueByField(Object obj, String field) {
		Object value = null;
		try {
			String methodName = fieldName2GetMethod(field);
			Method method = obj.getClass().getMethod(methodName, new Class[0]);
			value = method.invoke(obj, new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public static String fieldName2GetMethod(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
	}

	public static String fieldName2SetMethod(String fieldName) {
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
	}
}
