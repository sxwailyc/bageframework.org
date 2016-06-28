package com.bageframework.coder.type;

public enum FormType {

	TEXT(1), TEXTAREA(2), SELECT(3), CHECKBOX(4);

	private int value;

	private FormType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
