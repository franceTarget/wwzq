package com.ren.wwzq.models.request;

import io.swagger.annotations.Api;
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

    @ApiModelProperty(value = "登陆code")
    private String code;

    @ApiModelProperty(value = "签名")
    private String signature;

    @ApiModelProperty(value = "加密向量")
    private String iv;

    @ApiModelProperty(value = "加密数据")
    private String encryptedData;

    @ApiModelProperty(value = "签名数据")
    private String rawData;

    @ApiModelProperty(value = "用户唯一标识", hidden = true)
    private String openid;

    @ApiModelProperty(value = "会话密钥", hidden = true)
    private String sessionKey;
}
