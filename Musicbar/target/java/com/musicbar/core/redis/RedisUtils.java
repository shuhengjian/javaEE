package com.musicbar.core.redis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Title:Redis工具类 Copyright: Copyright (c) 2017 Company:creatorblue.co.,ltd
 * 操作字符串redis缓存方法 list中的操作全是按照right方式
 * https://www.cnblogs.com/0201zcr/p/4987561.html
 * 
 * @author creatorblue.co.,ltd
 * @version 1.0
 */
@Component
public class RedisUtils {
	private Logger logger = Logger.getLogger(RedisUtils.class);
	@Autowired
	public RedisTemplate<String, String> redisTemplate;
	@Autowired
	private ValueOperations<String, String> valueOperations;
	@Autowired
	private HashOperations<String, String, Object> hashOperations;
	@SuppressWarnings("rawtypes")
	@Autowired
	private  ListOperations  listOperations;
	@SuppressWarnings("rawtypes")
	@Autowired
	private SetOperations setOperations;
	/** 默认过期时长，单位：秒 */
	public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
	/** 不设置过期时长 */
	public final static long NOT_EXPIRE = -1;
	private final static ObjectMapper gson = new ObjectMapper();

	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 * 
	 * @param key
	 *            缓存的键值
	 * @param value
	 *            缓存的值
	 * @return 缓存的对象
	 */
	public void setCacheObject(String key, Object value, long expire) {
		valueOperations.set(key, toJson(value));
		if (expire != NOT_EXPIRE) {
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
	}

	public void setCacheObject(String key, Object value) {
		setCacheObject(key, value, DEFAULT_EXPIRE);
	}

	/**
	 * 获得缓存的基本对象。
	 * 
	 * @param key
	 *            缓存键值
	 * @param operation
	 * @return 缓存键值对应的数据
	 */
	public <T> T getCacheObject(String key, Class<T> clazz, long expire) {
		String value = valueOperations.get(key);
		if (expire != NOT_EXPIRE) {
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
		return value == null ? null : fromJson(value, clazz);
	}

	public <T> T getCacheObject(String key, Class<T> clazz) {
		return getCacheObject(key, clazz, NOT_EXPIRE);
	}

	public String getCacheObject(String key, long expire) {
		String value = valueOperations.get(key);
		if (expire != NOT_EXPIRE) {
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
		return value;
	}

	public String getCacheObject(String key) {
		return getCacheObject(key, NOT_EXPIRE);
	}



	/**
	 * Object转成JSON数据
	 */
	private String toJson(Object object) {
		if (object instanceof Integer || object instanceof Long || object instanceof Float || object instanceof Double
				|| object instanceof Boolean || object instanceof String) {
			return String.valueOf(object);
		}
		try {
			return gson.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * JSON数据，转成Object
	 */
	private <T> T fromJson(String json, Class<T> clazz) {
		try {
			return gson.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 缓存set操作
	 * 
	 * @param k
	 * @param v
	 * @param time
	 * @return
	 */
	public boolean setCacheSet(String key, String v, long time) {
		try {
			setOperations = redisTemplate.opsForSet();
			setOperations.add(key, v);
			if (time > 0)
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			return true;
		} catch (Throwable t) {
			logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
		}
		return false;
	}

	/**
	 * 缓存set
	 * 
	 * @param k
	 * @param v
	 * @return
	 */
	public boolean setCacheSet(String key, String v) {
		return setCacheSet(key, v, -1);
	}

	/**
	 * 缓存set
	 * 
	 * @param k
	 * @param v
	 * @param time
	 * @return
	 */
	public boolean setCacheSet(String key, Set<String> v, long time) {
		try {
			setOperations = redisTemplate.opsForSet();
			setOperations.add(key, v.toArray(new String[v.size()]));
			if (time > 0)
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			return true;
		} catch (Throwable t) {
			logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
		}
		return false;
	}

	/**
	 * 缓存set
	 * 
	 * @param k
	 * @param v
	 * @return
	 */
	public boolean setCacheSet(String key, Set<String> v) {
		return setCacheSet(key, v, -1);
	}

	/**
	 * 获取缓存set数据
	 * 
	 * @param k
	 * @return
	 */
	public Set<String> getCacheSet(String key) {
		try {
			setOperations = redisTemplate.opsForSet();
			return setOperations.members(key);
		} catch (Throwable t) {
			logger.error("获取set缓存失败key[" + key + ", error[" + t + "]");
		}
		return null;
	}

	/**
	 * list缓存
	 * 
	 * @param k
	 * @param v
	 * @param time
	 * @return
	 */
	public boolean setCacheList(String key, String v, long time) {
		try {
			listOperations = redisTemplate.opsForList();
			listOperations.rightPush(key, v);
			if (time > 0)
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			return true;
		} catch (Throwable t) {
			logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
		}
		return false;
	}

	/**
	 * 缓存list
	 * 
	 * @param k
	 * @param v
	 * @return
	 */
	public boolean setCacheList(String key, String v) {
		return setCacheList(key, v, -1);
	}

	/**
	 * 缓存list
	 * 
	 * @param k
	 * @param v
	 * @param time
	 * @return
	 */
	public boolean setCacheList(String key, List<String> v, long time) {
		try {
			ListOperations<String, String> listOps = redisTemplate.opsForList();
			long l = listOps.rightPushAll(key, v);
			if (time > 0)
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			return true;
		} catch (Throwable t) {
			logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
		}
		return false;
	}

	/**
	 * 哈希 添加
	 * 
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public void setCacheHash(String key, Object hashKey, Object value) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		hash.put(key, hashKey, value);
	}

	/**
	 * 哈希获取数据
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public Object setCacheHash(String key, Object hashKey) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		return hash.get(key, hashKey);
	}

	/**
	 * 缓存list
	 * 
	 * @param k
	 * @param v
	 * @return
	 */
	public boolean setCacheList(String key, List<String> v) {
		return setCacheList(key, v, -1);
	}

	/**
	 * 获取list缓存
	 * 
	 * @param k
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> getCacheList(String key, long start, long end) {
		try {
			ListOperations<String, String> listOps = redisTemplate.opsForList();
			return listOps.range(key, start, end);
		} catch (Throwable t) {
			logger.error("获取list缓存失败key[" + key + ", error[" + t + "]");
		}
		return null;
	}

	/**
	 * 获取总条数, 可用于分页
	 * 
	 * @param key
	 * @return
	 */
	public long getCacheListSize(String key) {
		try {
			ListOperations<String, String> listOps = redisTemplate.opsForList();
			return listOps.size(key);
		} catch (Throwable t) {
			logger.error("获取list长度失败key[" + key + "], error[" + t + "]");
		}
		return 0;
	}

	/**
	 * 获取总条数, 可用于分页
	 * 
	 * @param listOps
	 * @param k
	 * @return
	 */
	public long getCacheListSize(ListOperations<String, String> listOps, String key) {
		try {
			return listOps.size(key);
		} catch (Throwable t) {
			logger.error("获取list长度失败key[" + key + "], error[" + t + "]");
		}
		return 0;
	}
	/**
	 * 移除缓存
	 * 
	 * @param key
	 * @return
	 */
	public void deleteCache(String key) {
		try {
			redisTemplate.delete(key);
		} catch (Throwable t) {
			logger.error("获取缓存失败key[" + key + ", error[" + t + "]");
		}
	}

	/**
	 * 移除缓存
	 * 
	 * @param key
	 * @return
	 */
	public void deleteCacheValue(String key) {
		deleteCache(key);
	}

	/**
	 * 移除缓存
	 * 
	 * @param key
	 * @return
	 */
	public void deleteCacheSet(String key) {
		deleteCache(key);
	}

	/**
	 * 移除list缓存
	 * 
	 * @param k
	 * @return
	 */
	public boolean deleteCacheList(String key) {
		try {
			ListOperations<String, String> listOps = redisTemplate.opsForList();
			listOps.rightPop(key);
			return true;
		} catch (Throwable t) {
			logger.error("移除list缓存失败key[" + key + ", error[" + t + "]");
		}
		return false;
	}
}
