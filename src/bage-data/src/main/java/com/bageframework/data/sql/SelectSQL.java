package com.bageframework.data.sql;

import java.util.ArrayList;
import java.util.List;

import com.bageframework.data.jdbc.SqlParameter;

public class SelectSQL implements SQL {

	private WhereSQL where = new WhereSQL();

	private OrderSQL order = new OrderSQL();

	private LimitSQL limit = new LimitSQL();

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

	public SelectSQL condition(String column, String operate, Object value) {
		where.condition(column, Operate.parse(operate), value);
		return this;
	}

	public SelectSQL asc(String column) {
		order.order(column, Order.ASC);
		return this;
	}

	public SelectSQL desc(String column) {
		order.order(column, Order.DESC);
		return this;
	}

	public SelectSQL limit(int start, int size) {
		limit.limit(start, size);
		return this;
	}

	public SelectSQL limit(int size) {
		limit.limit(size);
		return this;
	}

	public static SelectSQL create(String table) {
		SelectSQL insertSQL = new SelectSQL(table);
		return insertSQL;
	}

	/**
	 * 返加查询行数的SQL
	 * 
	 * @return
	 */
	public String getCountSql() {

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT count(1) FROM ");
		sb.append(table);

		String w = where.getSql();
		if (w.length() > 0) {
			sb.append(" WHERE ");
			sb.append(w);
		}

		return sb.toString();
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

		String o = order.getSql();
		if (o.length() > 0) {
			sb.append(" ORDER BY ");
			sb.append(o);
		}

		String l = limit.getSql();
		if (l.length() > 0) {
			sb.append(l);
		}

		return sb.toString();
	}

	public SqlParameter getParams() {
		SqlParameter parameter = where.getParams();
		return parameter;
	}

}
