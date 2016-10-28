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
@RequestMapping(value = PropertyAdminController.DIR)
public class PropertyAdminController {

	public static final String DIR = "/admin/property";

	public static Logger logger = Logger.getLogger(PropertyAdminController.class);

	@RequestMapping
	public ModelAndView list(HttpServletRequest req, String metadataId) {

		ModelAndView model = new ModelAndView("/admin/property/list");
		model.addObject("metadataId", metadataId);
		return model;

	}
}
