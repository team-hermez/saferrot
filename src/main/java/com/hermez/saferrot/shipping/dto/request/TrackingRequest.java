package com.hermez.saferrot.shipping.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrackingRequest {
    private Integer paymentId;
    private String courierCode;
    private String trackingNumber;
    private String merchantId;
}
