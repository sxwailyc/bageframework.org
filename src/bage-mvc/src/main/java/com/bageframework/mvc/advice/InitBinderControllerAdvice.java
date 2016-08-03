package com.bageframework.mvc.advice;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.bageframework.mvc.propertyeditors.CustomNumberEditor;

@ControllerAdvice
public class InitBinderControllerAdvice {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Number.class, new CustomNumberEditor());
	}

}