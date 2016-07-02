package com.bageframework.mvc.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface ContextProcessor {

	public void process(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView);

}
