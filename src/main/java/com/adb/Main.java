package com.adb;

import com.adb.config.Config;
import com.adb.controllers.CarController;
import com.adb.controllers.CreateUserController;
import com.adb.controllers.HelloController;
import com.adb.controllers.PaymentController;
import spark.Spark;

import static spark.Spark.*;


public class Main {
    public static void main (String[] args){
        Config.init();
        get("/hello", HelloController::getHello);
        get("/car/details", CarController::getCarDetails);
        post("/users/create", CreateUserController::postCreateUser);
        Spark.notFound((req, res) -> {res.type("application/json");
        return "{\"message\":\"Invalid URL.\"}";});
        post("/payment/processing", PaymentController::processPayment);
    }
}
