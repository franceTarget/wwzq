package com.ren.wwzq.service;

import com.ren.wwzq.models.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * @author: target
 * @date: 2020/5/14 17:07
 * @description:
 */
public interface DictService {

    List<Dict> queryByCategory(String appletConfig);


}
