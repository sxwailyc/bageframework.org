package com.bageframework.mvc.session;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bageframework.dao.redis.Redis;
import com.bageframework.util.JsonSerializer;

@Repository
public class SessionDaoRedisImpl implements SessionDao {

	@Resource(name = "session")
	private Redis redis;

	private String getSessionKey(String sessionId) {
		return "sid:" + sessionId;
	}

	public void delete(String sessionid) {

	}

	public Map<String, Object> getMap(String sessionId) {
		Map<String, String> m = redis.hgetAll(getSessionKey(sessionId));
		Map<String, Object> result = new HashMap<String, Object>();
		for (Entry<String, String> entry : m.entrySet()) {
			Object obj = JsonSerializer.deserialize(entry.getValue());
			result.put(entry.getKey(), obj);
		}
		return result;
	}

	public Object get(String sessionid, String name) {
		String json = redis.hget(getSessionKey(sessionid), name);
		return JsonSerializer.deserialize(json);
	}

	public void put(String sessionid, String name, Object value) {
		String json = JsonSerializer.serialize(value);
		redis.hset(getSessionKey(sessionid), name, json);
	}

	public void remove(String sessionId, String name) {
		redis.hdel(getSessionKey(sessionId), name);
	}

}
