package com.kockatoos.client.model.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Http {

    private Rest rest;
    private Soap soap;

}