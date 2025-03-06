package com.kockatoos.client.model.wrapper;

import com.client.model.http.Http;
import com.client.model.jms.Jms;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Resources {

    public List<Jms> jms;
    public Http http;

}