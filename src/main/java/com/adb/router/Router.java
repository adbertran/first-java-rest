package com.adb.router;

import com.adb.controllers.CarController;
import com.adb.controllers.HelloController;
import com.adb.controllers.PaymentController;
import com.adb.controllers.UsersController;
import spark.Spark;
import spark.servlet.SparkApplication;

import static spark.Spark.*;

public class Router implements SparkApplication {
    @Override
    public void init() {
        get("/hello", HelloController::getHello);
        get("/car/details", CarController::getCarDetails);
        get("/cars/:car_id", CarController::getCarById);
        get("/users/:user_id", UsersController::getUser);
        post("/users/create", UsersController::postCreateUser);
        delete("/users/delete", UsersController::deleteUser);
        Spark.notFound((req, res) -> {res.type("application/json");
            return "{\"message\":\"Invalid URL.\"}";});
        post("/payment/processing", PaymentController::processPayment);
    }
}
