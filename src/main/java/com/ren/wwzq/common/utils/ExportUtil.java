package com.ren.wwzq.common.utils;

import com.ren.wwzq.models.entity.Goods;
import com.ren.wwzq.models.enums.ExcelVersion;
import com.ren.wwzq.models.po.CellTitle;
import com.ren.wwzq.models.po.ExcelData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author willing
 * @Date 2020/10/11
 */
public class ExportUtil {

    public static void exportExcel(HttpServletResponse response, String fileName, ExcelData data, Integer version) {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (ExcelVersion.XLSX.getValue().equals(version)) {
            export2007(response, data);
        }
    }

    private static void export2007(HttpServletResponse response, ExcelData data) {
        XSSFWorkbook wb = new XSSFWorkbook();
        try (ServletOutputStream out = response.getOutputStream()) {
            String sheetName = data.getName();
            if (null == sheetName) {
                sheetName = "Sheet1";
            }
            XSSFSheet sheet = wb.createSheet(sheetName);
            writeExcel07(wb, sheet, data);
            wb.write(out);
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeExcel07(XSSFWorkbook wb, Sheet sheet, ExcelData data) {
        Map<String, XSSFCellStyle> stringXSSFCellStyleMap = writeTitlesToExcel07(wb, sheet, data.getTitles());
        writeRowsToExcel07(sheet, data, stringXSSFCellStyleMap);
        autoSizeColumns(sheet, data.getTitles().size() + 1);
    }

    private static void autoSizeColumns(Sheet sheet, int columnNumber) {

        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            sheet.autoSizeColumn(i, true);
            int newWidth = sheet.getColumnWidth(i) + 100;
            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }

    private static int writeRowsToExcel07(Sheet sheet, ExcelData data, Map<String, XSSFCellStyle> styleMap) {
        List<Goods> list = data.getData();

        int rowIndex = 1;
        for (Goods goods : list) {
            Row dataRow = sheet.createRow(rowIndex);
            //名称
            Cell cell0 = dataRow.createCell(0);
            cell0.setCellStyle(styleMap.get(null));
            if (StringUtil.isNotEmpty(goods.getName())) {
                cell0.setCellValue(goods.getName());
            } else {
                cell0.setCellValue("");
            }
            //价格
            Cell cell1 = dataRow.createCell(1);
            cell1.setCellStyle(styleMap.get("#,##0.00"));
            if (null != goods.getPrice()) {
                cell1.setCellValue(goods.getPrice().doubleValue());
            } else {
                cell1.setCellValue(0.00);
            }
            //描述
            Cell cell2 = dataRow.createCell(2);
            cell2.setCellStyle(styleMap.get(null));
            if (StringUtil.isNotEmpty(goods.getDescription())) {
                cell2.setCellValue(goods.getDescription());
            } else {
                cell2.setCellValue("");
            }
            //创建日期
            Cell cell3 = dataRow.createCell(3);
            cell3.setCellStyle(styleMap.get("yyyy-MM-dd"));
            if (null != goods.getCreateTime()) {
                cell3.setCellValue(goods.getCreateTime());
            } else {
                cell3.setCellValue("");
            }
            //创建时间
            Cell cell4 = dataRow.createCell(4);
            cell4.setCellStyle(styleMap.get("hh:mm:ss"));
            if (null != goods.getCreateTime()) {
                cell4.setCellValue(goods.getCreateTime());
            } else {
                cell4.setCellValue("");
            }
            rowIndex++;
        }
        return rowIndex;
    }

    private static Map<String, XSSFCellStyle> writeTitlesToExcel07(XSSFWorkbook wb, Sheet sheet, List<CellTitle> titles) {
        int rowIndex = 0;

        Map<String, XSSFCellStyle> stytleMap = createStytleMap(wb, titles);

        Row titleRow = sheet.createRow(rowIndex);
        int colIndex = 0;
        for (CellTitle cellTitle : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(cellTitle.getName());
            cell.setCellStyle(stytleMap.get(cellTitle.getFormat()));
            colIndex++;
        }
        return stytleMap;
    }

    private static Map<String, XSSFCellStyle> createStytleMap(XSSFWorkbook wb, List<CellTitle> titles) {
        Map<String, XSSFCellStyle> map = new HashMap<>(8);
        for (CellTitle cell : titles) {
            if (!map.containsKey(cell.getFormat())) {
                Font titleFont = wb.createFont();
                titleFont.setFontName("simsun");
                titleFont.setBold(true);
                titleFont.setColor(IndexedColors.BLACK.index);

                XSSFCellStyle titleStyle = wb.createCellStyle();
                titleStyle.setAlignment(HorizontalAlignment.CENTER);
                titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                titleStyle.setFillForegroundColor(IndexedColors.WHITE1.getIndex());
                titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                titleStyle.setFont(titleFont);
                setBorder07(titleStyle, BorderStyle.THIN, new XSSFColor(new java.awt.Color(0, 0, 0)));

                if (StringUtil.isNotEmpty(cell.getFormat())) {
                    XSSFDataFormat dataFormat = wb.createDataFormat();
                    short format = dataFormat.getFormat(cell.getFormat());
                    titleStyle.setDataFormat(format);
                }

                map.put(cell.getFormat(), titleStyle);
            }

        }
        return map;
    }

    private static void setBorder07(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderBottom(border);
        style.setBorderColor(XSSFCellBorder.BorderSide.TOP, color);
        style.setBorderColor(XSSFCellBorder.BorderSide.LEFT, color);
        style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, color);
        style.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, color);
    }
}
