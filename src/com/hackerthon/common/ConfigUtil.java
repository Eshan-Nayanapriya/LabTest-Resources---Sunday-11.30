package com.hackerthon.common;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for loading configuration properties.
 */
public class ConfigUtil {

    // A Properties object to hold the configuration values loaded from the properties file.
    public static final Properties properties = new Properties();
	
    // Logger instance to log messages and errors.
    public static final Logger Log = Logger.getLogger(ConfigUtil.class.getName());

    // Static block to initialize the properties object when the class is loaded.
    static {
        try {
            // Load the properties file using the specified file path from CommonConstants.
            properties.load(QueryUtil.class.getResourceAsStream(CommonConstants.PROPERTY_FILE));
        } catch (IOException e) {
            // Log an error message if the properties file fails to load.
            Log.log(Level.SEVERE, e.getMessage());
        }
    }
}
