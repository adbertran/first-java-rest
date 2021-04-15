package com.adb;

import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class Main {
    public static void main (String[] args){
        get("/hello", Main::getHello);
        post("/users/create", Main::postCreateUser);
    }

    private static String getHello(Request req, Response res) {
        return "Hello World";
    }

    private static String postCreateUser(Request req, Response res) {
        return "Usuario Creado";
    }
}
