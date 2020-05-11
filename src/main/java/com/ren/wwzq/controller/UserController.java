package com.ren.wwzq.controller;

import com.ren.wwzq.common.annotation.ApiMapping;
import com.ren.wwzq.common.annotation.IgnoreLoginCheck;
import com.ren.wwzq.models.entity.User;
import com.ren.wwzq.models.request.UserReq;
import com.ren.wwzq.models.response.Response;
import com.ren.wwzq.models.response.UserResp;
import com.ren.wwzq.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: target
 * @date: 2020/5/8 15:36
 * @description:
 */
@Api(tags = "用户操作接口")
@ApiMapping
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @IgnoreLoginCheck
    @ApiOperation(value = "登录", notes = "")
    @PostMapping(value = "/user/login")
    public Response<UserResp> login(@Valid @RequestBody UserReq req) {
        //密码校验
        User user = userService.loginSelect(req);
        if (user == null) {
            return Response.failed("用户名或密码不正确");
        }
        return Response.ok("登陆成功", userService.login(user));
    }

    @IgnoreLoginCheck
    @ApiOperation(value = "根据token获取信息", notes = "")
    @PostMapping("/user/getUserByToken")
    public Response<User> getUserByToken(@ApiParam(value = "token") @RequestParam String token) {
        return Response.ok("查询成功", userService.getUserByToken(token));
    }

    @IgnoreLoginCheck
    @ApiOperation(value = "小程序登录", notes = "")
    @PostMapping(value = "/applet/login")
    public Response appletLogin(@Valid @RequestBody UserReq req) {

        return Response.ok("登陆成功", null);
    }

}
