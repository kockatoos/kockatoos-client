package com.kockatoos.client.util;

import org.springframework.core.env.Environment;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PropertyUtil {

    /**
     * Returns default properties as an immutable map.
     */
    private static Map<String, String> getDefaultProperties() {

        Map<String, String> defaultProperties = new HashMap<>();

        // REST Endpoint Properties
        defaultProperties.put("kockatoos.rest.endpoint.expose", "false");

        // Application Properties
        defaultProperties.put("kockatoos.application.name", "undefined-name");
        defaultProperties.put("kockatoos.application.port", "8080"); // Default port
        defaultProperties.put("kockatoos.application.context-path", "/");

        // Host Machine Properties
        defaultProperties.put("kockatoos.application.host", "undefined-name");
        defaultProperties.put("kockatoos.application.host.environment", "default");

        // Backend Server Properties
        defaultProperties.put("kockatoos.backend.server.expose", "false");
        defaultProperties.put("kockatoos.backend.server.url", "");
        defaultProperties.put("kockatoos.backend.server.secret", "");

        return Collections.unmodifiableMap(defaultProperties);
    }

    public static Map<String, String> getFinalProperties(Environment environment) {

        Map<String, String> finalProperties = new HashMap<>(getDefaultProperties());

        // Fetch environment variables if present
        overrideIfPresent(finalProperties, environment, "kockatoos.rest.endpoint.expose");
        overrideIfPresent(finalProperties, environment, "kockatoos.application.name", "spring.application.name");
        overrideIfPresent(finalProperties, environment, "kockatoos.application.port", "server.port");
        overrideIfPresent(finalProperties, environment, "kockatoos.application.context-path", "server.servlet.context-path");
        overrideIfPresent(finalProperties, environment, "kockatoos.application.host");
        overrideIfPresent(finalProperties, environment, "kockatoos.application.host.environment");

        // Backend Server Configuration
        if ("true".equalsIgnoreCase(environment.getProperty("kockatoos.backend.server.expose"))) {
            finalProperties.put("kockatoos.backend.server.expose", "true");
            overrideIfPresent(finalProperties, environment, "kockatoos.backend.server.url");
            overrideIfPresent(finalProperties, environment, "kockatoos.backend.server.secret");
        }

        return finalProperties;
    }

    private static void overrideIfPresent(Map<String, String> properties, Environment environment, String key) {
        String value = environment.getProperty(key);
        if (Objects.nonNull(value) && !value.trim().isEmpty()) {
            properties.put(key, value);
        }
    }

    private static void overrideIfPresent(Map<String, String> properties, Environment environment, String primaryKey, String fallbackKey) {
        String value = environment.getProperty(primaryKey, environment.getProperty(fallbackKey));
        if (Objects.nonNull(value) && !value.trim().isEmpty()) {
            properties.put(primaryKey, value);
        }
    }
}
