package com.bageframework.dao.helper;

public class DBHelper {

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

}
