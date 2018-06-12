package com.example.demo.commons.service;

/**
 * @author: create by 孙海军
 * @date:2018/6/11 20:20
 */

/**
 * 统一插入service
 * @param <E>
 * @param <PK>
 */
public interface InsertService<E,PK> {
    /**
     *
     * @param record 添加的数据
     * @return 生成的主键
     */
    PK insert(E record);

}
