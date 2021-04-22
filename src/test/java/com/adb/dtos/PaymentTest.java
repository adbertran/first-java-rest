package com.adb.dtos;

import com.adb.utils.JsonFormatter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.jetty.util.UrlEncoded;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @Test
    public void paymentTest() throws JsonProcessingException {
        String json = null;
        File file = null;
        URL url = Thread.currentThread().getContextClassLoader().getResource("mocks/payments/1.json");

        try {
            file = new File(url.toURI());
            json = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        assertNotNull(file);
        assertNotNull(json);

        Payment payment = JsonFormatter.parse(json, Payment.class);
        assertEquals(1L, payment.getId());
        assertEquals(new BigDecimal("123.45"), payment.getAmount());
        assertEquals("VISA",payment.getOrigin());
    }
}