package com.bageframework.mvc.session;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

	private boolean distributed = false;

	@Autowired
	private SessionDao sessionDaoRedisImpl;

	@Autowired
	private SessionDao sessionDaoJvmImpl;

	private SessionDao getDao() {
		if (distributed) {
			return sessionDaoRedisImpl;
		} else {
			return sessionDaoJvmImpl;
		}
	}

	public void setDistributed(boolean distributed) {
		this.distributed = distributed;
	}

	public void delete(String sessionid) {
		getDao().delete(sessionid);
	}

	public Map<String, Object> getMap(String sessionId) {
		return getDao().getMap(sessionId);
	}

	public Object get(String sessionid, String name) {
		return getDao().get(sessionid, name);
	}

	public void put(String sessionid, String name, Object value) {
		getDao().put(sessionid, name, value);
	}

	public void remove(String sessionid, String name) {
		getDao().remove(sessionid, name);
	}

}
