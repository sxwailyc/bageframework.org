package com.bageframework.youzhi.web.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	public String upload(MultipartFile upfile, String base);
}
