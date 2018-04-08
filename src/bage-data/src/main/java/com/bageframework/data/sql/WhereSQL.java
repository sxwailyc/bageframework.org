package com.bageframework.data.sql;

import java.util.ArrayList;
import java.util.List;

import com.bageframework.data.DB;
import com.bageframework.data.jdbc.SqlParameter;
import com.bageframework.data.util.SqlUtil;

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
	public String getSql(DB db) {
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

		if (db == DB.MSSQL) {
			return sb.toString().replaceAll("@\\[", "[").replaceAll("@\\]", "]");
		} else {
			return sb.toString().replaceAll("@\\[", "`").replaceAll("@\\]", "`");
		}

	}

	@Override
	public SqlParameter getParams() {
		return SqlParameter.fromList(values);
	}

}
