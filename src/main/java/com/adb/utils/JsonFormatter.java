package com.adb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFormatter {
    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
    }

    public static String format(Object o) throws JsonProcessingException {
        return MAPPER.writeValueAsString(o);
    }

    public static <T> T parse(String json, Class<T> valueType) throws JsonProcessingException {
        return MAPPER.readValue(json, valueType);
    }
}
