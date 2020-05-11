package com.ren.wwzq.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import java.security.Principal;


/**
 * @author: target
 * @date: 2019/2/28 14:13
 * @description:握手控制工厂类
 */
@Slf4j
@Component
public class WsDecoratorFactory implements WebSocketHandlerDecoratorFactory {

    @Override
    public WebSocketHandler decorate(WebSocketHandler handler) {
        return new WebSocketHandlerDecorator(handler) {
            @Override
            public void afterConnectionEstablished(WebSocketSession session)
                throws Exception {
                log.info("有人连接啦  sessionId = {}", session.getId());
                Principal principal = session.getPrincipal();
                if (principal != null) {
                    // 身份校验成功，缓存socket连接
                    WebSocketManager.add(principal.getName(), session);
                }
                super.afterConnectionEstablished(session);
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
                throws Exception {
                log.info("有人退出连接啦  sessionId = {}", session.getId());
                Principal principal = session.getPrincipal();
                if (principal != null) {
                    // 身份校验成功，移除socket连接
                    WebSocketManager.remove(principal.getName());
                }
                super.afterConnectionClosed(session, closeStatus);
            }

            /**
             * 处理来自WebSocket消息传输的错误，类似与@OnError注解
             */
            @Override
            public void handleTransportError(WebSocketSession session, Throwable exception)
                throws Exception {
                log.error("客户端" + session.getId() + "传输异常");
                //一定要移除
                Principal principal = session.getPrincipal();
                if (principal != null) {
                    // 身份校验成功，移除socket连接
                    WebSocketManager.remove(principal.getName());
                }
                session.close();
                super.handleTransportError(session, exception);
            }

            /**
             * 表示是否让WebSocket支持处理大文件的拆分处理，默认为false
             */
            @Override
            public boolean supportsPartialMessages() {
                //不需要进行大文件的拆分处理
                return false;
            }
        };
    }
}
