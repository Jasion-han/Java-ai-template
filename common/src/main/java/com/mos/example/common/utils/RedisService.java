package com.mos.example.common.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author mos
 */
@Component
public class RedisService {

    // 注入自定义的RedisTemplate
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // 注入StringRedisTemplate
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 指定缓存失效时间
     *
     * @param key     缓存的key
     * @param timeout 超时时间
     */
    public Boolean expire(String key, long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取缓存失效时间
     *
     * @param key 缓存的key
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 根据规则匹配对应的所有key
     *
     * @param pattern 字符串前缀
     * @return 符合规则key构成的Set集合
     */
    public Set<String> getKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 判断key是否存在
     *
     * @param key 缓存的key
     * @return 是否存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除单个缓存
     *
     * @param key 缓存的key
     * @return 是否删除成功
     */
    public Boolean deleteKey(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除多个缓存
     *
     * @param collection 多个key构成的集合
     * @return 删除的key的数量
     */
    public Long deleteKey(Collection<String> collection) {
        return redisTemplate.delete(collection);
    }

    // ==============================String==================================

    /**
     * 获取缓存的字符串数据
     *
     * @param key 缓存的key
     * @return 缓存的值
     */
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 缓存字符串
     *
     * @param key   缓存的key
     * @param value 缓存的值
     */
    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存字符串并设置失效时间
     *
     * @param key     缓存的key
     * @param value   缓存的值
     * @param timeout 失效时间
     */
    public void setString(String key, String value, long timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    // ===============================Object=================================

    /**
     * 缓存基本对象（Integer、String、实体类等）
     *
     * @param key   缓存的key
     * @param value 缓存的值
     */
    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本对象并设置失效时间
     *
     * @param key     缓存的key
     * @param value   缓存的值
     * @param timeout 失效时间
     */
    public void setObject(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取缓存的基本对象
     *
     * @param key 缓存的key
     * @return 缓存的值
     */
    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 增加缓存的值（必须是整形）
     *
     * @param key   缓存的key
     * @param delta 增量
     * @return 缓存的值
     */
    public Long incrObject(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 增加缓存的值（必须是浮点形）
     *
     * @param key   缓存的key
     * @param delta 增量
     * @return 缓存的值
     */
    public Double incrObject(String key, double delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    // ===============================List=================================

    /**
     * 在List列表开头存入元素
     *
     * @param key    缓存的key
     * @param values 要存入的元素，可以是多个
     * @return List列表中的元素数量
     */
    public Long setListLeft(String key, Object... values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 在List列表开头存入集合
     *
     * @param key    缓存的key
     * @param values 要存入的集合
     * @return List列表中的元素数量
     */
    public Long setListLeft(String key, Collection<Object> values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 在List列表末尾存入元素
     *
     * @param key    缓存的key
     * @param values 要存入的元素，可以是多个
     * @return List列表中的元素数量
     */
    public Long setListRight(String key, Object... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 在List列表末尾存入集合
     *
     * @param key    缓存的key
     * @param values 要存入的集合
     * @return List列表中的元素数量
     */
    public Long setListRight(String key, Collection<Object> values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 获取缓存的List列表
     *
     * @param key 缓存的key
     * @return List列表中的所有元素
     */
    public List<Object> getList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 删除缓存的List列表中的元素
     *
     * @param key   缓存的key
     * @param count 删除数量（0：移除所有。正整数：从列表的左边移除。负整数：从列表的右边移除）
     * @param value 要删除的值
     * @return 删除元素的数量
     */
    public Long delListValue(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    // ===============================Set=================================

    /**
     * 向Set列表中存入元素
     *
     * @param key    缓存的key
     * @param values 要存入的元素，可以是多个
     * @return 本次缓存的数量，已存在的不会再次缓存
     */
    public Long setSet(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 获取缓存的Set列表
     *
     * @param key 缓存的key
     * @return Set列表中所有元素
     */
    public Set<Object> getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 删除缓存的Set列表中的元素
     *
     * @param key    缓存的key
     * @param values 要删除的值，可以是多个
     * @return 删除元素的数量
     */
    public Long delSetValue(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 差集
     *
     * @param key1 Set列表1
     * @param key2 Set列表2
     * @return key1和key2中，key1特有的元素
     */
    public Set<Object> getSetDiff(String key1, String key2) {
        return redisTemplate.opsForSet().difference(key1, key2);
    }

    /**
     * 交集
     *
     * @param key1 Set列表1
     * @param key2 Set列表2
     * @return key1和key2的公有元素
     */
    public Set<Object> getSetInter(String key1, String key2) {
        return redisTemplate.opsForSet().intersect(key1, key2);
    }

    /**
     * 并集
     *
     * @param key1 Set列表1
     * @param key2 Set列表2
     * @return key1和key2的所有元素
     */
    public Set<Object> getSetUnion(String key1, String key2) {
        return redisTemplate.opsForSet().union(key1, key2);
    }

    // ===============================Hash=================================

    /**
     * 向Hash列表中存入键值对
     *
     * @param key       缓存的key
     * @param hashKey   Hash列表的键名
     * @param hashValue Hash列表的键值
     */
    public void setHash(String key, String hashKey, Object hashValue) {
        redisTemplate.opsForHash().put(key, hashKey, hashValue);
    }

    /**
     * 向Hash列表中存入Map集合
     *
     * @param key 缓存的key
     * @param map 要存入的集合
     */
    public void setHash(String key, Map<Object, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 获取缓存的Hash列表的单个键值
     *
     * @param key     缓存的key
     * @param hashKey Hash列表的键名
     * @return Hash列表的键值
     */
    public Object getHash(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取缓存的Hash列表的所有键值对
     *
     * @param key 缓存的key
     * @return Hash列表的所有键值对
     */
    public Map<Object, Object> getHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 删除缓存的Hash列表的键值对
     *
     * @param key     缓存的key
     * @param hashKey Hash列表的键名，可以有多个
     * @return 删除键值对的数量
     */
    public Long delHashValue(String key, Object... hashKey) {
        return redisTemplate.opsForHash().delete(key, hashKey);
    }
}
