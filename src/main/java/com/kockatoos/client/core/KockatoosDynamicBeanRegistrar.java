package com.kockatoos.client.core;

import com.kockatoos.client.controller.KockatoosEndpoint;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class KockatoosDynamicBeanRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

        registry.registerBeanDefinition("kockatoosListener", BeanDefinitionBuilder.genericBeanDefinition(KockatoosListener.class).getBeanDefinition());

    }

}
