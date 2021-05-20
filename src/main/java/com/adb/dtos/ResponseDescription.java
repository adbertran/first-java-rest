package com.adb.dtos;

public class ResponseDescription extends AbstractJson {
    private String description;

    public ResponseDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
