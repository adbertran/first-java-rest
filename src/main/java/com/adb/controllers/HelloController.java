package com.adb.controllers;

import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

public class HelloController {
    public static String getHello(Request req, Response res) {
        if (req.queryParams("planet") == null) {
            res.status(HttpServletResponse.SC_BAD_REQUEST);
            return "Pusiste mal el parametro";
        }
        String planet=req.queryParams("planet");
        res.status(HttpServletResponse.SC_OK);
        return "Hello " + planet;
    }
}
