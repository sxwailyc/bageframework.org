package com.bageframework.mvc.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.bageframework.mvc.session.BageSession;
import com.bageframework.mvc.session.SessionService;
import com.bageframework.util.CookieUtils;
import com.bageframework.util.UUIDGenerator;

public class BageHttpRequestWrapper extends HttpServletRequestWrapper {

	private final static String BAGE_SESSION_ID = "BAGESESSIONID";

	private SessionService sessionService;

	private String sessionId;

	private HttpSession session;

	public BageHttpRequestWrapper(HttpServletRequest request, HttpServletResponse response, SessionService sessionService) {

		super(request);
		this.sessionService = sessionService;

		sessionId = CookieUtils.getCookie(request, BAGE_SESSION_ID);
		if (StringUtils.isEmpty(sessionId)) {
			sessionId = UUIDGenerator.getUUID();
			CookieUtils.setCookie(request, response, BAGE_SESSION_ID, sessionId, -1);
		}
	}

	@Override
	public HttpSession getSession() {
		return this.getSession(true);
	}

	@Override
	public HttpSession getSession(boolean create) {
		if (session != null) {
			return session;
		} else if (!create) {
			return null;
		}

		session = new BageSession(sessionId, sessionService);
		return session;

	}
}
