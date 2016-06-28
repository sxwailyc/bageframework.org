package com.bageframework.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

public class RedisImpl implements Redis {

	private redis.clients.jedis.ShardedJedisPool pool;

	public void setPool(redis.clients.jedis.ShardedJedisPool pool) {
		this.pool = pool;
	}

	private Object call(Callable callable) {
		ShardedJedis jedis = pool.getResource();
		try {
			return callable.execute(jedis);
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public String set(final String key, final String value) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(ShardedJedis jedis) {
				return jedis.set(key, value);
			}
		});
	}

	@Override
	public String get(final String key) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(ShardedJedis jedis) {
				return jedis.get(key);
			}
		});
	}

	@Override
	public Boolean exists(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String type(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long expire(String key, int seconds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long expireAt(String key, long unixTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long ttl(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setbit(String key, long offset, boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getbit(String key, long offset) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long setrange(String key, long offset, String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getrange(String key, long startOffset, long endOffset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSet(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long setnx(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setex(String key, int seconds, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long decrBy(String key, long integer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long decr(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long incrBy(String key, long integer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long incr(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long append(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String substr(String key, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long hset(final String key, final String field, final String value) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(ShardedJedis jedis) {
				return jedis.hset(key, field, value);
			}
		});
	}

	@Override
	public String hget(final String key, final String field) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(ShardedJedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}

	@Override
	public Long hsetnx(String key, String field, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long hincrBy(String key, String field, long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean hexists(String key, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long hdel(String key, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long hlen(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> hkeys(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> hvals(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> hgetAll(final String key) {
		return (Map<String, String>) this.call(new Callable() {
			@Override
			public Object execute(ShardedJedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}

	@Override
	public Long rpush(String key, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long lpush(String key, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long llen(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ltrim(String key, long start, long end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String lindex(String key, long index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String lset(String key, long index, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long lrem(String key, long count, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String lpop(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String rpop(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long sadd(String key, String member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> smembers(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long srem(String key, String member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String spop(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long scard(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean sismember(String key, String member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String srandmember(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zadd(String key, double score, String member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrange(String key, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zrem(String key, String member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double zincrby(String key, double score, String member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zrank(String key, String member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zrevrank(String key, String member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrevrange(String key, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrangeWithScores(String key, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(String key, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zcard(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double zscore(String key, String member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> sort(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> sort(String key, SortingParams sortingParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zcount(String key, double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zremrangeByRank(String key, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zremrangeByScore(String key, double start, double end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
