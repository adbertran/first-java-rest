package com.adb.config;

public class Config {
    public static int getSparkPort(){
        int port;
        try{
            port = Integer.parseInt(System.getProperty("spark.port"));
        } catch (NumberFormatException e){
            port = 4567;
        }
        return port;
    }
}
