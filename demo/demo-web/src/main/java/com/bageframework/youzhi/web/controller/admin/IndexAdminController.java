package com.bageframework.youzhi.web.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bageframework.youzhi.web.service.MenuService;
import com.bageframework.youzhi.web.vo.admin.MenuAdminVO;

@Controller
@RequestMapping(value = IndexAdminController.DIR)
public class IndexAdminController {

	public static final String DIR = "/admin/";

	@Autowired
	private MenuService menuService;

	@RequestMapping
	public ModelAndView index(HttpServletRequest req) {

		ModelAndView model = new ModelAndView("/admin/index");

		List<MenuAdminVO> menus = menuService.getAdminVOList();

		model.addObject("menus", menus);

		return model;

	}

	@RequestMapping
	public ModelAndView empty(HttpServletRequest req) {

		ModelAndView model = new ModelAndView("/admin/empty");
		return model;

	}

}
