package com.hermez.saferrot.shipping.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackingInfoRequest {

    @JsonProperty("t_code")
    private String courierCode;

    @JsonProperty("t_invoice")
    private String trackingNumber;

    @JsonProperty("t_key")
    private String apiKey;

    public TrackingInfoRequest(String courierCode, String trackingNumber) {
        this.courierCode = courierCode;
        this.trackingNumber = trackingNumber;
    }

    public TrackingInfoRequest(String courierCode, String trackingNumber, String apiKey) {
        this.courierCode = courierCode;
        this.trackingNumber = trackingNumber;
        this.apiKey = apiKey;
    }
}
