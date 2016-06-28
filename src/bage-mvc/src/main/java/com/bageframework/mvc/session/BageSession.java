package com.bageframework.mvc.session;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.commons.lang.NotImplementedException;

@SuppressWarnings("deprecation")
public class BageSession implements HttpSession {

	private long creationTime;

	private long lastAccessedTime;

	private String sid;

	private SessionService sessionService;

	public BageSession(String sid, SessionService sessionService) {
		this.sid = sid;
		this.sessionService = sessionService;
		this.creationTime = System.currentTimeMillis();
	}

	public Object getAttribute(String name) {
		lastAccessedTime = System.currentTimeMillis();
		return sessionService.get(this.sid, name);
	}

	public Enumeration<String> getAttributeNames() {
		Map<String, Object> map = sessionService.getMap(this.sid);
		return new BageEnumeration<String>(map.keySet());
	}

	public long getCreationTime() {
		return creationTime;
	}

	public String getId() {
		return sid;
	}

	public long getLastAccessedTime() {
		return lastAccessedTime;
	}

	public int getMaxInactiveInterval() {
		throw new NotImplementedException();
	}

	public ServletContext getServletContext() {
		throw new NotImplementedException();
	}

	public HttpSessionContext getSessionContext() {
		throw new NotImplementedException();
	}

	public Object getValue(String arg0) {

		return null;
	}

	public String[] getValueNames() {
		throw new NotImplementedException();
	}

	public void invalidate() {
		sessionService.delete(this.sid);
	}

	public boolean isNew() {
		throw new NotImplementedException();
	}

	public void putValue(String name, Object value) {
		sessionService.put(this.sid, name, value);
	}

	public void removeAttribute(String name) {
		sessionService.remove(this.sid, name);
	}

	public void removeValue(String name) {
		sessionService.remove(this.sid, name);
	}

	public void setAttribute(String name, Object value) {
		sessionService.put(this.sid, name, value);
	}

	public void setMaxInactiveInterval(int arg0) {
		throw new NotImplementedException();
	}

}
