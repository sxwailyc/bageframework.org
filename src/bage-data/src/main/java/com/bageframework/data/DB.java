package com.bageframework.data;

public enum DB {

	CROSS(0), MYSQL(1), MSSQL(2);

	public int value;

	private DB(int value) {
		this.value = value;
	}

}
