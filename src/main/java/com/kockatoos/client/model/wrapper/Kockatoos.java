package com.kockatoos.client.model.wrapper;

import com.client.model.application.Application;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Kockatoos {

    public Application application;
    public Resources resources;

}
