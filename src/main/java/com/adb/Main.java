package com.adb;

import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

import static spark.Spark.*;

public class Main {
    public static void main (String[] args){
        get("/hello", Main::getHello);
        post("/users/create", Main::postCreateUser);
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
        return "Usuario Creado";
    }
}
