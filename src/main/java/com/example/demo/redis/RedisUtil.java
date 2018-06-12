package com.example.demo.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

@Component
@Service("redisTemplateService")
public final class RedisUtil implements RedisTemplateService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void setStr(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String getStrByKey(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setObj(String key, Object o) {
        redisTemplate.opsForValue().set(key, o);
    }

    @Override
    public <T> Object getObjByKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    
    @Override
    public long sizeOfList(String key) {
        return redisTemplate.opsForList().size(key);
    }
    
    @Override
    public void setListByKey(String key, List<?> list) {
        redisTemplate.opsForList().leftPushAll(key, list);
    }
    
    @Override
    public List<?> getListByKey(String key) {
        List<?> listResult = new ArrayList<>();
        long size = sizeOfList(key);
        listResult = redisTemplate.opsForList().range(key, 0, size);
        return listResult;
    }

    @Override
    public Set<String> keySets(String pattern) {
        return  redisTemplate.keys(pattern);
    }

    private String buildKey(ServletWebRequest request,String name) {
        return "error:"+name;
    }



}
