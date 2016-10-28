package com.bageframework.dao.redis;

import java.util.List;

import redis.clients.jedis.JedisCommands;

public interface Redis extends JedisCommands {

	public Long del(String key);

	public List<String> mget(String... key);
}
