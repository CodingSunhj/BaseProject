package com.example.demo.commons.service;

/**
 * @author: create by 孙海军
 * @date:2018/6/11 20:23
 */

/**
 * 统一删除Service
 * @param <PK>
 */
public interface DeleteService<PK> {

    /**
     * 根据主键删除记录
     *
     * @param pk 主键
     * @return 影响记录数
     */
    int deleteByPk(PK pk);

    /**
     * 根据主键删除记录
     *
     * @param pks 主键集合
     * @return 影响记录数
     */
    int deleteByPks(Iterable<PK> pks);

}
