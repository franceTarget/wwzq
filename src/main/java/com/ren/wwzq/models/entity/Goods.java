package com.ren.wwzq.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @Author willing
 * @Date 2020/10/7
 */
@Data
public class Goods {

    @Id
    private Integer id;

    private String name;

    private BigDecimal price;

    private String description;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
}
