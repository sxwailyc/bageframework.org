package com.bageframework.data.exception;

public class ParentIdFieldNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6794403992293981407L;

	public ParentIdFieldNotFoundException(String className) {
		super("parentId filed not found:class[" + className + "]");
	}
}
