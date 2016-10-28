package com.bageframework.youzhi.web.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bageframework.util.DateTimeUtil;
import com.bageframework.util.DigestUtil;
import com.bageframework.youzhi.web.SiteConfig;
import com.bageframework.youzhi.web.exception.BageRuntimeException;
import com.bageframework.youzhi.web.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

	public static Logger logger = Logger.getLogger(UploadServiceImpl.class);

	@Override
	public String upload(MultipartFile upfile, String base) {

		String originalFilename = upfile.getOriginalFilename();
		try {
			String[] datas = originalFilename.split("\\.");
			String extend = datas[datas.length - 1];
			byte[] bytes = upfile.getBytes();
			String md5 = DigestUtil.MD5(bytes);
			String name = DateTimeUtil.getDateStr() + "/" + md5 + "." + extend;
			String fullSaveName = SiteConfig.getUploadPath() + name;
			FileUtils.writeByteArrayToFile(new File(fullSaveName), bytes);
			return name;
		} catch (IOException ie) {
			logger.error(ie.getMessage(), ie);
			throw new BageRuntimeException(ie.getMessage());
		}
	}

}
