package com.bageframework.data.beans;

import com.bageframework.data.sql.Operate;

public class QueryItem {

	public QueryItem() {

	}

	public QueryItem(String column, String operate, Object value) {
		this.column = column;
		this.operate = operate;
		this.value = value;
	}

	public QueryItem(String column, Object value) {
		this.column = column;
		this.operate = Operate.EQUAL.getValue();
		this.value = value;
	}

	private String column;

	private String operate;

	private Object value;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
