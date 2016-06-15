package com.bageframework.demo.web.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = IndexAdminController.DIR)
public class IndexAdminController {

	public static final String DIR = "/admin/";

	private static Logger LOG = Logger.getLogger(IndexAdminController.class);

	@RequestMapping
	public ModelAndView index(HttpServletRequest req) {

		ModelAndView model = new ModelAndView("/admin/index");
		return model;

	}

}
