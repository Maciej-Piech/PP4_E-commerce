package com.maciejpiech.payu;

import lombok.Data;

@Data
public class OrderCreateResponse {
    Status status;
    String redirectUri;
    String orderId;
}
