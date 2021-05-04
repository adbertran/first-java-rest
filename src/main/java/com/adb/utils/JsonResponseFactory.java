package com.adb.utils;

import com.adb.dtos.ResponseDescription;
import com.fasterxml.jackson.core.JsonProcessingException;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

//todo todo estático
public class JsonResponseFactory {
    public static String createErrorResponse(Response res, int statusCode, String description) {
        return createJsonResponse(res, statusCode, new ResponseDescription(description));
    }

    public static String createSuccessResponse(Response res, String description) {
        return createJsonResponse(res, HttpServletResponse.SC_OK, new ResponseDescription(description));

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
}
