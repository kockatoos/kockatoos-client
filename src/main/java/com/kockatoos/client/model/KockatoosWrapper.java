package com.kockatoos.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KockatoosWrapper {
    @JsonProperty("kockatoos")
    private KockatoosConfig kockatoos;

    public KockatoosConfig getKockatoos() {
        return kockatoos;
    }

    public void setKockatoos(KockatoosConfig kockatoos) {
        this.kockatoos = kockatoos;
    }
}
