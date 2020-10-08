package com.ren.wwzq.common.utils;

import com.ren.wwzq.models.enums.ExcelVersion;
import com.ren.wwzq.models.po.CellTitle;
import com.ren.wwzq.models.po.ExcelData;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Description
 * @Author willing
 * @Date 2020/10/7
 */
public class ExportExcelUtil {
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
        } else {
            export2003(response, data);
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

    private static void export2003(HttpServletResponse response, ExcelData data) {
        HSSFWorkbook wb = new HSSFWorkbook();
        try (ServletOutputStream out = response.getOutputStream()) {
            String sheetName = data.getName();
            if (null == sheetName) {
                sheetName = "Sheet1";
            }
            HSSFSheet sheet = wb.createSheet(sheetName);
            writeExcel03(wb, sheet, data);
            wb.write(out);
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeExcel03(HSSFWorkbook wb, HSSFSheet sheet, ExcelData data) {
        int rowIndex = 0;
        rowIndex = writeTitlesToExcel03(wb, sheet, data.getTitles());
        writeRowsToExcel03(wb, sheet, data.getRows(), data.getTitles(), rowIndex);
        autoSizeColumns(sheet, data.getTitles().size() + 1);
        setSheetStyle03(wb, sheet, data);
    }

    private static void setSheetStyle03(HSSFWorkbook wb, HSSFSheet sheet, ExcelData data) {
        List<CellTitle> titles = data.getTitles();
        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        dataFont.setColor(IndexedColors.BLACK.index);
        for (int i = 0; i < titles.size(); i++) {
            HSSFCellStyle dataStyle = wb.createCellStyle();
            dataStyle.setAlignment(HorizontalAlignment.CENTER);
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            dataStyle.setFont(dataFont);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            dataStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            dataStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
            dataStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
            if (StringUtil.isNotEmpty(titles.get(i).getFormat())) {
                HSSFDataFormat hssfDataFormat = wb.createDataFormat();
                short format = hssfDataFormat.getFormat(titles.get(i).getFormat());
                dataStyle.setDataFormat(format);
            }
            sheet.setDefaultColumnStyle(i, dataStyle);
        }
    }

    private static int writeRowsToExcel03(HSSFWorkbook wb, HSSFSheet sheet, List<List<Object>> rows, List<CellTitle> titles, int rowIndex) {
        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            int colIndex = 0;

            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;
    }

    private static int writeTitlesToExcel03(HSSFWorkbook wb, HSSFSheet sheet, List<CellTitle> titles) {
        int rowIndex = 0;

        Row titleRow = sheet.createRow(rowIndex);
        int colIndex = 0;
        for (CellTitle cellTitle : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(cellTitle.getName());
            colIndex++;
        }
        rowIndex++;
        return rowIndex;
    }

    private static void setSheetStyle07(XSSFWorkbook wb, Sheet sheet, ExcelData data) {
        List<CellTitle> titles = data.getTitles();
        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        dataFont.setColor(IndexedColors.BLACK.index);
        for (int i = 0; i < titles.size(); i++) {
            XSSFCellStyle dataStyle = wb.createCellStyle();
            dataStyle.setAlignment(HorizontalAlignment.CENTER);
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            dataStyle.setFont(dataFont);
            setBorder07(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
            if (StringUtil.isNotEmpty(titles.get(i).getFormat())) {
                XSSFDataFormat xssfDataFormat = wb.createDataFormat();
                short format = xssfDataFormat.getFormat(titles.get(i).getFormat());
                dataStyle.setDataFormat(format);
            }
            sheet.setDefaultColumnStyle(i, dataStyle);
        }

    }


    private static void writeExcel07(XSSFWorkbook wb, Sheet sheet, ExcelData data) {
        int rowIndex = 0;
        rowIndex = writeTitlesToExcel07(wb, sheet, data.getTitles());
        writeRowsToExcel07(wb, sheet, data.getRows(), data.getTitles(), rowIndex);
        autoSizeColumns(sheet, data.getTitles().size() + 1);
//        setSheetStyle07(wb, sheet, data);
    }

    private static int writeTitlesToExcel07(XSSFWorkbook wb, Sheet sheet, List<CellTitle> titles) {
        int rowIndex = 0;

        Font titleFont = wb.createFont();
        titleFont.setFontName("simsun");
        titleFont.setBold(true);
        titleFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setFont(titleFont);
        setBorder07(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));

        Row titleRow = sheet.createRow(rowIndex);
        int colIndex = 0;
        for (CellTitle cellTitle : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(cellTitle.getName());
            cell.setCellStyle(titleStyle);
            colIndex++;
        }
        rowIndex++;
        return rowIndex;
    }

    private static int writeRowsToExcel07(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, List<CellTitle> titles, int rowIndex) {

        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        dataFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataStyle.setFont(dataFont);
        setBorder07(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            int colIndex = 0;

            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;
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
