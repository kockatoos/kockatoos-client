package com.kockatoos.client;

import com.kockatoos.client.annotations.InBoundQueue;
import com.kockatoos.client.annotations.InOutBoundQueue;
import com.kockatoos.client.annotations.OutBoundQueue;
import jakarta.jms.Queue;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class KockatoosApplicationContextInspector implements ApplicationListener<ContextRefreshedEvent> {

    private final List<String> inboundQueues = new ArrayList<>();
    private final List<String> outboundQueues = new ArrayList<>();
    private final List<String> inOutboundQueues = new ArrayList<>();
    private final List<String> httpInboundEndPoint = new ArrayList<>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        var applicationContext = event.getApplicationContext();

        // Get all beans of type Queue
        Map<String, Queue> queueBeans = applicationContext.getBeansOfType(Queue.class);

        for (Map.Entry<String, Queue> entry : queueBeans.entrySet()) {
            String beanName = entry.getKey();
            Queue queue = entry.getValue();

            // Check for annotations
            if (applicationContext.findAnnotationOnBean(beanName, InBoundQueue.class) != null) {
                inboundQueues.add(beanName);
            }
            if (applicationContext.findAnnotationOnBean(beanName, OutBoundQueue.class) != null) {
                outboundQueues.add(beanName);
            }
            if (applicationContext.findAnnotationOnBean(beanName, InOutBoundQueue.class) != null) {
                inOutboundQueues.add(beanName);
            }
        }

        printQueueDetails();
    }

    private void printQueueDetails() {
        System.out.println("Inbound Queues: " + inboundQueues);
        System.out.println("Outbound Queues: " + outboundQueues);
        System.out.println("In-Out Queues: " + inOutboundQueues);
    }


}
