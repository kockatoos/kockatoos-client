package com.kockatoos.client.core;

import org.springframework.core.env.Environment;

import java.util.Collections;
import java.util.Map;

public class KockatoosDefaultPropertyProvider extends KockatoosAbstractPropertyProvider {

    public KockatoosDefaultPropertyProvider(Environment environment) {
        super(environment);
    }

    @Override
    public Map<String, String> loadCustomProperties() {
        return Collections.emptyMap(); // No custom properties
    }
}
