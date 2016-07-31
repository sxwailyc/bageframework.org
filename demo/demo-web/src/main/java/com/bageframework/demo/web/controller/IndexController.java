package com.bageframework.demo.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(IndexController.DIR)
public class IndexController {

	public static final String DIR = "/";

	private static Logger logger = Logger.getLogger(IndexController.class);

	@RequestMapping
	public ModelAndView index(HttpServletRequest req) {

		ModelAndView model = new ModelAndView("/index");
		return model;

	}

}
