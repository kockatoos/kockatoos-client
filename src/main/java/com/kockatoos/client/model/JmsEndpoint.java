package com.kockatoos.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JmsEndpoint {

    private String queueJndi;
    private String queueName;
    private String brokerType;

}