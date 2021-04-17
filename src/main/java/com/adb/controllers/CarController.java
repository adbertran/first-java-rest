package com.adb.controllers;

import com.adb.dtos.Car;
import com.adb.dtos.Engine;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

public class CarController {
    public static Object getCarDetails(Request req, Response res) {
        String color = req.queryParams("color");
        String brand = req.queryParams("brand");
        if (color == null || brand == null) {
            res.status(HttpServletResponse.SC_BAD_REQUEST);
            return "Pusiste mal los parametros";
        }
        Engine engine = new Engine(123,1000.5D);

        Car car = new Car ();
        car.setColor(color);
        car.setBrand(brand);
        car.setEngine(engine);

        ObjectMapper mapper= new ObjectMapper();

        String json = null;
        try {
            json = mapper.writeValueAsString(car);
        } catch (JsonProcessingException e) {
            res.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Se rompio json: " + e.getMessage();
        }
        res.header("Content-type","application/json");
        res.status(HttpServletResponse.SC_OK);
        return json;
    }
}
