package com.bageframework.coder.type;

public enum Formatter {

	STRING("string"), INT("int"), DATE("date");

	private String value;

	private Formatter(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String getFormatter(Type type) {

		switch (type) {
		case DATE:
			return DATE.value;
		case STRING:
			return STRING.value;
		case INT:
			return INT.value;
		default:
			return STRING.value;
		}

	}
}
