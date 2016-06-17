package com.bageframework.coder.helper;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CoderHelper {

	protected static Log logger = LogFactory.getLog(CoderHelper.class);

	public static void save(String path, String content) {
		try {
			FileUtils.writeStringToFile(new File(path), content);
		} catch (IOException ie) {
			logger.error(ie.getMessage(), ie);
			throw new RuntimeException("save file error.path[" + path + "], message[" + ie.getMessage() + "]");
		}
	}
}
