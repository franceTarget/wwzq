package com.ren.wwzq.models.response.test;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author wings
 * @Date 2020/9/26
 */
@Data
public class TestUserResp implements Serializable {

    private static final long serialVersionUID = 2255713299853385811L;

    private String username;

    private transient String password;
}
