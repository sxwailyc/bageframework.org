package com.bageframework.data.redis;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;

public class RedisImpl implements Redis {

	private redis.clients.jedis.JedisPool pool;

	public void setPool(redis.clients.jedis.JedisPool pool) {
		this.pool = pool;
	}

	public redis.clients.jedis.JedisPool getPool() {
		return pool;
	}

	private Object call(Callable callable) {
		Jedis jedis = pool.getResource();
		try {
			return callable.execute(jedis);
		} finally {
			jedis.close();
		}
	}

	@Override
	public String set(final String key, final String value) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.set(key, value);
			}
		});
	}

	@Override
	public String get(final String key) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.get(key);
			}
		});
	}

	@Override
	public Boolean exists(final String key) {
		return (Boolean) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.exists(key);
			}
		});
	}

	@Override
	public String type(final String key) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.type(key);
			}
		});
	}

	@Override
	public Long expire(final String key, final int seconds) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.expire(key, seconds);
			}
		});
	}

	@Override
	public Long expireAt(final String key, final long unixTime) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.expireAt(key, unixTime);
			}
		});
	}

	@Override
	public Long ttl(final String key) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.ttl(key);
			}
		});
	}

	@Override
	public Boolean setbit(final String key, final long offset, final boolean value) {
		return (Boolean) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.setbit(key, offset, value);
			}
		});
	}

	@Override
	public Boolean getbit(final String key, final long offset) {
		return (Boolean) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.getbit(key, offset);
			}
		});
	}

	@Override
	public Long setrange(final String key, final long offset, final String value) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.setrange(key, offset, value);
			}
		});
	}

	@Override
	public String getrange(final String key, final long startOffset, final long endOffset) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.getrange(key, startOffset, endOffset);
			}
		});
	}

	@Override
	public String getSet(final String key, final String value) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.getSet(key, value);
			}
		});
	}

	@Override
	public Long setnx(final String key, final String value) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.setnx(key, value);
			}
		});
	}

	@Override
	public String setex(final String key, final int seconds, final String value) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.setex(key, seconds, value);
			}
		});
	}

	@Override
	public Long decrBy(final String key, final long integer) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.decrBy(key, integer);
			}
		});
	}

	@Override
	public Long decr(final String key) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.decr(key);
			}
		});
	}

	@Override
	public Long incrBy(final String key, final long integer) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.incrBy(key, integer);
			}
		});
	}

	@Override
	public Long incr(final String key) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.incr(key);
			}
		});
	}

	@Override
	public Long append(final String key, final String value) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.append(key, value);
			}
		});
	}

	@Override
	public String substr(final String key, final int start, final int end) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.substr(key, start, end);
			}
		});
	}

	@Override
	public Long hset(final String key, final String field, final String value) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hset(key, field, value);
			}
		});
	}

	@Override
	public String hget(final String key, final String field) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}

	@Override
	public Long hsetnx(final String key, final String field, final String value) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hsetnx(key, field, value);
			}
		});
	}

	@Override
	public String hmset(final String key, final Map<String, String> hash) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hmset(key, hash);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> hmget(final String key, final String... fields) {
		return (List<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hmget(key, fields);
			}
		});
	}

	@Override
	public Long hincrBy(final String key, final String field, final long value) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hincrBy(key, field, value);
			}
		});
	}

	@Override
	public Boolean hexists(final String key, final String field) {
		return (Boolean) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hexists(key, field);
			}
		});
	}

	@Override
	public Long hlen(final String key) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hlen(key);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> hkeys(final String key) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hkeys(key);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> hvals(final String key) {
		return (List<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hvals(key);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> hgetAll(final String key) {
		return (Map<String, String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}

	@Override
	public Long llen(final String key) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.llen(key);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> lrange(final String key, final long start, final long end) {
		return (List<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.lrange(key, start, end);
			}
		});
	}

	@Override
	public String ltrim(final String key, final long start, final long end) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.ltrim(key, start, end);
			}
		});
	}

	@Override
	public String lindex(final String key, final long index) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.lindex(key, index);
			}
		});
	}

	@Override
	public String lset(final String key, final long index, final String value) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.lset(key, index, value);
			}
		});
	}

	@Override
	public Long lrem(final String key, final long count, final String value) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.lrem(key, count, value);
			}
		});
	}

	@Override
	public String lpop(final String key) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.lpop(key);
			}
		});
	}

	@Override
	public String rpop(final String key) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.rpop(key);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> smembers(final String key) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.smembers(key);
			}
		});
	}

	@Override
	public String spop(final String key) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.spop(key);
			}
		});
	}

	@Override
	public Long scard(final String key) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.scard(key);
			}
		});
	}

	@Override
	public Boolean sismember(final String key, final String member) {
		return (Boolean) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.sismember(key, member);
			}
		});
	}

	@Override
	public String srandmember(final String key) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.srandmember(key);
			}
		});
	}

	@Override
	public Long zadd(final String key, final double score, final String member) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zadd(key, score, member);
			}
		});
	}

	@Override
	public Double zincrby(final String key, final double score, final String member) {
		return (Double) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zincrby(key, score, member);
			}
		});
	}

	@Override
	public Long zrank(final String key, final String member) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrank(key, member);
			}
		});
	}

	@Override
	public Long zrevrank(final String key, final String member) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrevrank(key, member);
			}
		});
	}

	@Override
	public Long zcard(final String key) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zcard(key);
			}
		});
	}

	@Override
	public Double zscore(final String key, final String member) {
		return (Double) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zscore(key, member);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> sort(final String key) {
		return (List<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.sort(key);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> sort(final String key, final SortingParams sortingParameters) {
		return (List<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.sort(key, sortingParameters);
			}
		});
	}

	@Override
	public Long zcount(final String key, final double min, final double max) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zcount(key, min, max);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrangeByScore(final String key, final double min, final double max) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrangeByScore(key, min, max);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrevrangeByScore(final String key, final double max, final double min) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrevrangeByScore(key, max, min);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrangeByScore(final String key, final double min, final double max, final int offset, final int count) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrangeByScore(key, min, max, offset, count);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrevrangeByScore(final String key, final double max, final double min, final int offset, final int count) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrevrangeByScore(key, max, min, offset, count);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max) {
		return (Set<Tuple>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min) {
		return (Set<Tuple>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max, final int offset, final int count) {
		return (Set<Tuple>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min, final int offset, final int count) {
		return (Set<Tuple>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
			}
		});
	}

	@Override
	public Long zremrangeByScore(final String key, final double start, final double end) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zremrangeByScore(key, start, end);
			}
		});
	}

	@Override
	public Long linsert(final String key, final LIST_POSITION where, final String pivot, final String value) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.linsert(key, where, pivot, value);
			}
		});
	}

	@Override
	public Long del(final String key) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.del(key);
			}
		});
	}

	@Override
	public String set(final String key, final String value, final String nxxx, final String expx, final long time) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.set(key, value, nxxx, expx, time);
			}
		});
	}

	@Override
	public String set(final String key, final String value, final String nxxx) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.set(key, value, nxxx);
			}
		});
	}

	@Override
	public Long persist(final String key) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.persist(key);
			}
		});
	}

	@Override
	public Long pexpire(final String key, final long milliseconds) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.pexpire(key, milliseconds);
			}
		});
	}

	@Override
	public Long pexpireAt(final String key, final long millisecondsTimestamp) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.pexpireAt(key, millisecondsTimestamp);
			}
		});
	}

	@Override
	public Long pttl(final String key) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.pttl(key);
			}
		});
	}

	@Override
	public Boolean setbit(final String key, final long offset, final String value) {
		return (Boolean) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.setbit(key, offset, value);
			}
		});
	}

	@Override
	public String psetex(final String key, final long milliseconds, final String value) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.psetex(key, milliseconds, value);
			}
		});
	}

	@Override
	public Double incrByFloat(final String key, final double value) {
		return (Double) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.incrByFloat(key, value);
			}
		});
	}

	@Override
	public Double hincrByFloat(final String key, final String field, final double value) {
		return (Double) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hincrByFloat(key, field, value);
			}
		});
	}

	@Override
	public Long hdel(final String key, final String... field) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hdel(key, field);
			}
		});
	}

	@Override
	public Long rpush(final String key, final String... string) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.rpush(key, string);
			}
		});
	}

	@Override
	public Long lpush(final String key, final String... string) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.lpush(key, string);
			}
		});
	}

	@Override
	public Long sadd(final String key, final String... member) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.sadd(key, member);
			}
		});
	}

	@Override
	public Long srem(final String key, final String... member) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.srem(key, member);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> spop(final String key, final long count) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.spop(key, count);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> srandmember(final String key, final int count) {
		return (List<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.spop(key, count);
			}
		});
	}

	@Override
	public Long strlen(final String key) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.strlen(key);
			}
		});
	}

	@Override
	public Long zadd(final String key, final double score, final String member, final ZAddParams params) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zadd(key, score, member, params);
			}
		});
	}

	@Override
	public Long zadd(final String key, final Map<String, Double> scoreMembers) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zadd(key, scoreMembers);
			}
		});
	}

	@Override
	public Long zadd(final String key, final Map<String, Double> scoreMembers, final ZAddParams params) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zadd(key, scoreMembers, params);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrange(final String key, final long start, final long end) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrange(key, start, end);
			}
		});
	}

	@Override
	public Long zrem(final String key, final String... member) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrem(key, member);
			}
		});
	}

	@Override
	public Double zincrby(final String key, final double score, final String member, final ZIncrByParams params) {
		return (Double) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zincrby(key, score, member, params);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrevrange(final String key, final long start, final long end) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrevrange(key, start, end);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Tuple> zrangeWithScores(final String key, final long start, final long end) {
		return (Set<Tuple>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrangeWithScores(key, start, end);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Tuple> zrevrangeWithScores(final String key, final long start, final long end) {
		return (Set<Tuple>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrevrangeWithScores(key, start, end);
			}
		});
	}

	@Override
	public Long zcount(final String key, final String min, final String max) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zcount(key, min, max);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrangeByScore(final String key, final String min, final String max) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrangeByScore(key, min, max);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrevrangeByScore(final String key, final String max, final String min) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrevrangeByScore(key, max, min);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrangeByScore(final String key, final String min, final String max, final int offset, final int count) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrangeByScore(key, min, max, offset, count);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrevrangeByScore(final String key, final String max, final String min, final int offset, final int count) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrevrangeByScore(key, max, min, offset, count);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max) {
		return (Set<Tuple>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrangeByScoreWithScores(key, min, max);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String max, final String min) {
		return (Set<Tuple>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max, int offset, int count) {
		return (Set<Tuple>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String max, final String min, final int offset, final int count) {
		return (Set<Tuple>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
			}
		});
	}

	@Override
	public Long zremrangeByRank(final String key, final long start, final long end) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zremrangeByRank(key, start, end);
			}
		});
	}

	@Override
	public Long zremrangeByScore(final String key, final String start, final String end) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zremrangeByScore(key, start, end);
			}
		});
	}

	@Override
	public Long zlexcount(final String key, final String min, final String max) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zlexcount(key, min, max);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrangeByLex(final String key, final String min, final String max) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrangeByLex(key, min, max);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrangeByLex(final String key, final String min, final String max, final int offset, final int count) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrangeByLex(key, min, max, offset, count);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrevrangeByLex(final String key, final String max, final String min) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrevrangeByLex(key, max, min);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrevrangeByLex(final String key, final String max, final String min, final int offset, final int count) {
		return (Set<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zrevrangeByLex(key, max, min, offset, count);
			}
		});
	}

	@Override
	public Long zremrangeByLex(final String key, final String min, final String max) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zremrangeByLex(key, min, max);
			}
		});
	}

	@Override
	public Long lpushx(final String key, final String... string) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.lpushx(key, string);
			}
		});
	}

	@Override
	public Long rpushx(final String key, final String... string) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.rpushx(key, string);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> blpop(final String arg) {
		return (List<String>) this.call(new Callable() {
			@SuppressWarnings("deprecation")
			@Override
			public Object execute(Jedis jedis) {
				return jedis.blpop(arg);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> blpop(final int timeout, final String key) {
		return (List<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.blpop(timeout, key);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> brpop(final String arg) {
		return (List<String>) this.call(new Callable() {
			@SuppressWarnings("deprecation")
			@Override
			public Object execute(Jedis jedis) {
				return jedis.brpop(arg);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> brpop(final int timeout, final String key) {
		return (List<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.brpop(timeout, key);
			}
		});
	}

	@Override
	public String echo(final String string) {
		return (String) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.echo(string);
			}
		});
	}

	@Override
	public Long move(final String key, final int dbIndex) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.move(key, dbIndex);
			}
		});
	}

	@Override
	public Long bitcount(final String key) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.bitcount(key);
			}
		});
	}

	@Override
	public Long bitcount(final String key, final long start, final long end) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.bitcount(key, start, end);
			}
		});
	}

	@Override
	public Long bitpos(final String key, final boolean value) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.bitpos(key, value);
			}
		});
	}

	@Override
	public Long bitpos(final String key, final boolean value, final BitPosParams params) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.bitpos(key, value, params);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ScanResult<Entry<String, String>> hscan(final String key, final int cursor) {
		return (ScanResult<Entry<String, String>>) this.call(new Callable() {
			@SuppressWarnings("deprecation")
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hscan(key, cursor);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ScanResult<String> sscan(final String key, final int cursor) {
		return (ScanResult<String>) this.call(new Callable() {
			@SuppressWarnings("deprecation")
			@Override
			public Object execute(Jedis jedis) {
				return jedis.sscan(key, cursor);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ScanResult<Tuple> zscan(final String key, final int cursor) {
		return (ScanResult<Tuple>) this.call(new Callable() {
			@SuppressWarnings("deprecation")
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zscan(key, cursor);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ScanResult<Entry<String, String>> hscan(final String key, final String cursor) {
		return (ScanResult<Entry<String, String>>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hscan(key, cursor);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ScanResult<Entry<String, String>> hscan(final String key, final String cursor, final ScanParams params) {
		return (ScanResult<Entry<String, String>>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.hscan(key, cursor, params);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ScanResult<String> sscan(final String key, final String cursor) {
		return (ScanResult<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.sscan(key, cursor);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ScanResult<String> sscan(final String key, final String cursor, final ScanParams params) {
		return (ScanResult<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.sscan(key, cursor, params);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ScanResult<Tuple> zscan(final String key, final String cursor) {
		return (ScanResult<Tuple>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zscan(key, cursor);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ScanResult<Tuple> zscan(final String key, final String cursor, final ScanParams params) {
		return (ScanResult<Tuple>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.zscan(key, cursor, params);
			}
		});
	}

	@Override
	public Long pfadd(final String key, final String... elements) {
		return (long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.pfadd(key, elements);
			}
		});
	}

	@Override
	public long pfcount(final String key) {
		return (long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.pfcount(key);
			}
		});
	}

	@Override
	public Long geoadd(final String key, final double longitude, final double latitude, final String member) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.geoadd(key, longitude, latitude, member);
			}
		});
	}

	@Override
	public Long geoadd(final String key, final Map<String, GeoCoordinate> memberCoordinateMap) {
		return (Long) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.geoadd(key, memberCoordinateMap);
			}
		});
	}

	@Override
	public Double geodist(final String key, final String member1, final String member2) {
		return (Double) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.geodist(key, member1, member2);
			}
		});
	}

	@Override
	public Double geodist(final String key, final String member1, final String member2, final GeoUnit unit) {
		return (Double) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.geodist(key, member1, member2, unit);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> geohash(final String key, final String... members) {
		return (List<String>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.geohash(key, members);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GeoCoordinate> geopos(final String key, final String... members) {
		return (List<GeoCoordinate>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.geopos(key, members);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GeoRadiusResponse> georadius(final String key, final double longitude, final double latitude, final double radius, final GeoUnit unit) {
		return (List<GeoRadiusResponse>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.georadius(key, longitude, latitude, radius, unit);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GeoRadiusResponse> georadius(final String key, final double longitude, final double latitude, final double radius, final GeoUnit unit, final GeoRadiusParam param) {
		return (List<GeoRadiusResponse>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.georadius(key, longitude, latitude, radius, unit, param);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GeoRadiusResponse> georadiusByMember(final String key, final String member, final double radius, final GeoUnit unit) {
		return (List<GeoRadiusResponse>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.georadiusByMember(key, member, radius, unit);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GeoRadiusResponse> georadiusByMember(final String key, final String member, final double radius, final GeoUnit unit, final GeoRadiusParam param) {
		return (List<GeoRadiusResponse>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.georadiusByMember(key, member, radius, unit, param);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> bitfield(final String key, final String... arguments) {
		return (List<Long>) this.call(new Callable() {
			@Override
			public Object execute(Jedis jedis) {
				return jedis.bitfield(key, arguments);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> mget(final String... key) {
		return (List<String>) this.call(new Callable() {

			@Override
			public Object execute(Jedis jedis) {
				return jedis.mget(key);
			}
		});
	}

}
