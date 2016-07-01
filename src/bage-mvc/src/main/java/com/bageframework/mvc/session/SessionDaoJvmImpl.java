package com.bageframework.mvc.session;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class SessionDaoJvmImpl implements SessionDao {

	private Map<String, Map<String, Object>> data = new ConcurrentHashMap<String, Map<String, Object>>();

	private String getSessionKey(String sessionId) {
		return "sid:" + sessionId;
	}

	public void delete(String sessionId) {
		data.remove(getSessionKey(sessionId));
	}

	public Map<String, Object> getMap(String sessionId) {
		return data.get(getSessionKey(sessionId));
	}

	public Object get(String sessionId, String name) {
		Map<String, Object> m = data.get(getSessionKey(sessionId));
		if (m != null) {
			return m.get(name);
		}
		return null;
	}

	public void put(String sessionId, String name, Object value) {
		Map<String, Object> m = data.get(getSessionKey(sessionId));
		if (m == null) {
			m = new HashMap<String, Object>();
			data.put(getSessionKey(sessionId), m);
		}
		m.put(name, value);
	}

	public void remove(String sessionId, String name) {
		Map<String, Object> m = data.get(getSessionKey(sessionId));
		if (m != null) {
			m.remove(sessionId);
		}
	}

}
