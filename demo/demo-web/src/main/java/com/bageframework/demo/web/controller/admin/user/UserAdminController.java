package com.bageframework.demo.web.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = UserAdminController.DIR)
public class UserAdminController {

	public static final String DIR = "/admin/user";

	private static Logger LOG = Logger.getLogger(UserAdminController.class);

	@RequestMapping
	public ModelAndView list(HttpServletRequest req) {

		ModelAndView model = new ModelAndView("/admin/user/list");
		return model;

	}

}
