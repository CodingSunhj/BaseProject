package com.example.demo.redis;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public interface RedisTemplateService {
    /**
     * 删除键为key的内容
     * 
     * @param key
     */
    void delete(String key);
    
    /**
     * 将字符串内容存储到redis
     * 
     * @param key
     * @param value
     */
    void setStr(String key, String value);

    /**
     * 通过key获取字符串内容
     * 
     * @param key
     * @return 字符串内容
     */
    String getStrByKey(String key);

    /**
     * 存储一个对象
     * 
     * @param key
     * @param o
     */
    void setObj(String key, Object o);

    /**
     * 通过key获取
     * 
     * @param key
     * @return
     */
    <T> Object getObjByKey(String key);

    /**
     * 根据key获取list对象长度
     * 
     * @param key
     * @return
     */
    long sizeOfList(String key);

    /**
     * 根据key获取一组对象
     * 
     * @param key
     * @return
     */
    void setListByKey(String key, List<?> list);

    /**
     * 根据key获取一组对象
     * 
     * @param key
     * @return
     */
    List<?> getListByKey(String key);
    
    /**
     * 通过正则匹配keys
     * 
     * @param pattern
     * @return
     */
    Set<String> keySets(String pattern);

    
}
