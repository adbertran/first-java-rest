package com.adb.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

public class FileLoader {
    private static File file;
    private static URL url;
    private static String json;

    public static String getJson (String path) {
        json = null;
        url = Thread.currentThread().getContextClassLoader().getResource(path);

        try {
            file = new File(url.toURI());
            json = new String(Files.readAllBytes(file.toPath()));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
