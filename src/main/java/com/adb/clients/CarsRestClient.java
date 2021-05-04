package com.adb.clients;

import com.adb.config.Config;
import com.adb.dtos.Car;
import org.springframework.web.reactive.function.client.WebClient;


public class CarsRestClient {
    public static String GET_CLIENT_URL = "/cars/%d";

    public static WebClient getRestClient() {
        return WebClient.create(Config.getDomainUrl());
    }

    public static Car getCars(Long carId) {
        return getRestClient().get().uri(String.format(GET_CLIENT_URL, carId)).retrieve().bodyToMono(Car.class).block();

    }

}
