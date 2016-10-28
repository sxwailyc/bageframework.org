package com.bageframework.youzhi.web.processor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.bageframework.authority.model.User;
import com.bageframework.mvc.processor.ContextProcessor;
import com.bageframework.youzhi.web.service.MenuService;
import com.bageframework.youzhi.web.vo.admin.MenuAdminVO;

@Component
public class MenuProcessor implements ContextProcessor {

	@Autowired
	private MenuService menuService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {

//		User user = (User) request.getSession().getAttribute("user");
//		if (user == null) {
//			return;
//		}

//		List<MenuAdminVO> menus = menuService.getAdminVOList();
//
//		modelAndView.addObject("menus", menus);
	}
}
