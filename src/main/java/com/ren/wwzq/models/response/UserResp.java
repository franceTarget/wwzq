package com.ren.wwzq.models.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: target
 * @date: 2020/5/8 17:16
 * @description:
 */
@Data
public class UserResp {

    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("用户token")
    private String token;

}
