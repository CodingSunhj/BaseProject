package com.example.demo.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHelper implements ApplicationContextAware {

    private static ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (null == ac) {
            ac = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return ac;
    }

    public static <T> T getBean(Class<T> claz) {
        return ac.getBean(claz);
    }

    public static Object getBean(String beanName) {
        return ac.getBean(beanName);
    }
}
