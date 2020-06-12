package com.ren.wwzq.models.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: target
 * @date: 2020/5/14 18:11
 * @description:
 */
@Data
public class WecatLoginResp {

    @ApiModelProperty("用户唯一标识")
    private String openid;

    @ApiModelProperty("会话密钥")
    private String session_key;

    @ApiModelProperty("错误码")
    private Integer errcode;

    @ApiModelProperty("错误信息")
    private String errmsg;

}
