package com.ren.wwzq.models.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: target
 * @date: 2020/6/12 10:41
 * @description:
 */
@Data
public class UserInfo {

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "session")
    private String sessionKey;

    @ApiModelProperty(value = "微信应用id")
    private String openId;

    @ApiModelProperty(value = "微信用户id")
    private String unionId;
}
