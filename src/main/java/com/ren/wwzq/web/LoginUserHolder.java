package com.ren.wwzq.web;

import com.ren.wwzq.models.entity.User;


/**
 * @author: target
 * @date: 2020/5/8 13:39
 * @description:
 */
public class LoginUserHolder {

    private static ThreadLocal<User> loginUser = new ThreadLocal<>();

    /**
     * 获取当前登录用户
     */
    public static User getUser() {
        return loginUser.get();
    }

    public static void bind(User user) {
        loginUser.set(user);
    }

    public static void unbind() {
        loginUser.remove();
    }

    public static String getUserId() {
        if (getUser() == null) {
            return null;
        }
        return getUser().getId();
    }

    public static String getUserName() {
        if (getUser() == null) {
            return null;
        }
        return getUser().getName();
    }
}
