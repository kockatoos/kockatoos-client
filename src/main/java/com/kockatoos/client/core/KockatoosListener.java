package com.kockatoos.client.core;

import com.kockatoos.client.model.KockatoosConfig;
import com.kockatoos.client.model.KockatoosWrapper;
import com.kockatoos.client.model.Output;
import com.kockatoos.client.util.DynamicBeanRegistrationUtil;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.Map;
public class KockatoosListener implements ApplicationListener<ContextRefreshedEvent> {

    Output output = new Output();

    public KockatoosListener(){
        System.out.println("called....");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //Load YAML file
        KockatoosWrapper wrapper = YamlConfigLoader.loadConfig("kockatoos.yaml", KockatoosWrapper.class);
        KockatoosConfig config = wrapper.getKockatoos();
        System.out.print("$$$" + config);





        ApplicationContext applicationContext = event.getApplicationContext();

        GenericApplicationContext context = (GenericApplicationContext) applicationContext;
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) (context);

        KockatoosPropertyProvider propertyProvider = applicationContext.getBean(KockatoosAbstractPropertyProvider.class);

        if (propertyProvider.getProperty("kockatoos.rest.endpoint.expose").equals("true")) {
            System.out.println("adding rest bean");
            DynamicBeanRegistrationUtil.registerRestEndPoint(context); //register rest controller to expose data on "/kokatoos URL"
        }

        // Check if any bean of type KockatoosAbstractPropertyProvider already exists. if there is none then register default.
        Map<String, KockatoosAbstractPropertyProvider> existingBeans = applicationContext.getBeansOfType(KockatoosAbstractPropertyProvider.class);

        if (existingBeans.isEmpty()) {
            DynamicBeanRegistrationUtil.registerBean(registry, "kockatoosDefaultPropertyProvider", KockatoosAbstractPropertyProvider.class);
        }


        output.setName(propertyProvider.getProperty("kockatoos.application.name"));
        output.setPort(propertyProvider.getProperty("kockatoos.application.server.port"));
        output.setContextPath(propertyProvider.getProperty("kockatoos.application.context-path"));
        output.setHost(propertyProvider.getProperty("kockatoos.application.host"));
        output.setEnvironment(propertyProvider.getProperty("kockatoos.application.host.environment"));
        output.setTimestamp(new Date().toString());



    }
}
