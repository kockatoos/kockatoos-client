package com.kockatoos.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Output {

    private String name;
    private String port;
    private String contextPath;
    private String host;
    private List<RestInBoundEndpoint> httpRestInBoundEndpoints;
    private List<String> httpRestOutBoundEndpoints;
    private List<JmsEndpoint> jmsInBoundEndpoints;
    private List<JmsEndpoint> jmsOutBoundEndpoints;
    private String environment;
    private String timestamp;

}
