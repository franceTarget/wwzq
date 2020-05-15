package com.ren.wwzq.dao;

import com.ren.wwzq.models.entity.Dict;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: target
 * @date: 2020/5/9 10:17
 * @description:
 */
@Repository
public interface DictDao extends BaseDao<Dict> {

    List<Dict> queryByCategory(@Param("category") String category);

}
