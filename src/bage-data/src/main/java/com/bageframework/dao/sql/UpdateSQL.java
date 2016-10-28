package com.bageframework.dao.sql;

import java.util.ArrayList;
import java.util.List;

import com.bageframework.dao.jdbc.SqlParameter;
import com.bageframework.dao.util.SqlUtil;

public class UpdateSQL extends WhereBaseSQL implements SQL {

	private String table;

	private List<String> columns = new ArrayList<String>();

	private List<Object> values = new ArrayList<Object>();

	private UpdateSQL(String table) {
		this.table = table;
	}

	public UpdateSQL set(String column, Object value) {
		columns.add(column);
		values.add(value);
		return this;
	}

	public static UpdateSQL create(String table) {
		UpdateSQL updateSQL = new UpdateSQL(table);
		return updateSQL;
	}

	private String getUpdateColumnSql() {
		StringBuilder sb = new StringBuilder();
		for (String column : columns) {
			if (sb.length() > 0) {
				sb.append(",");
			} else {
				sb.append(" SET ");
			}
			sb.append(SqlUtil.getSafeName(column));
			sb.append(" = ? ");
		}
		return sb.toString();
	}

	public String getSql() {
		StringBuilder sb = new StringBuilder();

		sb.append("UPDATE ");
		sb.append(SqlUtil.getSafeName(table));
		sb.append(getUpdateColumnSql());
		sb.append("WHERE");
		sb.append(where.getSql());

		return sb.toString();
	}

	public SqlParameter getParams() {
		SqlParameter parameter = SqlParameter.fromList(values);
		parameter.merge(where.getParams());
		return parameter;
	}

}
