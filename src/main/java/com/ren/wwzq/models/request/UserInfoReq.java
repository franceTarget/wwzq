package com.ren.wwzq.models.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: target
 * @date: 2020/5/14 17:51
 * @description:
 */
@Data
public class UserInfoReq {

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
    @ApiModelProperty("登陆code")
    private String code;

}
