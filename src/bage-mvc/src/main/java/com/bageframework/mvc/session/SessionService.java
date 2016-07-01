package com.bageframework.mvc.session;

import java.util.Map;

public interface SessionService {

	public void setDistributed(boolean distributed);

	public void delete(String sessionid);

	public Map<String, Object> getMap(String sessionId);

	public Object get(String sessionid, String name);

	public void put(String sessionid, String name, Object value);

	public void remove(String sessionid, String name);

}
