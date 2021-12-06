package com.epam.training.ticketservice.core.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {
    private static AppConfig instance = null;

    private Properties properties;

    private AppConfig() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "config.properties";

        properties = new Properties();

        try (var stream = new FileInputStream(appConfigPath)) {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }

        return instance;
    }

    public Properties getProperties() {
        return properties;
    }

}
