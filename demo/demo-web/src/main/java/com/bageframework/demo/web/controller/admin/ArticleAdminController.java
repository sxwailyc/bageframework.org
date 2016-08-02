package com.bageframework.demo.web.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bageframework.demo.web.service.UploadService;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Controller
@RequestMapping(value = ArticleAdminController.DIR)
public class ArticleAdminController {

	public static final String DIR = "/admin/article";

	public static Logger logger = Logger.getLogger(ArticleAdminController.class);

	@Autowired
	private UploadService uploadService;

	@RequestMapping
	public ModelAndView list(HttpServletRequest req) {

		ModelAndView model = new ModelAndView("/admin/article/list");
		return model;

	}

	@RequestMapping
	public ModelAndView edit(HttpServletRequest req, Number id) {

		ModelAndView model = new ModelAndView("/admin/article/edit");
		return model;

	}

	@RequestMapping
	@ResponseBody
	public Object dispatch(HttpServletRequest req, String action, MultipartFile upfile) {

		logger.debug(action);

		if ("config".equals(action)) {
			return config(req);
		} else if ("uploadimage".equals(action)) {
			return upload(upfile);
		}
		return null;

	}

	public Object upload(MultipartFile upfile) {

		String name = uploadService.upload(upfile, "/");

		Map<String, String> m = new HashMap<String, String>();
		m.put("state", "SUCCESS");
		m.put("url", "/upload/" + name);
		m.put("title", upfile.getOriginalFilename());
		m.put("original", upfile.getOriginalFilename());
		return m;
	}

	public ModelAndView config(HttpServletRequest req) {

		ModelAndView model = new ModelAndView("/admin/article/config");
		return model;

	}
}
