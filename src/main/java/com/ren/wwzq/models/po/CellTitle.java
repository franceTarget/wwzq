package com.ren.wwzq.models.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author willing
 * @Date 2020/10/7
 */
@Data
public class CellTitle {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "数据格式")
    private String format;

    public CellTitle(String name, String format) {
        this.name = name;
        this.format = format;
    }
}
