package com.ren.wwzq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ren.wwzq.common.utils.AESUtil;
import com.ren.wwzq.common.utils.EhcacheUtil;
import com.ren.wwzq.common.utils.IDUtil;
import com.ren.wwzq.common.utils.SHA1Util;
import com.ren.wwzq.dao.UserDao;
import com.ren.wwzq.models.entity.User;
import com.ren.wwzq.models.po.AppletUser;
import com.ren.wwzq.models.po.UserInfo;
import com.ren.wwzq.models.request.UserInfoReq;
import com.ren.wwzq.models.request.UserReq;
import com.ren.wwzq.models.response.UserResp;
import com.ren.wwzq.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author: target
 * @date: 2020/5/8 13:42
 * @description:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserInfo getUserByToken(String token) {
        UserInfo user = (UserInfo) EhcacheUtil.get("data", token);
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
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo);
        EhcacheUtil.put("data", uuid, user);
        return userResp;
    }

    @Override
    public String saveAppletUser(UserInfoReq req) {
        //签名校验
        String signature = null;
        try {
            signature = SHA1Util.encode(req.getRawData() + req.getSessionKey());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!req.getSignature().equals(signature)) {
            throw new RuntimeException("签名校验失败");
        }
        //用AES秘钥解密实际的内容
        AppletUser appletUser = new AppletUser();
        try {
            String decryptAES = AESUtil.decryptAES(req.getSessionKey(), req.getIv(), req.getEncryptedData());
            appletUser = JSONObject.parseObject(decryptAES, AppletUser.class);
            log.info("解密后的数据{}", appletUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserInfo userInfo = new UserInfo();
        //通过openid查询用户是否存在
        User user = new User();
        user.setOpenid(req.getOpenid());
        User u = userDao.selectOne(user);
        if (null == u) {
            BeanUtils.copyProperties(req, user);
            user.setUnionId(appletUser.getUnionId());
            String id = IDUtil.nextStrId();
            user.setId(id);
            userDao.insertSelective(user);
            userInfo.setId(id);
        }else{
            userInfo.setId(u.getId());
        }
        userInfo.setOpenId(req.getOpenid());
        userInfo.setSessionKey(req.getSessionKey());
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        EhcacheUtil.put("data", uuid, userInfo);
        return uuid;
    }

    @Override
    public User getById(String id) {
        return userDao.selectByPrimaryKey(id);
    }
}
