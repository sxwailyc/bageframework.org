package com.bageframework.dao.sql;

import com.bageframework.dao.jdbc.SqlParameter;

public interface SQL {

	public String getSql();

	public SqlParameter getParams();

}
