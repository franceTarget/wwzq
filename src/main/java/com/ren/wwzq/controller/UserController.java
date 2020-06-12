package com.ren.wwzq.controller;

import com.alibaba.fastjson.JSONObject;
import com.ren.wwzq.common.Constants;
import com.ren.wwzq.common.annotation.ApiMapping;
import com.ren.wwzq.common.annotation.IgnoreLoginCheck;
import com.ren.wwzq.models.entity.Dict;
import com.ren.wwzq.models.entity.User;
import com.ren.wwzq.models.po.UserInfo;
import com.ren.wwzq.models.request.UserInfoReq;
import com.ren.wwzq.models.request.UserReq;
import com.ren.wwzq.models.response.Response;
import com.ren.wwzq.models.response.UserResp;
import com.ren.wwzq.models.response.WecatLoginResp;
import com.ren.wwzq.service.DictService;
import com.ren.wwzq.service.UserService;
import com.ren.wwzq.web.LoginUserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: target
 * @date: 2020/5/8 15:36
 * @description:
 */
@Api(tags = "用户操作接口")
@ApiMapping
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DictService dictService;
    @Autowired
    private RestTemplate restTemplate;

    @IgnoreLoginCheck
    @ApiOperation(value = "登录", response = UserResp.class, notes = "")
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
    @GetMapping("/user/getUserByToken")
    public Response<UserInfo> getUserByToken(@ApiParam(value = "token") @RequestParam String token) {
        UserInfo userByToken = userService.getUserByToken(token);
        userByToken.setOpenId(null);
        userByToken.setSessionKey(null);
        userByToken.setUnionId(null);
        return Response.ok("查询成功", userByToken);
    }

    @IgnoreLoginCheck
    @ApiOperation(value = "小程序首次登陆", notes = "")
    @PostMapping(value = "/user/applet/first/login")
    public Response<String> appletLogin(@RequestBody UserInfoReq req) {
        List<Dict> dicts = dictService.queryByCategory(Constants.APPLET_CONFIG);
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.APPLET_LOGIN_URL)
                .append("?")
                .append(dicts.get(0).getName())
                .append("=")
                .append(dicts.get(0).getValue())
                .append("&")
                .append(dicts.get(1).getName())
                .append("=")
                .append(dicts.get(1).getValue())
                .append("&")
                .append("js_code")
                .append("=")
                .append(req.getCode())
                .append("&grant_type=authorization_code");
        String result = restTemplate.getForObject(sb.toString(), String.class);
        WecatLoginResp wecatLoginResp = JSONObject.parseObject(result, WecatLoginResp.class);
        req.setOpenid(wecatLoginResp.getOpenid());
        req.setSessionKey(wecatLoginResp.getSession_key());
        return Response.ok("登陆成功", userService.saveAppletUser(req));
    }

}
