package com.adb;

import static spark.Spark.*;

public class Main {
    public static void main (String[] args){
        get("/hello",(req,res)->"Hello World");
        post("/users/create",(req,res)->"Usuario Creado");
    }
}
