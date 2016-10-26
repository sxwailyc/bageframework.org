package com.bageframework.demo.web.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bageframework.demo.web.model.User;
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

	@RequestMapping
	public ModelAndView login(HttpServletRequest req, HttpServletResponse response, String username, String password, String redirect) throws IOException {

		if (req.getMethod().equals("GET")) {

			ModelAndView model = new ModelAndView("/admin/user/login");
			return model;
		} else {

			if (StringUtils.isEmpty(redirect)) {
				redirect = "/admin/index.do";
			}

			User user = userService.login(username, password);

			com.bageframework.authority.model.User u = new com.bageframework.authority.model.User();
			u.setNickname(user.getNickname());
			u.setUserId(user.getUserId());
			req.getSession().setAttribute("user", u);

			response.sendRedirect(redirect);
			return null;
		}

	}

}
