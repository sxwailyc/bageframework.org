package com.bageframework.data.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bageframework.data.sql.Operate;
import com.bageframework.util.Json;

public class Query implements Serializable, Iterable<QueryItem> {

	private static final long serialVersionUID = 1L;

	private String order;

	private List<QueryItem> data = new ArrayList<QueryItem>();

	public List<QueryItem> getData() {
		return data;
	}

	public void setData(List<QueryItem> data) {
		this.data = data;
	}

	public Query equal(String column, Object value) {
		QueryItem queryItem = new QueryItem(column, Operate.EQUAL.getValue(), value);
		data.add(queryItem);
		return this;
	}

	public Query less(String column, Object value) {
		QueryItem queryItem = new QueryItem(column, Operate.LESS.getValue(), value);
		data.add(queryItem);
		return this;
	}

	public Query lessOrEqual(String column, Object value) {
		QueryItem queryItem = new QueryItem(column, Operate.LESS_OR_EQUAL.getValue(), value);
		data.add(queryItem);
		return this;
	}

	public Query greater(String column, Object value) {
		QueryItem queryItem = new QueryItem(column, Operate.GREATER.getValue(), value);
		data.add(queryItem);
		return this;
	}

	public Query greaterOrEqual(String column, Object value) {
		QueryItem queryItem = new QueryItem(column, Operate.GREATER_OR_EQUAL.getValue(), value);
		data.add(queryItem);
		return this;
	}

	@Override
	public Iterator<QueryItem> iterator() {
		return data.iterator();
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public static void main(String[] args) throws Exception {

		String s = "{\"data\":[{\"column\":\"username\",\"value\":\"username-24\",\"operate\":\"=\"}]}";
		System.out.println(Json.toObject(s, Query.class));

	}
}
