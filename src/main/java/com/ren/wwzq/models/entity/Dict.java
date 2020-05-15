package com.ren.wwzq.models.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

/**
 * @author: target
 * @date: 2020/5/14 17:02
 * @description:
 */
@Data
public class Dict {

    @Id
    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("父id")
    private String pId;

    @ApiModelProperty("类别")
    private String category;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("值")
    private String value;

    @ApiModelProperty("备注")
    private String remark;
}
