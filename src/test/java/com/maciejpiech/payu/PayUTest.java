package com.maciejpiech.payu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PayUTest {

    @Test
    void itRegisterExamplePayment() {
        PayU payu = thereIsPayUApiClient();
        String reservationId = thereIsExampleReservation();
        OrderCreateRequest request = thereIsExampleOrderCreateRequest();

        OrderCreateResponse response = payu.handle(request);

        assertEquals("SUCCESS", response.getStatus().getStatusCode());
        assertNotNull(response.getOrderId());
        assertNotNull(response.getRedirectUri());
    }

    private OrderCreateRequest thereIsExampleOrderCreateRequest() {
//
        return OrderCreateRequest.builder()
                .notifyUrl("https://your.eshop.com/notify")
                .customerIp("127.0.0.1")
                .description("Tea shop")
                .currencyCode("PLN")
                .totalAmount(24)
                .buyer(Buyer.builder()
                        .firstName("Michael")
                        .lastName("Jackson")
                        .email("michael@jackson.com")
                        .phone("123456789")
                        .language("eng")
                        .build())
                .products(Arrays.asList(
                        Product.builder()
                                .name("Black Tea")
                                .quantity(1)
                                .unitPrice(24)
                                .build()
                ))
                .build();
    }

    private String thereIsExampleReservation() {
        return UUID.randomUUID().toString();
    }

    private PayU thereIsPayUApiClient() {
        return new PayU("120512");
    }
}