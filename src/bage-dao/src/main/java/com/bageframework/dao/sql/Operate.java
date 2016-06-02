package com.bageframework.dao.sql;

public enum Operate {

	EQUAL("="), LESS("<"), LESS_OR_EQUAL("<="), GREATER(">"), GREATER_OR_EQUAL(">=");

	private String value;

	private Operate(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}