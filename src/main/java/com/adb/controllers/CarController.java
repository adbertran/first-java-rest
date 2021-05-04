package com.adb.controllers;

import com.adb.clients.CarsRestClient;
import com.adb.dtos.Car;
import com.adb.dtos.CarV0;
import com.adb.dtos.Engine;
import com.adb.utils.JsonFormatter;
import com.adb.utils.JsonResponseFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

public class CarController {
    public static Object getCarDetails(Request req, Response res) {
        String color = req.queryParams("color");
        String brand = req.queryParams("brand");
        if (color == null) {
            res.status(HttpServletResponse.SC_BAD_REQUEST);
            return "Pusiste mal el color";
        }
        if (brand == null) {
            res.status(HttpServletResponse.SC_BAD_REQUEST);
            return "Pusiste mal la brand";
        }
        Engine engine = new Engine(123, 1000.5D);

        CarV0 carV0 = new CarV0();
        carV0.setColor(color);
        carV0.setBrand(brand);
        carV0.setEngine(engine);

        try {
            res.header("Content-type", "application/json");
            res.status(HttpServletResponse.SC_OK);
            return JsonFormatter.format(carV0);
        } catch (Exception e) {
            res.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Se rompio json: " + e.getMessage();
        }
    }

    public static Object getCarById(Request req, Response res) throws JsonProcessingException {
        Long carId = Long.valueOf(req.params(":car_id"));
        Car car;

        try {
            car = CarsRestClient.getCars(carId);
        } catch(Exception e) {
            return JsonResponseFactory.createErrorResponse(res, HttpServletResponse.SC_NOT_FOUND, String.format("Car %d not found", carId));

        }


        return JsonResponseFactory.createJsonResponse(res, HttpServletResponse.SC_OK, car);

    }

}
