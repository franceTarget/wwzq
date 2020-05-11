package com.ren.wwzq.web.handler;

import com.ren.wwzq.web.WsUserAuth;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.Map;

/**
 * @author: target
 * @date: 2020/5/8 12:34
 * @description:
 */
@Component
public class PrincipalHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected WsUserAuth determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
                                       Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest req = (ServletServerHttpRequest) request;
            //获取token认证
            String token = req.getServletRequest().getParameter("token");
            return (WsUserAuth) attributes.get(token);
        }
        return (WsUserAuth) request.getPrincipal();
    }
}
