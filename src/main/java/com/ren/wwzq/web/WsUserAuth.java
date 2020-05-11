package com.ren.wwzq.web;

import java.security.Principal;

/**
 * @author: target
 * @date: 2020/5/8 10:23
 * @description:
 */
public class WsUserAuth implements Principal {

    /**
     * 用户身份标识符
     */
    private String token;

    public WsUserAuth(String token) {
        this.token = token;
    }

    @Override
    public String getName() {
        return token;
    }
}
