package com.bageframework.data.jdbc;

/**
 * 一个bean的sql转换
 * 
 * @author shixiangwen03@gmail.com
 * 
 */

public class BeanSQL {

	private String sql;

	private SqlParameter params = new SqlParameter();

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public SqlParameter getParams() {
		return params;
	}

	public void AddParam(Object param) {
		if (param instanceof String) {
			this.params.setString(param.toString());
		} else if (param instanceof Integer) {
			this.params.setInt(Integer.parseInt(param.toString()));
		} else if (param instanceof Long) {
			this.params.setLong(Long.parseLong(param.toString()));
		} else if (param instanceof Double) {
			this.params.setDouble(Double.parseDouble(param.toString()));
		} else {
			this.params.setObject(param);
		}

	}
}
