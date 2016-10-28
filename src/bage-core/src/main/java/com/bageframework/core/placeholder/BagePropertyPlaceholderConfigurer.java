package com.bageframework.core.placeholder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.bageframework.core.env.Env;

public class BagePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	/**
	 * 读取自定义属性文件
	 * 
	 * @return
	 */
	private Properties readLocalProperties() {

		Properties prop = new Properties();
		String jdbcProperties = "properties/" + Env.getEnv() + "/" + "jdbc.properties";
		logger.debug("start to load jdbc properties:" + jdbcProperties);
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(jdbcProperties);
		try {
			prop.load(in);
			return prop;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	protected Properties mergeProperties() throws IOException {

		this.setProperties(readLocalProperties());

		return super.mergeProperties();
	}

}
