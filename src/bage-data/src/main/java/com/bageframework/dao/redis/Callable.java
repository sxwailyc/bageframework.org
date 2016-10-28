package com.bageframework.dao.redis;

import redis.clients.jedis.Jedis;

public interface Callable {

	public Object execute(Jedis jedis);

}
