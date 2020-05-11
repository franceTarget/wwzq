package com.ren.wwzq.common.utils;

import com.ren.wwzq.AppStarter;
import com.ren.wwzq.common.config.CacheManageConfig;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author: target
 * @date: 2020/5/8 15:12
 * @description:
 */
public class EhcacheUtil {

    public static CacheManager getCacheManage() {
        return AppStarter.getBean(CacheManageConfig.class).getInstance();
    }

    public static void put(String cacheName, String key, Object value) {
        Cache cache = get(cacheName);
        Element element = new Element(key, value);
        cache.put(element);
    }

    public static Object get(String cacheName, String key) {
        Cache cache = get(cacheName);
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    public static Cache get(String cacheName) {
        return getCacheManage().getCache(cacheName);
    }

    public static void remove(String cacheName, String key) {
        Cache cache = get(cacheName);
        cache.remove(key);
    }

}
