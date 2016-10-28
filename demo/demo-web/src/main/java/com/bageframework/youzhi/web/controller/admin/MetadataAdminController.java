package com.bageframework.youzhi.web.controller.admin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Controller
@RequestMapping(value = MetadataAdminController.DIR)
public class MetadataAdminController {

	public static final String DIR = "/admin/metadata";

	public static Logger logger = Logger.getLogger(MetadataAdminController.class);

	@RequestMapping
	public ModelAndView list(HttpServletRequest req) {

		ModelAndView model = new ModelAndView("/admin/metadata/list");
		return model;

	}

}
