package com.hermez.saferrot.escrow.dto.response;

import lombok.Data;

@Data
public class PaymentResponse {
    private String paymentId;
    private String paymentUrl;
}
