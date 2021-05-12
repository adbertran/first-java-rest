package com.adb.router;

import com.adb.controllers.CarController;
import com.adb.controllers.HelloController;
import com.adb.controllers.PaymentController;
import com.adb.controllers.UsersController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;
import spark.servlet.SparkApplication;

import static spark.Spark.*;

public class Router implements SparkApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(Router.class);

    @Override
    public void init() {
        LOGGER.info("Routers initialization starting, class name {} init routers quantity {}", Router.class.getCanonicalName(), 10);

        get("/hello", HelloController::getHello);
        get("/car/details", CarController::getCarDetails);
        get("/cars/:car_id", CarController::getCarByIdV2);
        delete("/cars/:car_id", CarController::deleteCarById);
        post("/cars/create/vw", CarController::createVW);
        put("/cars/:car_id", CarController::updateLicensePlate);

        get("/users/:user_id", UsersController::getUser);
        post("/users/create", UsersController::postCreateUser);
        delete("/users/delete", UsersController::deleteUser);

        Spark.notFound((req, res) -> {res.type("application/json");
            return "{\"message\":\"Invalid URL.\"}";});
        post("/payment/processing", PaymentController::processPayment);

        Spark.exception(Exception.class, new ApiExceptionHandler<>());

    }
}
