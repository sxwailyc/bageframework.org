package com.bageframework.data.redis;

import redis.clients.jedis.Jedis;

public interface Callable {

	public Object execute(Jedis jedis);

}
