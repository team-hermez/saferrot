package com.hermez.saferrot.escrow.dto.response;

import lombok.Builder;

@Builder
public class PaymentServerResponse {
    private String loadAddress;
    private String detailAddress;
    private String buyerPostcode;
}
