package com.bageframework.coder.model;

public class Column {

	private String field;

	private String type;

	private String comment;

	private String key;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "Field[" + field + "], Type[" + type + "], Key[" + key + "], Comment[" + comment + "]";
	}

}
