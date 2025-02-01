package com.kockatoos.client.model.jms;

import java.util.List;

public class JmsBroker {
    private String brokerName;
    private List<JmsQueue> inbounds;
    private List<JmsQueue> outbounds;
}
