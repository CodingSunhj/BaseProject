package com.example.demo.business.user.service.impl;

import com.example.demo.business.user.entity.UserInfo;
import com.example.demo.business.user.service.UserService;
import com.example.demo.commons.service.impl.BaseMySqlBaseCrudServiceImpl;
import com.example.demo.exception.BusinessException;
import com.example.demo.helper.SpringContextHelper;
import com.example.demo.manager.ThreadPoolManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @desc:
 * @author: create by SunHJ
 * @date:2018/7/6 13:36
 */
@Service
@Slf4j
public class UserServiceImpl extends BaseMySqlBaseCrudServiceImpl<UserInfo, Long> implements UserService {

    @Autowired
    ThreadPoolManager threadPoolManager;

    @Override
    @Transactional
    public UserInfo registerService(UserInfo userInfo) {
        String success = "";
        Future<String> future = threadPoolManager.submit(new RegisterCall("send"));
        System.out.println("多线程执行...");
        try {
            success = future.get();
        }catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        UserInfo userInfo1 = null;
        if(success.equals("success")){
            Long id = insert(userInfo);
            userInfo1 = selectByPk(id);
        }
        return userInfo1;
    }

}
class RegisterCall implements Callable<String>{
    public String request="";
    public RegisterCall(String request) {
            this.request = request;
    }

    @Override
    public String call() throws Exception {
        request=request+"success";
        throw new BusinessException("注册失败");
//        return request;
    }
}
