package com.bageframework.dao.sql;

public enum Order {

	ASC("ASC"), DESC("DESC");

	private String value;

	private Order(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}