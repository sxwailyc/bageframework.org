package com.bageframework.demo.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bageframework.authority.annotation.Authority;
import com.bageframework.demo.web.model.User;

@Controller
@RequestMapping(TestController.DIR)
public class TestController {

	public static final String DIR = "/";

	private static Logger LOG = Logger.getLogger(TestController.class);

	@RequestMapping
	@ResponseBody
	@Authority(code = "EDIT")
	public Object test(HttpServletRequest req) {

		Map<String, Object> data = new HashMap<String, Object>();

//		User user = new User();
//		user.setNickname("昵称");
//
//		req.getSession().setAttribute("username", "sxwailyc");
//		req.getSession().setAttribute("user", user);

		return data;

	}

	@RequestMapping
	@ResponseBody
	public Object test2(HttpServletRequest req) {

		Map<String, Object> data = new HashMap<String, Object>();

		Object username = req.getSession().getAttribute("username");
		data.put("username", username);
		data.put("user", req.getSession().getAttribute("user"));

		return data;

	}

}
