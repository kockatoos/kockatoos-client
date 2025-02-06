package com.kockatoos.client.controller;

import com.kockatoos.client.model.Output;
import com.kockatoos.client.util.DataUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KockatoosEndpoint {

    @GetMapping("/kockatoos")
    public Output expose(){
        return DataUtil.data;
    }

}

