package com.ren.wwzq.controller;

import com.ren.wwzq.common.annotation.ApiMapping;
import com.ren.wwzq.common.annotation.IgnoreLoginCheck;
import com.ren.wwzq.models.request.test.TestUserReq;
import com.ren.wwzq.models.response.Response;
import com.ren.wwzq.models.response.test.TestUserResp;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description
 * @Author wings
 * @Date 2020/9/26
 */
@Slf4j
@Api(tags = "测试操作接口")
@ApiMapping
public class TestController {

    @IgnoreLoginCheck
    @PostMapping(value = "/test/transient")
    public Response<TestUserReq> testTransient(@RequestBody TestUserReq req) {
        log.info("请求入参:{}", req);
        TestUserResp resp = new TestUserResp();
        BeanUtils.copyProperties(req, resp);
        return Response.ok(resp);
    }
}
