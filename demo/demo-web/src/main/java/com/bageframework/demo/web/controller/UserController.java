package com.bageframework.demo.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(UserController.DIR)
public class UserController {

	public static final String DIR = "/api/user";

	private static Logger LOG = Logger.getLogger(UserController.class);

	@RequestMapping
	public ModelAndView list(HttpServletRequest req) {

		ModelAndView modelView = new ModelAndView();
		// MappingJacksonJsonView view = new MappingJacksonJsonView();
		// view.setAttributesMap(map);
		// modelView.setView(view);

		return modelView;

	}

}
