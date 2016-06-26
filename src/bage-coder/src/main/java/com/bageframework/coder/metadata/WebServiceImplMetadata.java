package com.bageframework.coder.metadata;

public class WebServiceImplMetadata extends BaseClassMetadata {

	private String keyType;

	private String serviceObjectName;

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getServiceObjectName() {
		return serviceObjectName;
	}

	public void setServiceObjectName(String serviceObjectName) {
		this.serviceObjectName = serviceObjectName;
	}

}
