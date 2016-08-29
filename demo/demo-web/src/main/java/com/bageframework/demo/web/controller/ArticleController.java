package com.bageframework.demo.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bageframework.demo.web.bean.ArticlePage;
import com.bageframework.demo.web.dao.CategoryDao;
import com.bageframework.demo.web.helper.PageHelper;
import com.bageframework.demo.web.model.Category;
import com.bageframework.demo.web.service.ArticleService;
import com.bageframework.demo.web.vo.ArticleVO;

@Controller
@RequestMapping(ArticleController.DIR)
public class ArticleController {

	public static final String DIR = "/";

	private static Logger logger = Logger.getLogger(ArticleController.class);

	private final static int PAGE_SIZE = 10;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CategoryDao categoryDao;

	@RequestMapping(value = "/{name:.*\\.html}")
	public ModelAndView list(HttpServletRequest req, @PathVariable String name) {

		logger.debug(name);
		String categoryName = PageHelper.getName(name);
		int page = PageHelper.getPage(name);
		Category category = categoryDao.getByName(categoryName);

		List<ArticleVO> list = articleService.getVOList(category.getId(), (page - 1) * 10, PAGE_SIZE);
		int count = articleService.getCount(category.getId());

		ArticlePage articlePage = new ArticlePage(categoryName, count, page);

		ModelAndView model = new ModelAndView("/article/list");
		model.addObject("category", category);
		model.addObject("list", list);
		model.addObject("page", articlePage);
		model.addObject("categoryName", categoryName);

		model.addObject("recomList", articleService.getRecommendList(category.getId(), 5));
		model.addObject("hotList", articleService.getHotList(category.getId(), 5));
		model.addObject("newList", articleService.getNewList(category.getId(), 5));

		return model;

	}

	@RequestMapping(value = { "/article/{date:.*}/{name:.*\\.html}" })
	public ModelAndView detail(HttpServletRequest req, @PathVariable String date, @PathVariable String name) {

		String staticName = date + "/" + name;
		logger.debug(staticName);
		ArticleVO article = articleService.getByName(staticName);
		if (article == null) {
			logger.warn("文章不存在.name[" + staticName + "]");
		}

		ModelAndView model = new ModelAndView("/article/detail");
		model.addObject("article", article);

		model.addObject("recomList", articleService.getRecommendList(article.getCategory(), 5));
		model.addObject("hotList", articleService.getHotList(article.getCategory(), 5));
		model.addObject("newList", articleService.getNewList(article.getCategory(), 5));

		return model;

	}
}
