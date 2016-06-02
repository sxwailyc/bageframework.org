package com.bageframework.jdbc;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个bean的sql转换
 * 
 * @author jacky
 * 
 */

public class BeansSQL {

	private String sql;

	private List<SqlParameter> parameters = new ArrayList<SqlParameter>();

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<SqlParameter> getParams() {
		return parameters;
	}

	public void AddParam(SqlParameter parameter) {
		this.parameters.add(parameter);
	}
}
