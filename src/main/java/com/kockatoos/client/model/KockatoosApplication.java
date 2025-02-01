package com.kockatoos.client.model;

import com.kockatoos.client.model.jms.JmsBroker;

import java.util.List;

public class KockatoosApplication {

    private String applicationName;
    private String applicationContextPath;
    private String applicationDeployedEnvironment;
    private String applicationDeployedServer;
    private List<JmsBroker> jmsBrokers;

}
