package com.bageframework.dao.sql;

import java.util.ArrayList;
import java.util.List;

import com.bageframework.dao.jdbc.SqlParameter;

public class SelectSQL implements SQL {

	private WhereSQL where = new WhereSQL();

	private String table;

	private List<String> columns = new ArrayList<String>();

	private SelectSQL(String table) {
		this.table = table;
	}

	public SelectSQL select(String column) {
		columns.add(column);
		return this;
	}

	public SelectSQL equal(String column, Object value) {
		where.condition(column, Operate.EQUAL, value);
		return this;
	}

	public SelectSQL less(String column, Object value) {
		where.condition(column, Operate.LESS, value);
		return this;
	}

	public SelectSQL lessOrEqual(String column, Object value) {
		where.condition(column, Operate.LESS_OR_EQUAL, value);
		return this;
	}

	public SelectSQL greater(String column, Object value) {
		where.condition(column, Operate.GREATER, value);
		return this;
	}

	public SelectSQL greaterOrEqual(String column, Object value) {
		where.condition(column, Operate.GREATER_OR_EQUAL, value);
		return this;
	}

	public static SelectSQL create(String table) {
		SelectSQL insertSQL = new SelectSQL(table);
		return insertSQL;
	}

	public String getSql() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		if (columns.isEmpty()) {
			sb.append(" * ");
		} else {

		}
		sb.append(" FROM ");
		sb.append(table);

		String w = where.getSql();
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
