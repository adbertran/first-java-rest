package com.adb;

import com.adb.dtos.Car;
import com.adb.dtos.Engine;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {
    public static void main (String[] args){
        get("/hello", Main::getHello);
        get("/car/details",Main::getCarDetails);
        post("/users/create", Main::postCreateUser);

    }

    private static Object getCarDetails(Request req, Response res) {
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

    private static String getHello(Request req, Response res) {
        if (req.queryParams("planet") == null) {
            res.status(HttpServletResponse.SC_BAD_REQUEST);
            return "Pusiste mal el parametro";
        }
        String planet=req.queryParams("planet");
        res.status(HttpServletResponse.SC_OK);
        return "Hello " + planet;
    }

    private static String postCreateUser(Request req, Response res) {
        String body =   req.body();
        res.header("x-authorize","True");
        res.header("Content-type","application/json");
        return "User level: " + req.headers("x-level");
    }


}
