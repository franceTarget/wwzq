package com.ren.wwzq.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;


/**
 * @author: target
 * @date: 2019/2/27 17:35
 * @description:
 */
@Slf4j
public class WebSocketManager {
    private static ConcurrentHashMap<String, WebSocketSession> manager = new ConcurrentHashMap<String, WebSocketSession>();

    public static void add(String key, WebSocketSession webSocketSession) {
        log.info("新添加webSocket连接 {} ", key);
        manager.put(key, webSocketSession);
    }

    public static void remove(String key) {
        log.info("移除webSocket连接 {} ", key);
        manager.remove(key);
    }

    public static WebSocketSession get(String key) {
        log.info("获取webSocket连接 {}", key);
        return manager.get(key);
    }
}
