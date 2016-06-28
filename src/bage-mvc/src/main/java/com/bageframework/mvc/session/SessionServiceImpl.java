package com.bageframework.mvc.session;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	private SessionDao sessionDao;

	public void delete(String sessionid) {
		sessionDao.delete(sessionid);
	}

	public Map<String, Object> getMap(String sessionId) {
		return sessionDao.getMap(sessionId);
	}

	public Object get(String sessionid, String name) {
		return sessionDao.get(sessionid, name);
	}

	public void put(String sessionid, String name, Object value) {
		sessionDao.put(sessionid, name, value);
	}

	public void remove(String sessionid, String name) {
		sessionDao.remove(sessionid, name);
	}

}
