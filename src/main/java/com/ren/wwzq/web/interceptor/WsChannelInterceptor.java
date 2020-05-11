package com.ren.wwzq.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ImmutableMessageChannelInterceptor;
import org.springframework.stereotype.Component;


/**
 * @author: target
 * @date: 2019/2/20 14:29
 * @description:频道拦截器，类似管道，可以获取消息的一些meta数据
 */
@Slf4j
@Component
public class WsChannelInterceptor extends ImmutableMessageChannelInterceptor {

    /**
     * 在完成发送之后进行调用，不管是否有异常发生，一般用于资源清理
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel,
                                    boolean sent, Exception ex) {
        super.afterSendCompletion(message, channel, sent, ex);
    }

    /**
     * 在消息被实际发送到频道之前调用
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        return super.preSend(message, channel);
    }

    /**
     * 发送消息调用后立即调用
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel,
                         boolean sent) {
        // 消息头访问器
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
        if (headerAccessor.getCommand() == null) {
            return;// 避免非stomp消息类型，例如心跳检测
        }
    }

}
