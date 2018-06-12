package com.example.demo.commons.service;

import com.example.demo.commons.model.qo.PageQO;
import com.example.demo.commons.model.vo.PageVO;

import java.util.List;

/**
 * @author: create by 孙海军
 * @date:2018/6/11 20:24
 */
public interface SelectService<E,PK> {

    /**
     * 根据主键查询
     * @param pk 主键
     * @return 查询结果,无结果时返回{@code null}
     */
    E selectByPk(PK pk);

    /**
     * 根据多个主键查询
     * @param pks 主键集合
     * @return 查询结果,如果无结果返回空集合
     */
    List<E> selectByPks(Iterable<PK> pks);

    /**
     * 查询所有结果
     * @return 所有结果,如果无结果则返回空集合
     */
    List<E> selectAll();

    /**
     * 查询所有结果
     * @return 获取分页结果
     */
    PageVO<E> selectPage(PageQO pageQO);

    /**
     * 带查询条件的分页查询
     * @param pageQO
     * @param condition
     * @return
     */
    PageVO<E> selectPage(PageQO pageQO,Object condition);
}
