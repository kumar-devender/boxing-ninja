package com.ninja.boxing.app.module.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author DEVENDER
 *Thread safe singleton to access property
 */
public enum PropertyConfig {
    INSTANCE;
    private Properties prop = new Properties();
    private PropertyConfig() {
        InputStream inputStream;
        final String propFileName = "application.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    }

    public String getPropertyValue(final String property) {
        return prop.getProperty(property);
    }
}
