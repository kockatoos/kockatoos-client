package com.kockatoos.client.model.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Soap {
    private List<HttpEndpoint> inbound;
    private List<HttpEndpoint> outbound;

}