package com.adb.dtos;

public class SuccessMessageJson extends AbstractJson {
    private String success;

    public SuccessMessageJson() {
    }

    public SuccessMessageJson(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setErrorMessage(String success) {
        this.success = success;
    }

}
