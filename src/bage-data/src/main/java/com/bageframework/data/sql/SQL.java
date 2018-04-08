package com.bageframework.data.sql;

import com.bageframework.data.DB;
import com.bageframework.data.jdbc.SqlParameter;

public interface SQL {

	public String getSql(DB db);

	public SqlParameter getParams();

}
