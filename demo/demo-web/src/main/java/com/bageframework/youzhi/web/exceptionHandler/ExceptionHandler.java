package com.bageframework.youzhi.web.exceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver {

	private final static Logger log = Logger.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception) {
		// log.error(exception.getMessage(), exception);
		// Map<String, Object> map = new HashMap<String, Object>();
		// int rc = 0;
		// if (exception instanceof ServiceException) {
		// rc = ((ServiceException) exception).getCode();
		// map.put("msg", ((ServiceException) exception).getMessage());
		// } else {
		// rc = ServiceReturnCode.FAILD;
		// }
		// map.put("rc", rc);
		// ModelAndView modelView = new ModelAndView();
		// MappingJacksonJsonView view = new MappingJacksonJsonView();
		// view.setAttributesMap(map);
		// modelView.setView(view);
		// return modelView;
		return null;
	}

}
