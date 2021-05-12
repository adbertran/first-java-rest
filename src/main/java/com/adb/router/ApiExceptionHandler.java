package com.adb.router;

import com.adb.exceptions.ApiException;
import com.adb.utils.JsonResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class ApiExceptionHandler<T extends Exception> implements ExceptionHandler<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @Override
    public void handle(T e, Request request, Response response) {
        ApiException apiException = e instanceof ApiException ? (ApiException) e : new ApiException("Unchecked Exception.", e);

        LOGGER.error(apiException.getMessage(), apiException);

        response.body(JsonResponseFactory.createErrorResponse(response, apiException.getHttpStatusCode(), apiException));

    }

}
