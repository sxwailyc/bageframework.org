package com.bageframework.youzhi.web.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Controller
@RequestMapping(value = WeixinArticleAdminController.DIR)
public class WeixinArticleAdminController {

	public static final String DIR = "/admin/weixinArticle";

	public static Logger logger = Logger.getLogger(WeixinArticleAdminController.class);

	@RequestMapping
	public ModelAndView list(HttpServletRequest req) {

		ModelAndView model = new ModelAndView("/admin/weixinarticle/list");
		return model;

	}

}
