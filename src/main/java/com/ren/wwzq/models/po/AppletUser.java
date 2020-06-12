package com.ren.wwzq.models.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: target
 * @date: 2020/6/12 15:50
 * @description:
 */
@Data
public class AppletUser {

    @ApiModelProperty("小程序用户唯一标识")
    private String openid;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("语言")
    private String language;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("省份")
    private String province;

    @ApiModelProperty("国籍")
    private String country;

    @ApiModelProperty("用户头像")
    private String avatarUrl;

    @ApiModelProperty(value = "应用唯一id")
    private String unionId;

    @ApiModelProperty(value = "水印隐秘信息")
    private Watermark watermark;

}
