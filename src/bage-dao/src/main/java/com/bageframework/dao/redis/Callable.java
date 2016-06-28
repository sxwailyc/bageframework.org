package com.bageframework.dao.redis;

import redis.clients.jedis.ShardedJedis;

public interface Callable {

	public Object execute(ShardedJedis jedis);

}
