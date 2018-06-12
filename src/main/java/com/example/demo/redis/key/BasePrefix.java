package com.example.demo.redis.key;

import lombok.Data;

/**
 * @author: create by 孙海军
 * @date:2018/6/6 16:59
 */

/**
 * Redis  Key的主键生成基类
 */
public abstract class BasePrefix implements KeyPrefix{
    private int expireSeconds;
    private String prefix;
    public BasePrefix(String prefix){
        this(0,prefix);
    }
    public BasePrefix(int expireSeconds,String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    public int expireSeconds(){
        return expireSeconds;
    }
    public String getPrefix(){
        String className  = getClass().getSimpleName();
        return (className+":"+prefix+":").toUpperCase();
    }
}
