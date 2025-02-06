package com.kockatoos.client.core;

import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

public abstract class KockatoosAbstractPropertyProvider implements KockatoosPropertyProvider {

    private final Map<String, String> propertiesMap = new HashMap<>();

    public KockatoosAbstractPropertyProvider(Environment environment) {
        loadDefaultProperties(environment);
        mergeProperties(loadCustomProperties());
    }

    private void loadDefaultProperties(Environment environment) {
        propertiesMap.put("kockatoos.rest.endpoint.expose",environment.getProperty("kockatoos.rest.endpoint.expose","false"));
        propertiesMap.put("kockatoos.application.name", environment.getProperty("spring.application.name", "default-app"));
        propertiesMap.put("kockatoos.application.server.port", environment.getProperty("server.port", "8080"));
        propertiesMap.put("kockatoos.application.context-path", environment.getProperty("server.servlet.context-path", "/"));
        //propertiesMap.put("kockatoos.application.host", environment.getProperty("HOST", "localhost"));
        //propertiesMap.put("kockatoos.application.host.environment", environment.getProperty("HOST_ENV", "dev"));
    }

    @Override
    public String getProperty(String key) {
        return propertiesMap.getOrDefault(key, null);
    }

    private void mergeProperties(Map<String, String> props) {
        if (props != null) {
            propertiesMap.putAll(props); // Directly merge custom properties
        }
    }

    public abstract Map<String, String> loadCustomProperties();
}
