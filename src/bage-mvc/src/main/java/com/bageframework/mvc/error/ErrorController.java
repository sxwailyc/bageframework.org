package com.bageframework.mvc.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bageframework.mvc.constants.BageMvcConstant;
import com.bageframework.mvc.view.TextView;

@Controller
public class ErrorController {

	public static Logger logger = Logger.getLogger(ErrorController.class);

	@RequestMapping(value = "/error.do")
	public ModelAndView error(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setStatus(200);

		return new TextView("system error");

	}

	@RequestMapping(value = "/pageNotFound.do")
	public ModelAndView pageNotFound(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String uri = (String) request.getAttribute(BageMvcConstant.RAW_REQUEST_URI);
		if (logger.isDebugEnabled()) {
			logger.debug("raw request uri[" + uri + "]");
		}
		response.setStatus(200);

		return new TextView("page not found[" + uri + "]");

	}

}
