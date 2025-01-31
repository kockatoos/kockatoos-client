package com.kockatoos.client;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.beans.beancontext.BeanContext;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Component
public class KockatoosApplicationContextInspector implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // Get the application context
        var applicationContext = event.getApplicationContext();

        String[] beanNames = applicationContext.getBeanDefinitionNames();

        // Iterate through all beans
        for (String beanName : beanNames) {

            Object bean = applicationContext.getBean(beanName);

            // Check for classes implementing custom interfaces like JmsSender
            if (bean instanceof Object) {
                System.out.println("Found a JmsSender bean: " + beanName);
                // You can call methods on the bean to get queue names
            }

        }
    }

}

