package com.bageframework.demo.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SiteConfig {

	public static Logger logger = Logger.getLogger(SiteConfig.class);

	public static String uploadPath;

	static {
		Properties prop = new Properties();
		InputStream in = SiteConfig.class.getClassLoader().getResourceAsStream("site.properties");
		try {
			prop.load(in);
			uploadPath = prop.getProperty("upload.path");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static String getUploadPath() {
		return uploadPath;
	}

	public static void main(String[] args) {
		System.out.println(getUploadPath());
	}

}
