package com.adb.controllers;

import com.adb.dtos.User;
import com.adb.utils.JsonFormatter;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

public class CreateUserController {
    public static String postCreateUser(Request req, Response res) {
        try {
            User user = JsonFormatter.parse(req.body(), User.class);
            res.header("Content-Type", "application/json");
            res.status(HttpServletResponse.SC_OK);
            return JsonFormatter.format(user);
        } catch (Exception e) {
            res.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Se rompio json: " + e.getMessage();
        }
    }
}
