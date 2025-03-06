package com.kockatoos.client.model.jms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Queue {
    private String name;
    private String jndi;
}