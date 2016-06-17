package com.bageframework.coder.core;

public class Config {

	public final static String DEFAULT_MODEL_TEMPLATE = "model.ftl";

	/**
	 * 是否拆分子项目
	 */
	public boolean splitProject = false;

	/**
	 * 项目地址
	 */
	public String projectDir;

	public String modelProjectDir;

	public String daoProjectDir;

	public String serviceProjectDir;

	public String webProjectDir;

	public String adminJspDir;

	public String templateDir = "template";

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

	public String getModelTemplate() {
		return this.templateDir + "/" + DEFAULT_MODEL_TEMPLATE;
	}

	public String getProjectDir() {
		return projectDir;
	}

	public String getModelProjectDir() {
		if (modelProjectDir == null && !splitProject) {
			return projectDir;
		}
		return modelProjectDir;
	}

	public String getDaoProjectDir() {
		if (daoProjectDir == null && !splitProject) {
			return projectDir;
		}
		return daoProjectDir;
	}

	public String getServiceProjectDir() {
		if (serviceProjectDir == null && !splitProject) {
			return projectDir;
		}
		return serviceProjectDir;
	}

	public String getWebProjectDir() {
		if (webProjectDir == null && !splitProject) {
			return projectDir;
		}
		return webProjectDir;
	}

	public Config projectDir(String projectDir) {
		this.projectDir = projectDir;
		return this;
	}

	public Config templateDir(String templateDir) {
		this.templateDir = templateDir;
		return this;
	}

	public String getModelPackage() {
		if (modelPackage == null) {
			return packagePrefix + ".model";
		}
		return modelPackage;
	}

	/**
	 * 获取model的保存路径
	 * 
	 * @param className
	 * @return
	 */
	public String getModelClassPath(String className) {
		String modelClassPath = this.getModelProjectDir() + "src" + "/main/java/" + getPackagePrefix().replaceAll("\\.", "/") + "/model/" + className + ".java";
		return modelClassPath;
	}
}
