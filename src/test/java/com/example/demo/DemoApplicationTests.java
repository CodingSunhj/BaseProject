package com.example.demo;

import com.example.demo.redis.key.ResultCodeKey;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Before
    public void insertError() {
//        redisTemplate.opsForValue().set(ResultCodeKey.resultCode.getPrefix()+":USERNAME_NOT_FOUND",new ErrorCode("10001","用户未找到"));
    }

    @Test
    public void getError() {
        System.out.println(redisTemplate.opsForValue().get(ResultCodeKey.resultCode.getPrefix()+":USERNAME_NOT_FOUND"));
    }


    public String getKey(String name){
        return "errors:" + name;
    }

//    @Test
//    public void initErrors() {
//        ResultCode resultCode = new ResultCode(3,"");
//        Class resultClass = resultCode.getClass();
//        Field[] fs = resultClass.getDeclaredFields();
//        try {
//            for (Field field : fs) {
//                Class fieldType = field.getType();
//                if (fieldType.equals(ErrorCode.class)) {
//                    System.out.println(fieldType.getName() + field.getName());
//                    ErrorCode resultCode = (ErrorCode) redisTemplate.opsForValue().get(getKey(field.getName()));
//                    field.set(resultCode,new ErrorCode(resultCode.getCode(), resultCode.getMsg()));
//                }
//
//            }
//            System.out.println("*********************"+ResultCode.USERNAME_NOT_FOUND.getMsg());
            //动态改变运行时msg内容
//            Field changeField = resultClass.getDeclaredField("USERNAME_NOT_FOUND");
//            ResultCode.USERNAME_NOT_FOUND.setMsg("dasdadadas");
//            changeField.set(resultCode,"DASDAD");
//            System.out.println("*********************"+ResultCode.USERNAME_NOT_FOUND.getMsg());
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }

}
