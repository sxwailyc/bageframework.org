package com.bageframework.helper;

public class QueryObject {

	public QueryObject(String sql, String order, String parentId) {
		this.sql = sql;
		this.order = order;
		this.parentId = parentId;
	}

	public QueryObject(String sql, String order) {
		this.sql = sql;
		this.order = order;
	}

	public String getFullSql() {
		if (order.length() > 0) {
			return sql + " ORDER BY " + order;
		}
		return sql;
	}

	private String sql;

	private String order;

	private String parentId;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
