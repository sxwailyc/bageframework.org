package com.bageframework.demo.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bageframework.demo.web.bean.ArticlePage;
import com.bageframework.demo.web.service.ArticleService;
import com.bageframework.demo.web.vo.ArticleVO;

@Controller
@RequestMapping(IndexController.DIR)
public class IndexController {

	@Autowired
	private ArticleService articleService;

	private static final int PAGE_SIZE = 10;

	public static final String DIR = "/";

	@RequestMapping(value = "")
	public ModelAndView index(HttpServletRequest req) {
		return render(req, 1);
	}

	@RequestMapping(value = "index-{page}.html")
	public ModelAndView list(HttpServletRequest req, @PathVariable("page") int page) {
		return render(req, page);
	}

	private ModelAndView render(HttpServletRequest req, int page) {

		ModelAndView model = new ModelAndView("/index");

		List<ArticleVO> list = articleService.getVOList(0, (page - 1) * 10, PAGE_SIZE);
		int count = articleService.getCount(0);

		ArticlePage articlePage = new ArticlePage("", count, page);

		model.addObject("recomList", articleService.getRecommendList(0, 5));
		model.addObject("hotList", articleService.getHotList(0, 5));
		model.addObject("newList", articleService.getNewList(0, 5));

		model.addObject("page", articlePage);
		model.addObject("list", list);

		return model;

	}
}
