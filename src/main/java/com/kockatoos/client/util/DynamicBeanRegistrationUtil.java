package com.kockatoos.client.util;

import com.kockatoos.client.controller.KockatoosEndpoint;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

public class DynamicBeanRegistrationUtil {

     public static void registerRestEndPoint(GenericApplicationContext context){

        if (!context.containsBean("kockatoosEndpoint")) {
            context.registerBean("kockatoosEndpoint", KockatoosEndpoint.class);

            Map<String, RequestMappingHandlerMapping> mappings = context.getBeansOfType(RequestMappingHandlerMapping.class);

            if (!mappings.isEmpty()) {
                RequestMappingHandlerMapping requestMappingHandlerMapping = mappings.values().iterator().next();
                requestMappingHandlerMapping.afterPropertiesSet(); // Refresh Spring  request mappings
            }
        }

    }

    public static void registerBean(BeanDefinitionRegistry registry, String beanName, Class<?> clazz) {
        registry.registerBeanDefinition(beanName, BeanDefinitionBuilder.genericBeanDefinition(clazz).getBeanDefinition());
    }
}
