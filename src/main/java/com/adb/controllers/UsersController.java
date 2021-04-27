package com.adb.controllers;

import com.adb.domain.Users;
import com.adb.dtos.UserJson;
import com.adb.persistence.DaoService;
import com.adb.utils.JsonFormatter;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

public class UsersController {
    public static String postCreateUser(Request req, Response res) {
        try {
            UserJson userJson = JsonFormatter.parse(req.body(), UserJson.class);

            Users userDb = Users.createFrom(userJson);

            DaoService.INSTANCE.merge(userDb);

            res.header("Content-Type", "application/json");
            res.status(HttpServletResponse.SC_OK);


            return JsonFormatter.format(userJson);
        } catch (Exception e) {
            res.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Se rompio json: " + e.getMessage();
        }
    }

    public static String deleteUser(Request req, Response res) {
        try {
            UserJson userJson = JsonFormatter.parse(req.body(), UserJson.class);

            DaoService.INSTANCE.deleteUser(userJson.getUserID());

            res.header("Content-Type", "application/json");
            res.status(HttpServletResponse.SC_OK);


            return JsonFormatter.format(userJson);
        } catch (Exception e) {
            res.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Se rompio json: " + e.getMessage();
        }
    }
}
