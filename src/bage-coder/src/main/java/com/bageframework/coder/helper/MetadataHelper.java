package com.bageframework.coder.helper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bageframework.coder.metadata.Field;
import com.bageframework.coder.model.Column;
import com.bageframework.coder.type.Type;

public class MetadataHelper {

	protected static Log logger = LogFactory.getLog(MetadataHelper.class);

	/**
	 * 字段名转get方法
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String fieldName2GetMethod(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
	}

	/**
	 * 字段名转set方法
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String fieldName2SetMethod(String fieldName) {
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
	}

	/**
	 * 属性名(对象)转字段名(db)
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
		return columnName.toString().toLowerCase();
	}

	/**
	 * 数据库字段转成java属性
	 * 
	 * @param column
	 * @return
	 */
	public static String columnName2FieldName(String column) {

		if (null == column) {
			return "";
		}
		char[] chars = column.toCharArray();
		StringBuffer sb = new StringBuffer();
		boolean toUpper = false;
		for (char c : chars) {
			if (c == '_') {
				toUpper = true;
			} else {
				if (toUpper) {
					sb.append(String.valueOf(c).toUpperCase());
					toUpper = false;
				} else {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	public static String tableName2ClassName(String table) {

		if (null == table) {
			return "";
		}
		char[] chars = table.toCharArray();
		StringBuffer sb = new StringBuffer();
		boolean toUpper = true;
		for (char c : chars) {
			if (c == '_') {
				toUpper = true;
			} else {
				if (toUpper) {
					sb.append(String.valueOf(c).toUpperCase());
					toUpper = false;
				} else {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 类名转表名
	 * 
	 * @param cls
	 * @return
	 */
	public static String className2TableName(Class<?> cls) {

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

	public static Field column2Field(Column column) {

		Field field = new Field();

		field.setAttribute(columnName2FieldName(column.getField()));
		field.setGetMethod(fieldName2GetMethod(field.getAttribute()));
		field.setSetMethod(fieldName2SetMethod(field.getAttribute()));
		field.setType(Type.parse(column.getType()).getValue());

		return field;
	}

	public static String getImport(Column column) {

		Type type = Type.parse(column.getType());
		return Type.getImport(type);

	}

	public static void main(String[] args) {
		System.out.println(columnName2FieldName("book_id"));
	}
}
