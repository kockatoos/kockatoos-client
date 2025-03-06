package com.kockatoos.client.model.jms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public  class Jms {

    private String id;
    private String name;
    private String brokerType;
    private List<Queue> inbound;
    private List<Queue> outbound;

}