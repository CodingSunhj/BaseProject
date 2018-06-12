package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: create by 孙海军
 * @date:2018/6/11 13:52
 */

@Data
@ConfigurationProperties(prefix = "threadPool")
public class ThreadPoolConfig {
    private int corePoolSize = 10;
    private int maximumPoolSize = 40;
    private long keepAliveTime = 2000;
    private int queueSize = 100;
}
