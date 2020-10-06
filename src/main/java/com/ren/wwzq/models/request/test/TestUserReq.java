package com.ren.wwzq.models.request.test;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author wings
 * @Date 2020/9/26
 */
@Data
public class TestUserReq implements Serializable {

    private static final long serialVersionUID = -1846400724612868683L;

    private String username;

    private transient String password;
}
