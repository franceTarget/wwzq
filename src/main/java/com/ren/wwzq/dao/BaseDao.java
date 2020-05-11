package com.ren.wwzq.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> extends Mapper<T>, MySqlMapper<T> {

    /**
     * <p>根据map中的查询条件，查询对象列表</p>
     * <p>注意：需要在对应的映射文件里编写id为“getByMapForPage”的SQL语句</p>
     * @param paraMap   查询条件
     */
    public List<T> getByMap(Map<String, Object> paraMap);
    
    /**
     * <p>根据map中的查询条件,查询map对象列表</p>
     * <p>注意：需要在对应的映射文件里编写id为“getMap”的SQL语句</p>
     * @param paraMap   查询条件
     * @return map对象列表
     */
    public List<Map<String, Object>> getMapByMap(Map<String, Object> paraMap);
    
}