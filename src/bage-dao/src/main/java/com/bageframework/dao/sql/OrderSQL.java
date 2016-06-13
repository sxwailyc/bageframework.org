package com.bageframework.dao.sql;

import java.util.ArrayList;
import java.util.List;

import com.bageframework.dao.jdbc.SqlParameter;

public class OrderSQL implements SQL {

	private List<String> columns = new ArrayList<String>();

	private List<Order> orders = new ArrayList<Order>();

	public OrderSQL order(String column, Order order) {
		columns.add(column);
		orders.add(order);
		return this;
	}

	@Override
	public String getSql() {
		StringBuilder sb = new StringBuilder();
		int size = columns.size();
		for (int i = 0; i < size; i++) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(columns.get(i));
			sb.append(" ");
			sb.append(orders.get(i).getValue());
		}
		return sb.toString();
	}

	@Override
	public SqlParameter getParams() {
		return null;
	}

}
