package com.bageframework.demo.web.controller.admin.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bageframework.demo.web.service.UserService;

@Controller
@RequestMapping(value = UserAdminController.DIR)
public class UserAdminController {

	public static final String DIR = "/admin/user";

	private static Logger LOG = Logger.getLogger(UserAdminController.class);

	@Autowired
	private UserService userService;

	@RequestMapping
	public ModelAndView list(HttpServletRequest req) {

		ModelAndView model = new ModelAndView("/admin/user/list");
		return model;

	}

	@RequestMapping
	public ModelAndView list2(HttpServletRequest req) {

		ModelAndView model = new ModelAndView("/admin/user/list2");
		return model;

	}

}
