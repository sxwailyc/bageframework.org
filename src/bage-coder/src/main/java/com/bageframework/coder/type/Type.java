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

	public static Type parse(String type) {

		if (type.startsWith("tinyint") || type.startsWith("int") || type.startsWith("smallint") || type.startsWith("bigint")) {
			return INT;
		} else if (type.startsWith("varchar") || type.startsWith("char") || type.startsWith("longtext") || type.startsWith("text")) {
			return STRING;
		} else if (type.startsWith("datetime")) {
			return DATE;
		} else {
			return UNKNOW;
		}
	}

	public static String getImport(Type type) {

		switch (type) {
		case DATE:
			return "java.util.Date";
		default:
			return null;
		}
	}

}
