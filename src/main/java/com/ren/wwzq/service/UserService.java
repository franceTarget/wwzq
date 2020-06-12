package com.ren.wwzq.service;

import com.ren.wwzq.models.entity.User;
import com.ren.wwzq.models.request.UserInfoReq;
import com.ren.wwzq.models.request.UserReq;
import com.ren.wwzq.models.response.UserResp;

/**
 * @author: target
 * @date: 2020/5/8 13:43
 * @description:
 */
public interface UserService {

    User getUserByToken(String token);

    User loginSelect(UserReq req);

    UserResp login(User user);

    String saveAppletUser(UserInfoReq req);
}
