package com.example.demo.redis.key;

/**
 * @author: create by 孙海军
 * @date:2018/6/6 16:56
 */
public interface KeyPrefix {
    /**
     * 过期时间
     * @return
     */
    public int expireSeconds();

    /**
     * RedisKey前缀
     * @return
     */
    public String getPrefix();
}
