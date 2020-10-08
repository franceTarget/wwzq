package com.ren.wwzq.controller;

import com.ren.wwzq.common.annotation.ApiMapping;
import com.ren.wwzq.common.annotation.IgnoreLoginCheck;
import com.ren.wwzq.common.utils.DateUtil;
import com.ren.wwzq.common.utils.ExportExcelUtil;
import com.ren.wwzq.common.utils.JxlsUtil;
import com.ren.wwzq.models.entity.Goods;
import com.ren.wwzq.models.enums.ExcelVersion;
import com.ren.wwzq.models.po.CellTitle;
import com.ren.wwzq.models.po.ExcelData;
import com.ren.wwzq.models.response.Response;
import com.ren.wwzq.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author wings
 * @Date 2020/10/2
 */
@Api(tags = "表格操作")
@ApiMapping
public class ExcelController {

    @Autowired
    private GoodsService goodsService;

    @IgnoreLoginCheck
    @ApiOperation(value = "商品导出", response = Boolean.class)
    @GetMapping("/goods/export/poi")
    public Response<Boolean> exportGoods(HttpServletResponse response) {
        ExcelData data = new ExcelData();
        data.setName("hello");
        List<CellTitle> titles = new ArrayList();
        CellTitle cellTitle1 = new CellTitle("名称",null);
        CellTitle cellTitle2 = new CellTitle("价格","¥#,##0");
        CellTitle cellTitle3 = new CellTitle("描述",null);
        CellTitle cellTitle4 = new CellTitle("创建日期","yyyy-MM-dd");
        CellTitle cellTitle5 = new CellTitle("创建时间","HH:mm:ss");
        titles.add(cellTitle1);
        titles.add(cellTitle2);
        titles.add(cellTitle3);
        titles.add(cellTitle4);
        titles.add(cellTitle5);
        data.setTitles(titles);

        List<Goods> list = goodsService.selectAll();
        List<List<Object>> rows = new ArrayList();
        for (Goods goods:list){
            List<Object> row = new ArrayList();
            row.add(goods.getName());
            row.add(goods.getPrice());
            row.add(goods.getDescription());
            row.add(DateUtil.formatDateToString(goods.getCreateTime(),DateUtil.STYLE_TWO));
            row.add(DateUtil.formatDateToString(goods.getCreateTime(),DateUtil.STYLE_THREE));
            rows.add(row);
        }
        data.setRows(rows);

        ExportExcelUtil.exportExcel(response, "hello.xlsx", data, ExcelVersion.XLSX.getValue());
        return Response.ok(true);
    }


    @IgnoreLoginCheck
    @ApiOperation(value = "商品导出", response = Boolean.class)
    @GetMapping("/goods/export/jxl")
    public Response<Boolean> exportGoods(HttpServletRequest request, HttpServletResponse response) {
        String templatePath = "template/goods.xlsx";
        String fileName = "商品清单" + System.currentTimeMillis() + ".xlsx";
        List<Goods> list = goodsService.selectAll();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("list", list);
        JxlsUtil.export(templatePath, dataMap, fileName, request, response);
        return Response.ok(true);
    }
}
