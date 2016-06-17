package com.bageframework.coder.core;

public class Config {

	public final static String DEFAULT_MODEL_TEMPLATE = "model.ftl";

	public final static String DEFAULT_VO_TEMPLATE = "vo.ftl";

	public final static String DEFAULT_ADMIN_VO_TEMPLATE = "admin_vo.ftl";

	public final static String DEFAULT_SERVICE_TEMPLATE = "service.ftl";

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

	private String servicePackage;

	private String voPackage;

	private String adminVoPackage;

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

	public Config servicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
		return this;
	}

	public Config voPackage(String voPackage) {
		this.voPackage = voPackage;
		return this;
	}

	public Config adminVoPackage(String adminVoPackage) {
		this.adminVoPackage = adminVoPackage;
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

	public String getServiceTemplate() {
		return this.templateDir + "/" + DEFAULT_SERVICE_TEMPLATE;
	}

	public String getVOTemplate() {
		return this.templateDir + "/" + DEFAULT_VO_TEMPLATE;
	}

	public String getAdminVOTemplate() {
		return this.templateDir + "/" + DEFAULT_ADMIN_VO_TEMPLATE;
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

	public String getVOPackage() {
		if (voPackage == null) {
			return packagePrefix + ".vo";
		}
		return voPackage;
	}

	public String getAdminVOPackage() {
		if (adminVoPackage == null) {
			return packagePrefix + ".vo.admin";
		}
		return adminVoPackage;
	}

	public String getServicePackage() {
		if (servicePackage == null) {
			return packagePrefix + ".service";
		}
		return servicePackage;
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

	/**
	 * 获取model的保存路径
	 * 
	 * @param className
	 * @return
	 */
	public String getVOClassPath(String className) {
		String modelClassPath = this.getModelProjectDir() + "src" + "/main/java/" + getPackagePrefix().replaceAll("\\.", "/") + "/vo/" + className + ".java";
		return modelClassPath;
	}

	/**
	 * 获取model的保存路径
	 * 
	 * @param className
	 * @return
	 */
	public String getAdminVOClassPath(String className) {
		String modelClassPath = this.getModelProjectDir() + "src" + "/main/java/" + getPackagePrefix().replaceAll("\\.", "/") + "/vo/admin/" + className + ".java";
		return modelClassPath;
	}

	/**
	 * 获取service的保存路径
	 * 
	 * @param className
	 * @return
	 */
	public String getServiceClassPath(String className) {
		String serviceClassPath = this.getServiceProjectDir() + "src" + "/main/java/" + getPackagePrefix().replaceAll("\\.", "/") + "/service/" + className + "Service.java";
		return serviceClassPath;
	}
}
