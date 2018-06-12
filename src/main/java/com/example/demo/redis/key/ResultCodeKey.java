package com.example.demo.redis.key;

/**
 * @author: create by 孙海军
 * @date:2018/6/6 17:04
 */
public class ResultCodeKey extends BasePrefix {
    public ResultCodeKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static ResultCodeKey resultCode = new ResultCodeKey(0,"code");

}
