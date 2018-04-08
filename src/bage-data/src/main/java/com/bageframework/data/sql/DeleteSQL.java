package com.bageframework.data.sql;

import com.bageframework.data.DB;
import com.bageframework.data.jdbc.SqlParameter;

public class DeleteSQL extends WhereBaseSQL implements SQL {

	private String table;

	private DeleteSQL(String table) {
		this.table = table;
	}

	public DeleteSQL equal(String column, Object value) {
		where.condition(column, Operate.EQUAL, value);
		return this;
	}

	public static DeleteSQL create(String table) {
		DeleteSQL insertSQL = new DeleteSQL(table);
		return insertSQL;
	}

	public String getSql(DB db) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE ");
		sb.append(" FROM ");
		sb.append(table);

		String w = where.getSql(db);
		if (w.length() > 0) {
			sb.append(" WHERE ");
			sb.append(w);
		}

		return sb.toString();
	}

	public SqlParameter getParams() {
		SqlParameter parameter = where.getParams();
		return parameter;
	}

}
