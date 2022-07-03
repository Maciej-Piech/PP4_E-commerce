package com.maciejpiech.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class OrderingTest {

    private List<ProductDetails> products;
    private ReservationStorage reservationStorage;

    @BeforeEach
    void setUp() {

        products = new ArrayList<>();
        reservationStorage = new ReservationStorage();

    }

    @Test
    void itAllowsToOrderCollectedProducts() {
        String productId = thereIsExampleProduct();
        Sales sales = thereIsSalesModule();
        String customerId = thereIsCustomer();
        sales.addToCart(customerId, productId);
        Offer seenOffer = sales.getCurrentOffer(customerId);

        PaymentData payment = sales.acceptOffer(customerId, seenOffer, exampleCustomerData());

        String reservationId = payment.getReservationId();

        assertNotNull(payment.getUrl());

        assertNotNull(reservationId);
        thereIsPendingReservationWithId(reservationId);
    }

    private void thereIsPendingReservationWithId(String reservationId) {
        Optional<Reservation> optionalReservation = reservationStorage.find(reservationId);

        assertTrue(optionalReservation.isPresent());
    }

    @Test
    void denayPurchaseWhenOfferDifferentThenSeenOffer() {
        String productId = thereIsExampleProduct();
        Sales sales = thereIsSalesModule();
        String customerId = thereIsCustomer();
        sales.addToCart(customerId, productId);
        Offer seenOffer = sales.getCurrentOffer(customerId);
        Offer newOffer = Offer.of(BigDecimal.valueOf(1000), 1);

        assertThrows(OfferNotMatchedException.class, () -> {
            sales.acceptOffer(
                    customerId,
                    newOffer,
                    exampleCustomerData());
        });
    }

    private String thereIsExampleProduct() {
        String productId = "001";
        products.add(new ProductDetails(
                productId,
                "Black Tea",
                BigDecimal.valueOf(10.10)));
        return productId;
    }

    private Sales thereIsSalesModule() {
        return new Sales(
                new CartStorage(),
                new ListProductDetailsProvider(products),
                new DummyPaymentGateway(),
                reservationStorage
        );
    }

    private String thereIsCustomer() {
        return "Maciej";
    }

    private ClientData exampleCustomerData()  {
        return ClientData.builder()
                .firstname("Michael")
                .lastname("Jackson")
                .email("michael@jackson.pl")
                .build();
    }
}
