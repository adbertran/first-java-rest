package com.adb.utils;

import com.adb.dtos.ErrorMessageJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class JsonResponseFactory {
    public static String createErrorResponse(Response res, int statusCode, Throwable cause) {
        return createJsonResponse(res, statusCode, new ErrorMessageJson(cause.getMessage(), stackTraceToString(cause)));
    }

    public static String createSuccessResponse(Response res) {
        Map<String, String> map = new HashMap<>();
        map.put("Success", "true");
        return createJsonResponse(res, HttpServletResponse.SC_OK, map);
    }

    public static String createJsonResponse(Response res, int statusCode, Object o) {
        try {
            res.header("Content-Type", "application/json");
            res.status(statusCode);
            return JsonFormatter.format(o);
        } catch (JsonProcessingException e) {
            res.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "description: \"JsonFormatter se pegó un palo: \"" + e.getMessage();
        }
    }

    private static String stackTraceToString(Throwable cause){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        cause.printStackTrace(pw);
        return sw.toString();
    }
}
