package com.ren.wwzq.models.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: target
 * @date: 2020/6/12 9:42
 * @description:
 */
@Data
public class AppletUser {

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty("小程序用户唯一标识")
    private String openid;

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

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("省份")
    private String province;
}
