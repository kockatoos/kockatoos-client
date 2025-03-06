package com.kockatoos.client.model;

import com.client.model.application.Application;
import com.client.model.http.Http;
import com.client.model.jms.Jms;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Result {
    private Application application;
    private List<Jms> jms;
    private Http http;
}
