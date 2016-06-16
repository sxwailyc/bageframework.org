package com.bageframework.coder.type;

public enum Type {

	INT("Integer"), DATE("Date"), STRING("String"), UNKNOW("UNKNOW");

	private String value;

	private Type(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String parse(String type) {

		if (type.startsWith("tinyint") || type.startsWith("int") || type.startsWith("smallint") || type.startsWith("bigint")) {
			return INT.value;
		} else if (type.startsWith("varchar") || type.startsWith("char") || type.startsWith("longtext") || type.startsWith("text")) {
			return STRING.value;
		} else if (type.startsWith("datetime")) {
			return DATE.value;
		} else {
			return UNKNOW.value;
		}
	}

}
