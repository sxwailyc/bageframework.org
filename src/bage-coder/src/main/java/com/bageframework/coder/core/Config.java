package com.bageframework.coder.core;

public class Config {

	public String author;

	public String packagePrefix;

	private String modelPackage;

	public static Config create() {
		return new Config();
	}

	public Config author(String author) {
		this.author = author;
		return this;
	}

	public Config packagePrefix(String packagePrefix) {
		this.packagePrefix = packagePrefix;
		return this;
	}

	public Config modelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public String getPackagePrefix() {
		return packagePrefix;
	}

	public String getModelPackage() {
		if (modelPackage == null) {
			return packagePrefix + ".model";
		}
		return modelPackage;
	}

}
