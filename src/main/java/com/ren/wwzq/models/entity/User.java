package com.ren.wwzq.models.entity;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

}
