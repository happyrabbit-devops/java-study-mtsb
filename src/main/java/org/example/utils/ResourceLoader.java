package org.example.utils;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class ResourceLoader {

    private final ClassLoader classLoader;

    static Logger logger = Logger.getLogger(ResourceLoader.class.getName());

    public ResourceLoader() {
        this.classLoader = getClass().getClassLoader();
    }

    public String getFilePath(String relativePath) {

        URL resourcesURL = classLoader.getResource(relativePath);
        if (resourcesURL == null) {
            throw new IllegalStateException(relativePath + " не найден");
        }

        try {
            return Paths.get(resourcesURL.toURI()).toString();
        } catch (URISyntaxException e) {
            logger.warning(e.getMessage());
            return null;
        }

    }
}
