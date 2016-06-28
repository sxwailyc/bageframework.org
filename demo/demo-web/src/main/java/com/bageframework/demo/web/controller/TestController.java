package com.bageframework.demo.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(TestController.DIR)
public class TestController {

	public static final String DIR = "/";

	private static Logger LOG = Logger.getLogger(TestController.class);

	@RequestMapping
	@ResponseBody
	public Object test(HttpServletRequest req) {

		Map<String, Object> data = new HashMap<String, Object>();

		req.getSession().setAttribute("username", "sxwailyc");

		return data;

	}

	@RequestMapping
	@ResponseBody
	public Object test2(HttpServletRequest req) {

		Map<String, Object> data = new HashMap<String, Object>();

		Object username = req.getSession().getAttribute("username");
		data.put("username", username);

		return data;

	}

}
