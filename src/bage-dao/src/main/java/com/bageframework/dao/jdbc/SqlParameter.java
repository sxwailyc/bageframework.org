package com.bageframework.dao.jdbc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库参数
 * 
 * @author shixiangwen@gmail.com
 * 
 */

public class SqlParameter {

	int parameterInd = 1;

	private Map<Integer, Object> params = new HashMap<Integer, Object>();

	public static SqlParameter fromList(List<?> list) {
		SqlParameter sqlParameter = new SqlParameter();
		for (Object o : list) {
			sqlParameter.setObject(o);
		}
		return sqlParameter;
	}

	/***
	 * 设置整形参数
	 * 
	 * @param param
	 */
	public void setInt(Integer param) {

		this.setObject(param);
	}

	/**
	 * 设置字符串参数
	 * 
	 * @param param
	 */
	public void setString(String param) {

		this.setObject(param);
	}

	/**
	 * 设置long型参数
	 * 
	 * @param param
	 */
	public void setLong(Long param) {
		this.setObject(param);
	}

	/**
	 * 设置date型参数
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.setObject(date);
	}

	/**
	 * 设置double参数
	 * 
	 * @param param
	 */
	public void setDouble(Double param) {
		this.setObject(param);
	}

	public void setObject(Object param) {
		this.params.put(this.parameterInd++, param);
	}

	public Map<Integer, Object> getParams() {
		return params;
	}

	public void merge(SqlParameter parameter) {
		Map<Integer, Object> _params = parameter.getParams();
		int size = _params.size();
		for (int i = 1; i <= size; i++) {
			setObject(_params.get(i));
		}
	}
}
