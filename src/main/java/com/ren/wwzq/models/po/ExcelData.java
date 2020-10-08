package com.ren.wwzq.models.po;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author wings
 * @Date 2020/10/7
 */
@Data
public class ExcelData implements Serializable {
    private static final long serialVersionUID = 6055538690237943579L;

    //表头
    private List<CellTitle> titles;

    //数据
    private List<List<Object>> rows;

    //页签名称
    private String name;
}
