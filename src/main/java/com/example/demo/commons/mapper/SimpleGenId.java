package com.example.demo.commons.mapper;

import tk.mybatis.mapper.genid.GenId;

/**
 * @desc 雪花ids生成算法
 * @author: create by 孙海军
 */
public class SimpleGenId implements GenId<Long> {
    @Override
    public synchronized Long genId(String table, String column) {
        IdWorker worker = new IdWorker(1,1,1);
        return worker.nextId();
    }
}
