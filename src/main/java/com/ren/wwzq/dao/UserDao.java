package com.ren.wwzq.dao;

import com.ren.wwzq.common.datasource.BaseDao;
import com.ren.wwzq.models.entity.User;
import com.ren.wwzq.models.request.UserReq;
import org.springframework.stereotype.Repository;

/**
 * @author: target
 * @date: 2020/5/9 10:17
 * @description:
 */
@Repository
public interface UserDao extends BaseDao<User> {

    User loginSelect(UserReq req);

}
