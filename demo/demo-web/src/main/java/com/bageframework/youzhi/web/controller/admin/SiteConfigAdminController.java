package com.bageframework.demo.web.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Controller
@RequestMapping(value = SiteConfigAdminController.DIR)
public class SiteConfigAdminController {

	public static final String DIR = "/admin/siteConfig";

	public static Logger logger = Logger.getLogger(SiteConfigAdminController.class);

	@RequestMapping
	public ModelAndView list(HttpServletRequest req) {

		ModelAndView model = new ModelAndView("/admin/siteconfig/list");
		return model;

	}

}
