package com.bageframework.coder.metadata;

public class AdminControllerMetadata extends BaseClassMetadata {

	private String pathPrefix;

	private String templatePath;

	public String getPathPrefix() {
		return pathPrefix;
	}

	public void setPathPrefix(String pathPrefix) {
		this.pathPrefix = pathPrefix;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

}
