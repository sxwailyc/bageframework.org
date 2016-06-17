package com.bageframework.coder.metadata;

public class DaoMetadata extends BaseClassMetadata {

	private String modelClassName;

	private String keyType;

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getModelClassName() {
		return modelClassName;
	}

	public void setModelClassName(String modelClassName) {
		this.modelClassName = modelClassName;
	}

}
