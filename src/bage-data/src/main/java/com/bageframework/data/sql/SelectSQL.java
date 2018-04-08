package com.bageframework.data.sql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bageframework.data.DB;
import com.bageframework.data.jdbc.SqlParameter;
import com.bageframework.data.util.SqlUtil;

public class SelectSQL implements SQL {

	private WhereSQL where = new WhereSQL();

	private OrderSQL order = new OrderSQL();

	private String customOrder;

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

	public SelectSQL customOrder(String customOrder) {
		this.customOrder = customOrder;
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
	public String getCountSql(DB db) {

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT count(1) FROM ");
		sb.append(table);

		String w = where.getSql(db);
		if (w.length() > 0) {
			sb.append(" WHERE ");
			sb.append(w);
		}

		String s = sb.toString();
		if (db == DB.MSSQL) {
			return s.replaceAll("@\\[", "[").replaceAll("@\\]", "]");
		} else {
			return s.replaceAll("@\\[", "`").replaceAll("@\\]", "`");
		}
	}

	private String getMysqlSql() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		if (columns.isEmpty()) {
			sb.append(" * ");
		} else {

		}
		sb.append(" FROM ");
		sb.append(table);

		String w = where.getSql(DB.MYSQL);
		if (w.length() > 0) {
			sb.append(" WHERE ");
			sb.append(w);
		}

		String o = order.getSql(DB.MYSQL);
		if (o.length() > 0) {
			sb.append(" ORDER BY ");
			sb.append(o);
		}

		String l = limit.getSql(DB.MYSQL);
		if (l.length() > 0) {
			sb.append(l);
		}

		String s = sb.toString();
		return s.replaceAll("@\\[", "`").replaceAll("@\\]", "`");
	}

	@Override
	public String getSql(DB db) {
		switch (db) {
		case MSSQL:
			return getMssqlSql();
		case MYSQL:
		default:
			return getMysqlSql();
		}
	}

	private String getMssqlSql() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		if (columns.isEmpty()) {
			sb.append(" * ");
		} else {
			sb.append(SqlUtil.joinColumn(columns));
		}
		sb.append(" FROM ");

		sb.append(String.format(" (SELECT *, ROW_NUMBER() OVER(ORDER BY %s) AS RowId from %s)t ", StringUtils.isEmpty(this.customOrder) ? order.getSql(DB.MSSQL) : this.customOrder, table));

		String w = where.getSql(DB.MSSQL);
		if (w.length() > 0) {
			sb.append(" WHERE ");
			sb.append(w);
		}

		String l = limit.getSql(DB.MSSQL);
		if (l.length() > 0) {
			if (w.length() > 0) {
				sb.append(" AND ");
			} else {
				sb.append(" WHERE ");
			}
			sb.append(l);
		}

		String s = sb.toString();
		return s.replaceAll("@\\[", "[").replaceAll("@\\]", "]");
	}

	public SqlParameter getParams() {
		SqlParameter parameter = where.getParams();
		return parameter;
	}

}
