package com.example.demo.manager;

import com.example.demo.config.ThreadPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author: create by 孙海军
 * @date:2018/6/11 13:39
 */
@Configuration
@EnableConfigurationProperties(ThreadPoolConfig.class)
public class ThreadPoolFactory {

    @Autowired
    ThreadPoolConfig threadPoolConfig;

    @Bean("threadPoolExecutor")
    public ThreadPoolExecutor getThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                //核心线程数量
                threadPoolConfig.getCorePoolSize(),
                //最大线程数量
                threadPoolConfig.getMaximumPoolSize(),
                //当线程空闲时，保持活跃的时间
                threadPoolConfig.getKeepAliveTime(),
                //时间单元 ，毫秒级
                TimeUnit.MILLISECONDS,
                //线程任务队列
                new LinkedBlockingQueue<>(threadPoolConfig.getQueueSize()),
                //创建线程的工厂
                Executors.defaultThreadFactory());
        return threadPoolExecutor;
    }
}