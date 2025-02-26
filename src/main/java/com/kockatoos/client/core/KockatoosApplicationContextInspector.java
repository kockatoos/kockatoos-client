package com.kockatoos.client.core;

import com.kockatoos.client.annotations.InBoundQueue;
import com.kockatoos.client.annotations.OutBoundQueue;
import com.kockatoos.client.model.*;
import com.kockatoos.client.util.DynamicBeanRegistrationUtil;
import com.kockatoos.client.util.DataUtil;
import jakarta.jms.JMSException;
import jakarta.jms.Queue;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.*;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class KockatoosApplicationContextInspector implements ApplicationListener<ContextRefreshedEvent> {

    Output output = new Output();
    private final List<JmsEndpoint> inboundQueues = new ArrayList<>();
    private final List<JmsEndpoint> outboundQueues = new ArrayList<>();
    private final List<RestInBoundEndpoint> httpInboundEndPoint = new ArrayList<>();

    public KockatoosApplicationContextInspector(){
        System.out.println("called....");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //Load YAML file
        KockatoosWrapper wrapper = YamlConfigLoader.loadConfig("kockatoos.yaml", KockatoosWrapper.class);
        KockatoosConfig config = wrapper.getKockatoos();
        System.out.print("$$$"+config);

        ApplicationContext applicationContext = event.getApplicationContext();

        GenericApplicationContext context = (GenericApplicationContext) applicationContext;
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) (context);

        KockatoosPropertyProvider propertyProvider = applicationContext.getBean(KockatoosAbstractPropertyProvider.class);

        if(propertyProvider.getProperty("kockatoos.rest.endpoint.expose").equals("true")){
            System.out.println("adding rest bean");
            DynamicBeanRegistrationUtil.registerRestEndPoint(context); //register rest controller to expose data on "/kokatoos URL"
        }

        // Check if any bean of type KockatoosAbstractPropertyProvider already exists. if there is none then register default.
        Map<String, KockatoosAbstractPropertyProvider> existingBeans = applicationContext.getBeansOfType(KockatoosAbstractPropertyProvider.class);

        if (existingBeans.isEmpty()) {
            DynamicBeanRegistrationUtil.registerBean(registry,"kockatoosDefaultPropertyProvider",KockatoosAbstractPropertyProvider.class);
        }


        output.setName(propertyProvider.getProperty("kockatoos.application.name"));
        output.setPort(propertyProvider.getProperty("kockatoos.application.server.port"));
        output.setContextPath(propertyProvider.getProperty("kockatoos.application.context-path"));
        output.setHost(propertyProvider.getProperty("kockatoos.application.host"));
        output.setEnvironment(propertyProvider.getProperty("kockatoos.application.host.environment"));
        output.setTimestamp(new Date().toString());


        // extract all the JMS endpoints
        Map<String, Queue> queueBeans = applicationContext.getBeansOfType(Queue.class);

        for (Map.Entry<String, Queue> entry : queueBeans.entrySet()) {

            String beanName = entry.getKey();
            Queue queue = entry.getValue();

            // Check for annotations
            if (applicationContext.findAnnotationOnBean(beanName, InBoundQueue.class) != null) {
                try {
                    inboundQueues.add(new JmsEndpoint(queue.getQueueName(),queue.getQueueName(),"Artemis"));
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
                output.setJmsInBoundEndpoints(inboundQueues);
            }
            if (applicationContext.findAnnotationOnBean(beanName, OutBoundQueue.class) != null) {
                try {
                    outboundQueues.add(new JmsEndpoint(queue.getQueueName(),queue.getQueueName(),"Artemis"));
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
                output.setJmsOutBoundEndpoints(outboundQueues);
            }

        }

        //extract REST endpoints
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RestController.class);

        for (Object bean : beans.values()) {
            Class<?> beanClass = bean.getClass();
            String baseUrl = "";

            // Check if class has @RequestMapping for base URL
            RequestMapping classRequestMapping = AnnotationUtils.findAnnotation(beanClass, RequestMapping.class);

            if (classRequestMapping != null && classRequestMapping.value().length > 0) {
                baseUrl = classRequestMapping.value()[0]; // Use first value if multiple
            }

            // Iterate through methods to find REST mappings
            for (Method method : beanClass.getDeclaredMethods()) {
                extractMapping(method, baseUrl);
            }
        }

        DataUtil.data = output;

    }


    private void extractMapping(Method method, String baseUrl) {

        if (method.isAnnotationPresent(GetMapping.class)) {
           httpInboundEndPoint.add(new RestInBoundEndpoint("GET",baseUrl + getFirstValue(method.getAnnotation(GetMapping.class).value()),method.getName()));
        }
        if (method.isAnnotationPresent(PostMapping.class)) {
           httpInboundEndPoint.add(new RestInBoundEndpoint("POST",baseUrl + getFirstValue(method.getAnnotation(GetMapping.class).value()),method.getName()));
        }
        if (method.isAnnotationPresent(PutMapping.class)) {
            httpInboundEndPoint.add(new RestInBoundEndpoint("PUT",baseUrl + getFirstValue(method.getAnnotation(GetMapping.class).value()),method.getName()));
        }
        if (method.isAnnotationPresent(DeleteMapping.class)) {
           httpInboundEndPoint.add(new RestInBoundEndpoint("DELETE",baseUrl + getFirstValue(method.getAnnotation(GetMapping.class).value()),method.getName()));
        }
        if (method.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            String methodType = requestMapping.method().length > 0 ? requestMapping.method()[0].name() : "ANY";
           httpInboundEndPoint.add(new RestInBoundEndpoint(methodType,baseUrl + getFirstValue(method.getAnnotation(GetMapping.class).value()),method.getName()));
        }
        output.setHttpRestInBoundEndpoints(httpInboundEndPoint);
    }

    private String getFirstValue(String[] values) {
        return (values.length > 0) ? values[0] : "";
    }

}
