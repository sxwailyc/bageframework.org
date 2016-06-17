package com.bageframework.coder.metadata;

public class ServiceImplMetadata extends BaseClassMetadata {

	private String keyType;

	private String daoObjectName;

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getDaoObjectName() {
		return daoObjectName;
	}

	public void setDaoObjectName(String daoObjectName) {
		this.daoObjectName = daoObjectName;
	}

}
