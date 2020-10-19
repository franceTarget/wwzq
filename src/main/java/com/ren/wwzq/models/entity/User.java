package com.ren.wwzq.models.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author: target
 * @date: 2020/5/8 13:35
 * @description:
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -4134717129081676807L;

    @Id
    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty("小程序用户唯一标识")
    private String openid;

    @ApiModelProperty(value = "微信用户id")
    private String unionId;

    @ApiModelProperty("用户头像")
    private String avatarUrl;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("国籍")
    private String country;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("语言")
    private String language;

    @ApiModelProperty("省份")
    private String province;

}
