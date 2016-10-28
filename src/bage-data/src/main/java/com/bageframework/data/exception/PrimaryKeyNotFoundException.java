package com.bageframework.data.exception;

public class PrimaryKeyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6794403992293981407L;

	public PrimaryKeyNotFoundException(String table) {
		super("primary key not found:table[" + table + "]");
	}
}
