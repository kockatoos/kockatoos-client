package com.kockatoos.client.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;

public class YamlConfigLoader {
    public static <T> T loadConfig(String filePath, Class<T> configClass) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new RuntimeException("YAML file not found at: " + filePath);
            }
            return mapper.readValue(file, configClass);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load YAML configuration from " + filePath, e);
        }
    }
}

