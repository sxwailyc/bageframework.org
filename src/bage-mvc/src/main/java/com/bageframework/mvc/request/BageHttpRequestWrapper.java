package com.bageframework.mvc.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.bageframework.core.config.BageConfig;
import com.bageframework.mvc.session.BageDistributedSession;
import com.bageframework.mvc.session.BageLocalSession;
import com.bageframework.mvc.session.SessionService;
import com.bageframework.util.CookieUtils;
import com.bageframework.util.UUIDGenerator;

public class BageHttpRequestWrapper extends HttpServletRequestWrapper {

	private final static String BAGE_SESSION_ID = "BAGESESSIONID";

	private BageConfig bageMvcConfig;

	private SessionService sessionService;

	private String sessionId;

	private HttpSession session;

	public BageHttpRequestWrapper(HttpServletRequest request, HttpServletResponse response, SessionService sessionService, BageConfig bageMvcConfig) {

		super(request);
		this.sessionService = sessionService;
		this.bageMvcConfig = bageMvcConfig;

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

		sessionService.setDistributed(bageMvcConfig.isEnableDistributedSession());
		if (bageMvcConfig.isEnableDistributedSession()) {
			session = new BageDistributedSession(sessionId, sessionService);
		} else {
			session = new BageLocalSession(sessionId, sessionService);
		}
		return session;

	}
}
