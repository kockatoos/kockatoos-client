package com.kockatoos.client.model.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HttpEndpoint {
    private String url;
    private String method;
}