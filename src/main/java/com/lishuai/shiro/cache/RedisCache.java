package com.lishuai.shiro.cache;

import com.lishuai.utils.ApplicationContextUtils;
import com.lishuai.utils.RedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 自定义 Redis缓存
 * @author lishuai
 */
public class RedisCache<K,V> implements Cache<K,V> {

    private String cacheName;

    public RedisCache() {
    }

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }


    @Override
    public V get(K k) throws CacheException {
        return (V) getRedisUtil().hget(this.cacheName,k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        Map<String,Object> map = new HashMap<>();
        map.put(k.toString(),v);
        getRedisUtil().hmset(this.cacheName,map);
        return null;
    }

    /**
     * 清除一个用户记录
     * @param k
     * @return
     * @throws CacheException
     */
    @Override
    public V remove(K k) throws CacheException {
            getRedisUtil().deleteHashOne(this.cacheName,k.toString());
            return (V)"删除成功！";
    }

    /**
     * 清除缓存
     * @throws CacheException
     */
    @Override
    public void clear() throws CacheException {
            getRedisUtil().del(this.cacheName);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    private RedisUtil getRedisUtil(){
        return (RedisUtil) ApplicationContextUtils.getBean("RedisUtil");
    }
}