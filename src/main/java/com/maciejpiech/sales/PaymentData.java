package com.maciejpiech.sales;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class PaymentData {
    String paymentId;
    String reservationId;
    String url;
}
