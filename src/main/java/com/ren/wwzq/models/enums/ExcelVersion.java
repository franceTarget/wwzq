package com.ren.wwzq.models.enums;

/**
 * @Description
 * @Author wings
 * @Date 2020/9/26
 */
public enum ExcelVersion {

    XLS(2003,"xls"),
    XLSX(2007,"xlsx");

    private Integer value;
    private String name;

    ExcelVersion(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
