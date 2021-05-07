package com.adb.clients;

import com.adb.config.Config;
import com.adb.dtos.CarJson;
import com.adb.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;


public class CarsRestClient {
    public static String DEFAULT_CLIENT_URL = "/cars/%d";
    public static String POST_CLIENT_URL = "/cars";

    public static WebClient getRestClient() {
        return WebClient.create(Config.getDomainUrl());
    }

    public static CarJson getCars(Long carId) {
        //return getRestClient().get().uri(String.format(GET_CLIENT_URL, carId)).retrieve().bodyToMono(Car.class).block();
        Mono<CarJson> car = getRestClient().get()
                .uri(String.format(DEFAULT_CLIENT_URL, carId))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> clientError(response))
                .onStatus(HttpStatus::is5xxServerError, response -> serverError(response))
                .bodyToMono(CarJson.class);
        return car.block();
    }

    public static CarJson getCarsV2(Long carId) throws ApiException {
        try {
            return getRestClient().get().uri(String.format(DEFAULT_CLIENT_URL, carId)).retrieve().bodyToMono(CarJson.class).block();
        } catch (WebClientRequestException e) {
            throw new ApiException("Critical error - cannot reach the server.");
        } catch (WebClientResponseException e) {
            switch (e.getRawStatusCode()) {
                case HttpServletResponse.SC_NOT_FOUND:
                    throw new ApiException(String.format("Car %d not found.", carId), HttpServletResponse.SC_NOT_FOUND);
                case HttpServletResponse.SC_BAD_REQUEST:
                    throw new ApiException("Invalid parameter.", HttpServletResponse.SC_BAD_REQUEST);
                default:
                    throw new ApiException("Internal Server Error.", e);
            }
        }

    }

    public static void deleteCar(Long carId) throws ApiException {
        try {
            getRestClient().delete().uri(String.format(DEFAULT_CLIENT_URL, carId)).header("X-Admin", "true").retrieve().bodyToMono(Void.class).block();
        } catch (WebClientRequestException e) {
            throw new ApiException("Critical error - cannot reach the server.");
        } catch (WebClientResponseException e) {
            switch (e.getRawStatusCode()) {
                case HttpServletResponse.SC_NOT_FOUND:
                    throw new ApiException(String.format("Car %d not found.", carId), HttpServletResponse.SC_NOT_FOUND);
                case HttpServletResponse.SC_BAD_REQUEST:
                    throw new ApiException("Invalid parameter.", HttpServletResponse.SC_BAD_REQUEST);
                case HttpServletResponse.SC_FORBIDDEN:
                    throw new ApiException(e.getStatusText(), HttpServletResponse.SC_FORBIDDEN);
                default:
                    throw new ApiException("Internal Server Error.", e);
            }
        }

    }

    public static void createVW(CarJson car) throws ApiException {
        try {
            getRestClient().post().uri(POST_CLIENT_URL).contentType(MediaType.APPLICATION_JSON_UTF8).body(car, CarJson.class).header("X-Admin", "true").retrieve().bodyToMono(Void.class).block();
        } catch (WebClientRequestException e) {
            throw new ApiException("Critical error - cannot reach the server.");
        } catch (WebClientResponseException e) {
            switch (e.getRawStatusCode()) {
                case HttpServletResponse.SC_BAD_REQUEST:
                    throw new ApiException("Invalid parameter.", HttpServletResponse.SC_BAD_REQUEST);
                case HttpServletResponse.SC_FORBIDDEN:
                    throw new ApiException(e.getStatusText(), HttpServletResponse.SC_FORBIDDEN);
                default:
                    throw new ApiException("Internal Server Error.", e);
            }
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }

    }

    private static Mono<? extends Throwable> serverError(ClientResponse res) {
        switch (res.statusCode()) {
            case INTERNAL_SERVER_ERROR:
                return Mono.error(new Exception("The request could not be processed."));
            default:
                return Mono.error(new Exception("Error %d." + res.statusCode()));
        }
    }

    private static Mono<? extends Throwable> clientError(ClientResponse res) {
        switch (res.statusCode()) {
            case NOT_FOUND:
                return Mono.error(new Exception("The Car was not found."));
            case BAD_REQUEST:
                return Mono.error(new Exception("Invalid data."));
            default:
                return Mono.error(new Exception("Error %d." + res.statusCode()));
        }
    }

}
