package com.kockatoos.client.core.bkp;

import com.kockatoos.client.core.KockatoosListener;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class KockatoosRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

        registry.registerBeanDefinition("kockatoosApplicationContextInspector", BeanDefinitionBuilder.genericBeanDefinition(KockatoosListener.class).getBeanDefinition());
        //registry.registerBeanDefinition("kockatoosEndpoint",BeanDefinitionBuilder.genericBeanDefinition(KockatoosEndpoint.class).getBeanDefinition());


    }

}
