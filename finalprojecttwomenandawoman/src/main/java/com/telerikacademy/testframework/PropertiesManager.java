package com.telerikacademy.testframework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static com.telerikacademy.testframework.Utils.LOGGER;

public class PropertiesManager {

    public enum PropertiesManagerEnum {

        INSTANCE;
        private static final String UI_MAP = "src/test/resources/mappings/ui_map.properties";
        private static final String CONFIG_PROPERTIES = "src/test/resources/config.properties";

        public Properties getConfigProperties() {
            return loadProperties(CONFIG_PROPERTIES);
        }

        public Properties getUiMappings() {
            return loadProperties(UI_MAP);
        }

        private static Properties loadProperties(String url) {
            Properties properties = new Properties();

            try {
                properties.load(Files.newInputStream(Paths.get(url)));
            } catch (IOException ex) {
                LOGGER.error("Loading properties failed!");
            }

            return properties;
        }
    }
}
