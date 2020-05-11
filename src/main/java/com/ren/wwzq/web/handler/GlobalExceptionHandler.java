package com.ren.wwzq.web.handler;


import com.ren.wwzq.models.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.message.AuthException;
import javax.validation.ValidationException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

/**
 * @author: target
 * @date: 2019/11/25 17:32
 * @description:
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public Response handleExeResultException(AuthException e) {
        log.error("{}", e);
        return Response.failed("您的账户没有该操作权限，请登录后重试。");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : errorList) {
            sb.append(fieldError.getDefaultMessage());
            sb.append(", ");
        }
        log.error("{}", e);
        return Response.failed(sb.substring(0, sb.length() - 2));
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public Response handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("{}", e);
        if (e.getMessage().contains("Duplicate entry")) {
            int start = e.getMessage().indexOf("'", 0);
            int end = e.getMessage().indexOf("'", start + 1);
            String msg = e.getMessage().substring(start, end - 2);
            return Response.failed(msg + "'重复，请检查后重试。");
        }
        return Response.failed("操作数据库失败");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public Response handleRuntimeException(ValidationException e) {
        log.error("{}", e);
        return Response.failed(e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseBody
    public Response handleUndeclaredThrowableException(UndeclaredThrowableException e) {
        log.error("{}", e);
        return Response.failed(e.getUndeclaredThrowable().getMessage());
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Response handleRuntimeException(RuntimeException e) {
        log.error("{}", e);
        if (e.getMessage().contains("java.utils.Date")) {
            return Response.failed("日期格式有误");
        }
        return Response.failed(e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Response handleIllegalMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("{}", e);
        return Response.failed(e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response handleException(Exception e) {
        log.error("{}", e);
        return Response.failed("操作失败");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Response handleNullException(Exception e) {
        log.error("{}", e);
        return Response.failed("数据异常：" + e.getMessage());
    }

}
