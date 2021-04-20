package com.adb.config;

import com.adb.controllers.CarController;
import com.adb.controllers.CreateUserController;
import com.adb.controllers.HelloController;
import com.adb.controllers.PaymentController;
import spark.Spark;
import spark.servlet.SparkApplication;

import static spark.Spark.get;
import static spark.Spark.post;

public class Router implements SparkApplication {
    @Override
    public void init() {
        get("/hello", HelloController::getHello);
        get("/car/details", CarController::getCarDetails);
        post("/users/create", CreateUserController::postCreateUser);
        Spark.notFound((req, res) -> {res.type("application/json");
            return "{\"message\":\"Invalid URL.\"}";});
        post("/payment/processing", PaymentController::processPayment);
    }
}
