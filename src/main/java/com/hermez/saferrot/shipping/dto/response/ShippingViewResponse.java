package com.hermez.saferrot.shipping.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShippingViewResponse {
    String courierCode;
    String trackingNumber;
    String apiKey;
}
