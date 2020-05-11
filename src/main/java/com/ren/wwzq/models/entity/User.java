package com.ren.wwzq.models.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: target
 * @date: 2020/5/8 13:35
 * @description:
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -4134717129081676807L;

    private String id;

    private String name;

    private String nickname;

    private String phone;

    private String password;

}
