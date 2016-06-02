package com.bageframework.sql;

import java.util.ArrayList;
import java.util.List;

import com.bageframework.jdbc.SqlParameter;
import com.bageframework.util.SqlUtil;

public class InsertSQL implements SQL {

	private String table;

	private List<String> columns = new ArrayList<String>();

	private List<Object> values = new ArrayList<Object>();

	private InsertSQL(String table) {
		this.table = table;
	}

	public InsertSQL set(String column, Object value) {
		columns.add(column);
		values.add(value);
		return this;
	}

	public static InsertSQL create(String table) {
		InsertSQL insertSQL = new InsertSQL(table);
		return insertSQL;
	}

	public String getSql() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + SqlUtil.getSafeName(table) + "(" + SqlUtil.joinColumn(columns) + ") VALUES(" + SqlUtil.getPlaceholder(columns.size(), '?') + ")");
		return sb.toString();
	}

	public SqlParameter getParams() {
		return SqlParameter.fromList(values);
	}

}
