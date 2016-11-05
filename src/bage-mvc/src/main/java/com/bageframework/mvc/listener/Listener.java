package com.bageframework.mvc.listener;

import org.springframework.web.context.WebApplicationContext;

public interface Listener {

	public void onStart(WebApplicationContext context);

}
