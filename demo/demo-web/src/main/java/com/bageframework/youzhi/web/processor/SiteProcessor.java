package com.bageframework.youzhi.web.processor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.bageframework.mvc.processor.ContextProcessor;
import com.bageframework.youzhi.web.model.SiteConfig;
import com.bageframework.youzhi.web.service.CategoryService;
import com.bageframework.youzhi.web.service.SiteConfigService;
import com.bageframework.youzhi.web.vo.CategoryVO;

@Component
public class SiteProcessor implements ContextProcessor {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SiteConfigService siteConfigService;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {

		// category
		List<CategoryVO> categorys = categoryService.getVOList();
		modelAndView.addObject("categorys", categorys);

		SiteConfig siteConfig = siteConfigService.get(1);
		modelAndView.addObject("siteConfig", siteConfig);
	}
}
