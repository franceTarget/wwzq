package com.ren.wwzq.controller;

import com.ren.wwzq.common.annotation.ApiMapping;
import com.ren.wwzq.common.annotation.IgnoreLoginCheck;
import com.ren.wwzq.common.datasource.DataSourceContext;
import com.ren.wwzq.models.entity.User;
import com.ren.wwzq.models.request.test.TestUserReq;
import com.ren.wwzq.models.response.Response;
import com.ren.wwzq.models.response.test.TestUserResp;
import com.ren.wwzq.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @Author wings
 * @Date 2020/9/26
 */
@Slf4j
@Api(tags = "测试操作接口")
@ApiMapping
public class TestController {

    @Autowired
    private UserService userService;

    @IgnoreLoginCheck
    @ApiOperation(value = "测试transient")
    @PostMapping(value = "/test/transient")
    public Response<TestUserReq> testTransient(@RequestBody TestUserReq req) {
        log.info("请求入参:{}", req);
        TestUserResp resp = new TestUserResp();
        BeanUtils.copyProperties(req, resp);
        return Response.ok(resp);
    }

    @IgnoreLoginCheck
    @ApiOperation(value = "多数据源切换")
    @GetMapping("/test/datasource")
    public Response<List<User>> getUserList() {
        List<User> list = new LinkedList<>();
        User user0 = userService.getById("1271486622250102784");
        list.add(user0);
        DataSourceContext.setRouterKey("slave1");
        User user1 = userService.getById("1271486622250102784");
        list.add(user1);
        DataSourceContext.setRouterKey("slave2");
        User user2 = userService.getById("1271486622250102784");
        list.add(user2);
        User user3 = userService.getById("1271486622250102784");
        DataSourceContext.clearRouterKey();
        User user4 = userService.getById("1271486622250102784");
        list.add(user3);
        list.add(user4);
        return Response.ok(list);
    }
}
