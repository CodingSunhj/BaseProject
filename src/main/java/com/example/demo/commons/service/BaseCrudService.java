package com.example.demo.commons.service;

/**
 * @author: create by 孙海军
 * @date:2018/6/11 20:29
 */
public interface BaseCrudService<E,PK> extends DeleteService<PK>,InsertService<E,PK>,SelectService<E,PK>,UpdateService<E,PK>{

}
