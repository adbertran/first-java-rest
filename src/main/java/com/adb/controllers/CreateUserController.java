package com.adb.controllers;

import com.adb.dtos.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

public class CreateUserController {
    public static String postCreateUser(Request req, Response res) {
        /*
        String body =   req.body();
        res.header("x-authorize","True");
        res.header("Content-type","application/json");
        return "User level: " + req.headers("x-level");


         */
        ObjectMapper objectMapper = new ObjectMapper();
        String json = req.body();
        String reply;
        User user = null;
        try {
            user = objectMapper.readValue(json, User.class);
            res.header("Content-Type", "application/json");
            res.status(HttpServletResponse.SC_OK);
            reply=objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            res.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Se rompio json: " + e.getMessage();
        }
        return reply;
    }
}
