package com.bageframework.coder.core;

public class Config {

	public final static String DEFAULT_MODEL_TEMPLATE = "model.ftl";

	public final static String DEFAULT_VO_TEMPLATE = "vo.ftl";

	public final static String DEFAULT_ADMIN_VO_TEMPLATE = "admin.vo.ftl";

	public final static String DEFAULT_DAO_TEMPLATE = "dao.ftl";

	public final static String DEFAULT_DAO_MYSQL_IMPL_TEMPLATE = "dao.mysql.impl.ftl";

	public final static String DEFAULT_SERVICE_TEMPLATE = "service.ftl";

	public final static String DEFAULT_SERVICE_IMPL_TEMPLATE = "service.impl.ftl";

	public final static String DEFAULT_WEB_SERVICE_TEMPLATE = "web.service.ftl";

	public final static String DEFAULT_WEB_SERVICE_IMPL_TEMPLATE = "web.service.impl.ftl";

	public final static String DEFAULT_ADMIN_CONTROLLER_TEMPLATE = "web.admin.controller.ftl";

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

	private String serviceImplPackage;

	private String webServicePackage;

	private String webServiceImplPackage;

	private String daoPackage;

	private String daoMysqlImplPackage;

	private String voPackage;

	private String adminVoPackage;

	private String adminControllerPackage;

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

	public String getVOTemplate() {
		return this.templateDir + "/" + DEFAULT_VO_TEMPLATE;
	}

	public String getAdminVOTemplate() {
		return this.templateDir + "/" + DEFAULT_ADMIN_VO_TEMPLATE;
	}

	public String getDaoTemplate() {
		return this.templateDir + "/" + DEFAULT_DAO_TEMPLATE;
	}

	public String getDaoMysqlImplTemplate() {
		return this.templateDir + "/" + DEFAULT_DAO_MYSQL_IMPL_TEMPLATE;
	}

	public String getServiceTemplate() {
		return this.templateDir + "/" + DEFAULT_SERVICE_TEMPLATE;
	}

	public String getServiceImplTemplate() {
		return this.templateDir + "/" + DEFAULT_SERVICE_IMPL_TEMPLATE;
	}

	public String getWebServiceTemplate() {
		return this.templateDir + "/" + DEFAULT_WEB_SERVICE_TEMPLATE;
	}

	public String getWebServiceImplTemplate() {
		return this.templateDir + "/" + DEFAULT_WEB_SERVICE_IMPL_TEMPLATE;
	}

	public String getAdminControllerTemplate() {
		return this.templateDir + "/" + DEFAULT_ADMIN_CONTROLLER_TEMPLATE;
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

	public String getServiceImplPackage() {
		if (serviceImplPackage == null) {
			return packagePrefix + ".service.impl";
		}
		return serviceImplPackage;
	}

	public String getWebServicePackage() {
		if (servicePackage == null) {
			return packagePrefix + ".controller.service";
		}
		return webServicePackage;
	}

	public String getWebServiceImplPackage() {
		if (serviceImplPackage == null) {
			return packagePrefix + ".controller.service.impl";
		}
		return webServiceImplPackage;
	}

	public String getAdminControllerlPackage() {
		if (adminControllerPackage == null) {
			return packagePrefix + ".controller.admin";
		}
		return adminControllerPackage;
	}

	public String getDaoPackage() {
		if (daoPackage == null) {
			return packagePrefix + ".dao";
		}
		return daoPackage;
	}

	public String getDaoMysqlImplPackage() {
		if (daoMysqlImplPackage == null) {
			return packagePrefix + ".dao.impl.mysql";
		}
		return daoMysqlImplPackage;
	}

	/**
	 * 获取model的保存路径
	 * 
	 * @param className
	 * @return
	 */
	public String getModelClassPath(String className) {
		return getClassSavePath(getModelProjectDir(), getModelPackage(), className);
	}

	/**
	 * 获取vo的保存路径
	 * 
	 * @param className
	 * @return
	 */
	public String getVOClassPath(String className) {
		return getClassSavePath(getServiceProjectDir(), getVOPackage(), className);
	}

	/**
	 * 获取admin vo的保存路径
	 * 
	 * @param className
	 * @return
	 */
	public String getAdminVOClassPath(String className) {
		return this.getClassSavePath(getServiceProjectDir(), this.getAdminVOPackage(), className);
	}

	/**
	 * 获取service的保存路径
	 * 
	 * @param className
	 * @return
	 */
	public String getServiceClassPath(String className) {
		return this.getClassSavePath(getServiceProjectDir(), this.getServicePackage(), className);
	}

	/**
	 * 获取service impl 的保存路径
	 * 
	 * @param className
	 * @return
	 */
	public String getServiceImplClassPath(String className) {
		return this.getClassSavePath(getServiceProjectDir(), this.getServiceImplPackage(), className);
	}

	/**
	 * 获取dao的保存路径
	 * 
	 * @param className
	 * @return
	 */
	public String getDaoClassPath(String className) {
		return this.getClassSavePath(getDaoProjectDir(), this.getDaoPackage(), className);
	}

	/**
	 * 获取dao mysql impl 的保存路径
	 * 
	 * @param className
	 * @return
	 */
	public String getDaoMysqlImplClassPath(String className) {
		return this.getClassSavePath(getDaoProjectDir(), this.getDaoMysqlImplPackage(), className);
	}

	/**
	 * 获取webservice的保存路径
	 * 
	 * @param className
	 * @return
	 */
	public String getWebServiceClassPath(String className) {
		return this.getClassSavePath(getWebProjectDir(), this.getWebServicePackage(), className);
	}

	/**
	 * 获取webservice impl 的保存路径
	 * 
	 * @param className
	 * @return
	 */
	public String getWebServiceImplClassPath(String className) {
		return this.getClassSavePath(getWebProjectDir(), this.getWebServiceImplPackage(), className);
	}

	/**
	 * 获取admin controller 的保存路径
	 * 
	 * @param className
	 * @return
	 */
	public String getAdminControllerClassPath(String className) {
		return this.getClassSavePath(getWebProjectDir(), this.getAdminControllerlPackage(), className);
	}

	public String getClassSavePath(String projectDir, String packageName, String className) {
		String classSavePath = projectDir + "src" + "/main/java/" + packageName.replaceAll("\\.", "/") + "/" + className + ".java";
		return classSavePath;
	}

}
