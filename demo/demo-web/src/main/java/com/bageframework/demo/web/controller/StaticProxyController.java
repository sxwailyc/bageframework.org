package com.bageframework.demo.web.controller;

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

import com.bageframework.demo.web.SiteConfig;

@Controller
@RequestMapping(StaticProxyController.DIR)
public class StaticProxyController {

	public static final String DIR = "/upload";

	private static Logger logger = Logger.getLogger(StaticProxyController.class);

	@RequestMapping(value = { "/{date}/{name}" })
	public ResponseEntity<byte[]> handle(HttpServletRequest req, HttpServletResponse res, @PathVariable("date") String date, @PathVariable("name") String name) throws IOException {

		String fullFilename = date + "/" + name;
		logger.debug(fullFilename);

		String filename = SiteConfig.getUploadPath() + fullFilename;
		File file = new File(filename);
		if (!file.exists()) {
			logger.warn("file not exist:" + fullFilename);
			res.sendError(404);
			return null;
		}

		byte[] bytes = FileUtils.readFileToByteArray(file);

		HttpHeaders headers = new HttpHeaders();
		// headers.setContentDispositionFormData("attachment", name);
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);

	}

}
