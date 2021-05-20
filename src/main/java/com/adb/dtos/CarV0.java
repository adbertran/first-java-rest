package com.adb.dtos;

import java.util.Arrays;
import java.util.List;

public class CarV0 extends AbstractJson {
    private String color;
    private String brand;
    private Engine engine;
    private List<String> parts= Arrays.asList("rueda","puerta","parabrisas");

    public List<String> getParts() {
        return parts;
    }

    public void setParts(List<String> parts) {
        this.parts = parts;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
