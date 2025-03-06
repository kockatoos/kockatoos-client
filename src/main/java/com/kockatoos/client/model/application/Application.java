package com.kockatoos.client.model.application;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public  class Application {

    private String group;
    private String name;
    private String port;
    private String context;

}