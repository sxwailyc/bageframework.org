package com.bageframework.demo.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(ArticleController.DIR)
public class ArticleController {

	public static final String DIR = "/";

	private static Logger logger = Logger.getLogger(ArticleController.class);

	@RequestMapping(value = "/{name:.*\\.html}")
	public ModelAndView handle(HttpServletRequest req, @PathVariable String name) {

		logger.debug(name);

		ModelAndView model = new ModelAndView("/article/list");
		return model;

	}

	public ModelAndView list(HttpServletRequest req, String name) {

		logger.debug(name);

		ModelAndView model = new ModelAndView("/article/list");
		return model;

	}

	public ModelAndView detail(HttpServletRequest req, String name) {

		logger.debug(name);

		ModelAndView model = new ModelAndView("/article/detail");
		return model;

	}
}
