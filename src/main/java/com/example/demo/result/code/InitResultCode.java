package com.example.demo.result.code;

import com.example.demo.business.resultcode.entity.CommonResultCode;
import com.example.demo.enums.ResultCode;
import com.example.demo.redis.key.ResultCodeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@Order(value = 1)
public class InitResultCode implements ApplicationRunner{

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
//        Class resultClass = ResultCode.class;
//        Field[] fields = resultClass.getDeclaredFields();
//        for(Field field:fields){
//            CommonResultCode redisResultCode = (CommonResultCode) redisTemplate.opsForValue().get(ResultCodeKey.resultCode.getPrefix()+field.getName());
//            if(redisResultCode==null){
//                continue;
//            }
//            ResultCode resultCode = (ResultCode) field.get(resultClass);
//            resultCode.setMessage(redisResultCode.getMsg());
//        }
    }


}
