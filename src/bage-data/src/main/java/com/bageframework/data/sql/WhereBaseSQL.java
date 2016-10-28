package com.bageframework.data.sql;

public class WhereBaseSQL {

	protected WhereSQL where = new WhereSQL();

	public WhereBaseSQL equal(String column, Object value) {
		where.condition(column, Operate.EQUAL, value);
		return this;
	}

	public WhereBaseSQL less(String column, Object value) {
		where.condition(column, Operate.LESS, value);
		return this;
	}

	public WhereBaseSQL lessOrEqual(String column, Object value) {
		where.condition(column, Operate.LESS_OR_EQUAL, value);
		return this;
	}

	public WhereBaseSQL greater(String column, Object value) {
		where.condition(column, Operate.GREATER, value);
		return this;
	}

	public WhereBaseSQL greaterOrEqual(String column, Object value) {
		where.condition(column, Operate.GREATER_OR_EQUAL, value);
		return this;
	}

}
