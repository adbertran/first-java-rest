package com.adb.config;

public class Config {
    public static void init(){
        String port = System.getenv("port");
        System.out.println("Puerto: " + port);
    }
}
