package com.bageframework.dao.beans;

import com.bageframework.dao.sql.Operate;

public class QueryItem {

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

	public String column;

	public String operate;

	public Object value;

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
