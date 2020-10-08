package com.ren.wwzq.common.utils;

import com.ren.wwzq.common.BizException;
import lombok.extern.slf4j.Slf4j;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Map;

/**
 * @description
 * @author willing
 * @date 2020/10/8
 */
@Slf4j
public class JxlsUtil {


    private static String encodeFileName(String agent, String fileName)
            throws UnsupportedEncodingException {
        if (null != agent && (-1 != agent.toLowerCase().indexOf("msie") || agent.toLowerCase().contains("like gecko"))) {
            return URLEncoder.encode(fileName, "UTF8");
        } else if (null != agent && -1 != agent.toLowerCase().indexOf("mozilla")) {
            return "=?UTF-8?B?" + (new String(Base64.getEncoder().encode(fileName.getBytes("UTF-8")))) + "?=";
        } else {
            return fileName;
        }
    }

    public static void export(String templatePath, Map<String, Object> dataMap, String fileName,
                              HttpServletRequest request, HttpServletResponse response) {
        String agent = request.getHeader("USER-AGENT");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        try {
            response.setHeader("Content-Disposition", String.format("attachment;filename*=utf-8'zh_cn'%s",encodeFileName(agent, fileName).replaceAll("\\+", "%20")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Context context = new Context(dataMap);
        try (InputStream in = JxlsUtil.class.getClassLoader().getResourceAsStream(templatePath);
             ServletOutputStream outputStream = response.getOutputStream()) {
            // 将List<Exam>列表数据按照模板文件中的格式生成到模板文件中
            JxlsHelper.getInstance().processTemplate(in, outputStream, context);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] localExport(String templateFile, Map<String, Object> dataMap) {
        Context context = new Context(dataMap);
        try (OutputStream outputStream = new ByteArrayOutputStream();
             InputStream in = JxlsUtil.class.getClassLoader().getResourceAsStream(templateFile)) {
            // 将List<Exam>列表数据按照模板文件中的格式生成到模板文件中
            JxlsHelper.getInstance().processTemplate(in, outputStream, context);
            outputStream.flush();
            byte[] body = ((ByteArrayOutputStream) outputStream).toByteArray();
            return body;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
