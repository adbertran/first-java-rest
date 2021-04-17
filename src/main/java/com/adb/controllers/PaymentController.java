package com.adb.controllers;

import com.adb.dtos.Payment;
import com.adb.dtos.Receipt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PaymentController {
    public static Object processPayment(Request req, Response res) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Payment payment = mapper.readValue(req.bodyAsBytes(), Payment.class);
        Receipt receipt = processTransaction(payment);
        String json = null;
        try {
            json = mapper.writeValueAsString(receipt);
        } catch (JsonProcessingException e) {
            res.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Se rompio json: " + e.getMessage();
        }
        res.header("Content-type","application/json");
        res.status(HttpServletResponse.SC_OK);
        return json;
    }

    private static Receipt processTransaction(Payment pmt) {
        return new Receipt(pmt.getId() * 10, pmt.getId(), true);
    }
}
