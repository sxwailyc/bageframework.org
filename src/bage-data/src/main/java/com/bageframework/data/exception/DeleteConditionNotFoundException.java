package com.bageframework.data.exception;

public class DeleteConditionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6794403992293981407L;

	public DeleteConditionNotFoundException(String table) {
		super("delete condition not found:table[" + table + "]");
	}
}
