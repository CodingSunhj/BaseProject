package com.example.demo.commons.mapper;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.condition.SelectByConditionMapper;
import tk.mybatis.mapper.common.ids.DeleteByIdsMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

/**
 * @author: create by 孙海军
 */
@Repository
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T>,DeleteByIdsMapper<T>,SelectByIdsMapper<T>,SelectByConditionMapper<T> {
}

