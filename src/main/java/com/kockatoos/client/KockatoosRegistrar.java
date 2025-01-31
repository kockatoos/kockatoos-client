package com.kockatoos.client;

import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public class KockatoosRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

        var beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(KockatoosApplicationContextInspector.class)
                .getBeanDefinition();

        registry.registerBeanDefinition("kockatoosApplicationContextInspector", beanDefinition);
    }
}
