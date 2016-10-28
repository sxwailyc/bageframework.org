package com.bageframework.data.sql;

import com.bageframework.data.jdbc.SqlParameter;

public class LimitSQL implements SQL {

	private int start = -1;

	private int size = -1;

	public LimitSQL limit(int start, int size) {
		this.start = start;
		this.size = size;
		return this;
	}

	public LimitSQL limit(int size) {
		this.size = size;
		return this;
	}

	@Override
	public String getSql() {
		StringBuilder sb = new StringBuilder();
		if (start >= 0 && size >= 0) {
			sb.append(" LIMIT ");
			sb.append(start);
			sb.append(",");
			sb.append(size);
		} else if (size >= 0) {
			sb.append(" LIMIT ");
			sb.append(size);
		}
		return sb.toString();
	}

	@Override
	public SqlParameter getParams() {
		return null;
	}

}
