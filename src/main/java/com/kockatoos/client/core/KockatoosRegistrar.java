package com.kockatoos.client.core;

import com.kockatoos.client.controller.KockatoosEndpoint;
import com.kockatoos.client.model.KockatoosConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class KockatoosRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

        registry.registerBeanDefinition("kockatoosApplicationContextInspector", BeanDefinitionBuilder.genericBeanDefinition(KockatoosApplicationContextInspector.class).getBeanDefinition());
        //registry.registerBeanDefinition("kockatoosEndpoint",BeanDefinitionBuilder.genericBeanDefinition(KockatoosEndpoint.class).getBeanDefinition());


    }

}
