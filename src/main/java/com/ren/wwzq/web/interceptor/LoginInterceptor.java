package com.ren.wwzq.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.ren.wwzq.AppStarter;
import com.ren.wwzq.common.annotation.IgnoreLoginCheck;
import com.ren.wwzq.models.po.UserInfo;
import com.ren.wwzq.service.UserService;
import com.ren.wwzq.web.LoginUserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * @author： ZouHaiBo
 * @description：登录拦截器
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)
            throws Exception {

        log.info("intercept request url:{}", request.getRequestURL());
        //忽略token
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            if (method.isAnnotationPresent(IgnoreLoginCheck.class)) {
                return true;
            }
        }

        //允许编辑token
        allowEditToken(request, response);
        if (request.getRequestURL().toString().contains("swagger")
                || request.getRequestURL().toString().contains("api-docs")) {
            return true;
        }

        /*a
         *1，拦截用户请求，如果没有token，请求失败
          2，验证token是否有效
          3，把用户放入本地线程变量
          4，刷新存储用户时间
         */
        // step.1 检查是否输入token
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            log.info("intercept request url:{}, token:{}", request.getRequestURL(), "null");
            response.setStatus(401);
            return false;
        }

        // step.2 检查token是否有效和是否过期
        UserInfo user = AppStarter.getBean(UserService.class).getUserByToken(token);
        if (user == null) {
            response.setStatus(401);
            log.info("intercept request url:{}, userByToken:{}", request.getRequestURL(), user == null ? "NULL" : JSON.toJSONString(user));
            return false;
        }
        //绑定用户到本地线程变量
        LoginUserHolder.bind(user);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView)
            throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex)
            throws Exception {
        /*
            释放本地线程变量上的用户
         */
        LoginUserHolder.unbind();
    }

    /**
     * 允许浏览器设置header
     */
    private void allowEditToken(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 加入支持自定义的header 加入元素 token 前端就可以发送自定义token过来
        response.setHeader("Access-Control-Allow-Headers", "Content-gi,token");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        response.setHeader("X-Powered-By", " 3.2.1");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        String options = "OPTIONS";
        if (options.equals(request.getMethod())) {
            response.setStatus(200);
        }
    }

}
