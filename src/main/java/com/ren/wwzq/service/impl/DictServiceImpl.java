package com.ren.wwzq.service.impl;

import com.ren.wwzq.common.Constants;
import com.ren.wwzq.common.utils.EhcacheUtil;
import com.ren.wwzq.dao.DictDao;
import com.ren.wwzq.models.entity.Dict;
import com.ren.wwzq.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: target
 * @date: 2020/5/14 17:07
 * @description:
 */
@Slf4j
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDao dictDao;

    @CachePut(value = "data", key = "'key_'+#appletConfig")
    @Override
    public List<Dict> queryByCategory(String appletConfig) {
        Object data = EhcacheUtil.get("data", Constants.APPLET_CONFIG_EN);
        if (null != data) {
            return (List<Dict>) data;
        }
        List<Dict> list = dictDao.queryByCategory(appletConfig);
        EhcacheUtil.put("data", Constants.APPLET_CONFIG_EN, list);
        log.info("applet config query{}", list);
        return list;
    }
}
