package com.bageframework.data.sql;

import com.bageframework.data.jdbc.SqlParameter;

public interface SQL {

	public String getSql();

	public SqlParameter getParams();

}
