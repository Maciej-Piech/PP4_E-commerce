package com.maciejpiech.sales;

public class DummyPaymentGateway implements PaymentGateway {
    RegisterPaymentResponse handle(RegisterPaymentRequest registerPaymentRequest) {
        return new RegisterPaymentResponse(
                "dummyId",
                "https://gateway/url");
    }
}
