package com.ren.wwzq.controller;

import com.ren.wwzq.common.annotation.ApiMapping;
import com.ren.wwzq.common.annotation.IgnoreLoginCheck;
import com.ren.wwzq.models.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * @author: target
 * @date: 2020/6/11 9:35
 * @description:
 */
@Api(tags = "文件操作接口")
@ApiMapping
public class FileController {

    @Value("${file.path.upload}")
    private String fileUploadPath;

    @ApiOperation(value = "文件上传", notes = "")
    @PostMapping("/file/Upload")
    public Response fileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Response.failed("文件为空");
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        File dest = new File(fileUploadPath + "/" + fileName);
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file.transferTo(dest);
            return Response.ok("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failed("上传失败");
        }
    }

    @IgnoreLoginCheck
    @ApiOperation(value = "图片在线浏览", notes = "")
    @GetMapping("/file/img")
    public Response fileUpload(@RequestParam("fileId") String fileId,
                               HttpServletResponse response) {
        String filename = "4.jpg";
        File file = new File(fileUploadPath + "/" + filename);
        if (file.exists()) {
            response.setContentType("image/jpeg");
            response.addHeader("Content-Length", "" + file.length());
            byte[] buffer = new byte[1024];
            //输出流
            OutputStream os = null;
            try (FileInputStream fis = new FileInputStream(file);
                 BufferedInputStream bis = new BufferedInputStream(fis);) {
                os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
