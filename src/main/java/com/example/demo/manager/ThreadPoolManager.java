package com.example.demo.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.concurrent.*;

/**
 * @author: create by 孙海军
 * @date:2018/6/8 17:20
 */
@Component
public class ThreadPoolManager {

    @Autowired
    ThreadPoolExecutor threadPoolExecutor;

    public void execute(Runnable r){
        threadPoolExecutor.execute(r);
    }

    public <T>Future<T> submit(Callable<T> r){
        return threadPoolExecutor.submit(r);
    }



}
