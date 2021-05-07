package com.adb.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarJsonV0Test {
    @Test
    public void testCreateCar() {
        CarV0 carV0 = new CarV0();
        carV0.setBrand("ford");

        assertEquals("ford", carV0.getBrand());
    }

}