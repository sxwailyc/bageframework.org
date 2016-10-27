package com.bageframework.dao.sql;

import java.util.ArrayList;
import java.util.List;

import com.bageframework.dao.jdbc.SqlParameter;
import com.bageframework.dao.util.SqlUtil;

public class WhereSQL implements SQL {

	private List<String> columns = new ArrayList<String>();

	private List<Operate> operates = new ArrayList<Operate>();

	private List<Object> values = new ArrayList<Object>();

	public WhereSQL condition(String column, Operate operate, Object value) {
		columns.add(column);
		operates.add(operate);
		values.add(value);
		return this;
	}

	@Override
	public String getSql() {
		StringBuilder sb = new StringBuilder();
		int size = columns.size();
		for (int i = 0; i < size; i++) {
			if (sb.length() > 0) {
				sb.append(" AND ");
			}
			sb.append(SqlUtil.getSafeName(columns.get(i)));
			sb.append(operates.get(i).getValue());
			sb.append(" ? ");
		}
		return sb.toString();
	}

	@Override
	public SqlParameter getParams() {
		return SqlParameter.fromList(values);
	}

}
