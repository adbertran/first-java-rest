package com.adb.exceptions;

import javax.servlet.http.HttpServletResponse;

public class ApiException extends Exception {
    private Integer httpStatusCode;

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public ApiException(String message) {
        super(message);
        this.httpStatusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this.httpStatusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }

    public ApiException(Throwable cause) {
        super(cause);
        this.httpStatusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }

    public ApiException(String message, Integer httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

}
