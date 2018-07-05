package com.example.demo.business.user.web;

import com.example.demo.annotations.ResponseResult;
import com.example.demo.business.user.entity.UserInfo;
import com.example.demo.enums.ResultCode;
import com.example.demo.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@ResponseResult
@Api(tags = "用户管理")
@RestController
public class UserController {

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "测试用户是否通顺",notes = "测试")
    @PostMapping("user")
    public UserInfo user( UserInfo user){
        if(user.getId()==1){
            int a = 1/0;
        }else if(user.getId()==2){
//            System.out.println( redisTemplate.opsForValue().get("errors:" + (ResultCode.USERNAME_OR_PASSWORD_ERROR)));
//            ErrorsEntity errorsEntity = (ErrorsEntity) redisTemplate.opsForValue().get("errors:" + (ResultCode.USERNAME_OR_PASSWORD_ERROR));
            throw new BusinessException(ResultCode.DATA_ALREADY_EXISTED);
        }else{
            //TODO 模拟注册
            return user;
        }
    return null;

    }
}
