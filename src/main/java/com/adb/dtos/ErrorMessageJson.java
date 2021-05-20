package com.adb.dtos;

public class ErrorMessageJson extends AbstractJson {
    private String errorMessage;
    private String stackTrace;

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public ErrorMessageJson() {
    }

    public ErrorMessageJson(String errorMessage, String stackTrace) {
        this.errorMessage = errorMessage;
        this.stackTrace = stackTrace;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
