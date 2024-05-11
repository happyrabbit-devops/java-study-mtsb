package org.example.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
@Slf4j
public class ResourceLoader {

    private final ClassLoader classLoader;

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
            log.error(e.getMessage());
            return null;
        }

    }
}
