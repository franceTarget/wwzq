package com.ren.wwzq.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author: target
 * @date: 2020/5/8 12:41
 * @description:
 */
@Slf4j
@Component
public class WsHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest req = (ServletServerHttpRequest)serverHttpRequest;
            //获取token认证
            String token = req.getServletRequest().getParameter("token");

            log.info("-------------token认证----------：" + token);
            if (StringUtils.isEmpty(token)) {
                log.info("token不能为空。。。");
                return false;
            }

            //检查token是否有效和是否过期

            //解析token获取用户信息

            //保存认证用户
            map.put("user", null);
            return true;
        }

        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
