package com.kockatoos.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestInBoundEndpoint {

    private String method;
    private String url;
    private String handlerMethod;

}