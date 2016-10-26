package com.bageframework.demo.web.controller.admin;

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
@RequestMapping(value = MenuAdminController.DIR)
public class MenuAdminController {

	public static final String DIR = "/admin/menu";

	public static Logger logger = Logger.getLogger(MenuAdminController.class);

	@RequestMapping
	public ModelAndView list(HttpServletRequest req) {

		ModelAndView model = new ModelAndView("/admin/menu/list");
		return model;

	}

}
