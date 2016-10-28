package com.bageframework.youzhi.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bageframework.youzhi.web.SiteConfig;

@Controller
@RequestMapping(StaticProxyController.DIR)
public class StaticProxyController {

	public static final String DIR = "/upload";

	private static Logger logger = Logger.getLogger(StaticProxyController.class);

	@RequestMapping(value = { "/{name}", "/{dir1}/{name}", "/{dir1}/{dir2}/{name}", "/{dir1}/{dir2}/{dir3}/{name}" })
	public ResponseEntity<byte[]> handle(HttpServletRequest req, HttpServletResponse res, @PathVariable("dir1") String dir1, @PathVariable("dir2") String dir2,
			@PathVariable("dir3") String dir3, @PathVariable("name") String name) throws IOException {

		String fullFilename = name;
		if (dir3 != null) {
			fullFilename = dir1 + "/" + dir2 + "/" + dir3 + "/" + name;
		} else if (dir2 != null) {
			fullFilename = dir1 + "/" + dir2 + "/" + name;
		} else if (dir1 != null) {
			fullFilename = dir1 + "/" + name;
		}
		logger.debug(fullFilename);

		return _handle(res, fullFilename);

	}

	private ResponseEntity<byte[]> _handle(HttpServletResponse res, String name) throws IOException {

		String filename = SiteConfig.getUploadPath() + name;
		File file = new File(filename);
		if (!file.exists()) {
			logger.warn("file not exist:" + name);
			res.sendError(404);
			return null;
		}

		byte[] bytes = FileUtils.readFileToByteArray(file);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
	}

}
