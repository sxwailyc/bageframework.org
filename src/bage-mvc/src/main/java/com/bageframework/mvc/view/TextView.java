package com.bageframework.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TextView extends AbstractView {

	private String message;

	public TextView(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	@Override
	public String getContentType() {
		return "text/plain; charset=UTF-8";
	}

	@Override
	public String getBody(HttpServletRequest request, HttpServletResponse response) {
		String message = this.getMessage();
		return message;
	}
}
