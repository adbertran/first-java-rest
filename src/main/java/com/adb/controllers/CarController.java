package com.adb.controllers;

import com.adb.clients.CarsRestClient;
import com.adb.dtos.CarJson;
import com.adb.dtos.CarV0;
import com.adb.dtos.Engine;
import com.adb.exceptions.ApiException;
import com.adb.utils.JsonFormatter;
import com.adb.utils.JsonResponseFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
        CarJson carJson;

        try {
            Long carId = Long.valueOf(req.params(":car_id"));
            carJson = CarsRestClient.getCars(carId);
        } catch(Exception e) {
            return JsonResponseFactory.createErrorResponse(res, HttpServletResponse.SC_NOT_FOUND, e);
            //return JsonResponseFactory.createErrorResponse(res, HttpServletResponse.SC_NOT_FOUND, String.format("Car %d not found", carId));
        }


        return JsonResponseFactory.createJsonResponse(res, HttpServletResponse.SC_OK, carJson);

    }

    public static Object getCarByIdV2(Request req, Response res) throws ApiException {
        CarJson carJson;

        try {
            Integer carId = Integer.valueOf(req.params(":car_id"));
            carJson = CarsRestClient.getCarsV2(carId);
        } catch(NumberFormatException e) {
            throw new ApiException(String.format("El parametro carId [%s] es incorrecto", req.params(":car_id")), HttpServletResponse.SC_BAD_REQUEST);
        }


        return JsonResponseFactory.createJsonResponse(res, HttpServletResponse.SC_OK, carJson);

    }

    public static Object deleteCarById(Request req, Response res) throws ApiException {
        try {
            Long carId = Long.valueOf(req.params(":car_id"));
            CarsRestClient.deleteCar(carId);
        } catch(NumberFormatException e) {
            throw new ApiException(String.format("El parametro carId [%s] es incorrecto", req.params(":car_id")), HttpServletResponse.SC_BAD_REQUEST);
        }


        return JsonResponseFactory.createSuccessResponse(res);

    }

    public static Object createVW(Request req, Response res) throws JsonProcessingException, ApiException {
        CarJson carJson = JsonFormatter.parse(req.body(), CarJson.class);
        if (!carJson.getLicensePlate().startsWith("VW")) {
            throw new ApiException(String.format("Invalid license plate [%s]", carJson.getLicensePlate()), HttpServletResponse.SC_BAD_REQUEST);
        }
        carJson.setBrand("VolksWagen");
        CarsRestClient.createVW(carJson);
        return JsonResponseFactory.createSuccessResponse(res);
    }

    public static Object updateLicensePlate(Request req, Response res) throws ApiException {
        Integer carId = Integer.parseInt(req.params(":car_id"));
        Map<String, String> mapJson = null;
        try {
            mapJson = JsonFormatter.parse(req.body(), Map.class);
            validateLicensePlate(mapJson);
        } catch (JsonProcessingException e) {
            throw new ApiException("Error parsing License Plate.", HttpServletResponse.SC_BAD_REQUEST);
        }
        CarJson carJson = CarsRestClient.getCarsV2(carId);
        carJson.setLicensePlate(mapJson.get("licensePlate"));
        CarsRestClient.putCar(carJson);
        return JsonResponseFactory.createSuccessResponse(res);
    }

    private static void validateLicensePlate(Map<String, String> map) throws ApiException {
        if (map == null || !map.containsKey("licensePlate") || map.get("licensePlate") == null ){
            throw new ApiException("Missing License Plate.", HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
