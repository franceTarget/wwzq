package com.ren.wwzq.service.impl;

import com.ren.wwzq.common.utils.EhcacheUtil;
import com.ren.wwzq.dao.UserDao;
import com.ren.wwzq.models.entity.User;
import com.ren.wwzq.models.request.UserInfoReq;
import com.ren.wwzq.models.request.UserReq;
import com.ren.wwzq.models.response.UserResp;
import com.ren.wwzq.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author: target
 * @date: 2020/5/8 13:42
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByToken(String token) {
        User user = (User) EhcacheUtil.get("data", token);
        return user;
    }

    @Override
    public User loginSelect(UserReq req) {
        return userDao.loginSelect(req);
    }

    @Override
    public UserResp login(User user) {
        //生成token返回
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        UserResp userResp = new UserResp();
        BeanUtils.copyProperties(user, userResp);
        userResp.setToken(uuid);
        EhcacheUtil.put("data", uuid, user);
        return userResp;
    }

    @Override
    public String saveAppletUser(UserInfoReq req) {
        return null;
    }
}
