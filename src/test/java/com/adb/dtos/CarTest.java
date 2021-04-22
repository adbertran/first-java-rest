package com.adb.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    @Test
    public void testCreateCar() {
        Car car = new Car();
        car.setBrand("ford");

        assertEquals("ford", car.getBrand());
    }

}