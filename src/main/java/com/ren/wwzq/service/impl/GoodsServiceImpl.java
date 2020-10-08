package com.ren.wwzq.service.impl;

import com.ren.wwzq.dao.GoodsDao;
import com.ren.wwzq.models.entity.Goods;
import com.ren.wwzq.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author willing
 * @Date 2020/10/7
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<Goods> selectAll() {
        return goodsDao.selectAll();
    }
}
