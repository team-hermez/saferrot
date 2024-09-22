package com.hermez.saferrot.escrow.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentResultRequest {
    private String productId;
    private String merchantUid;
    private String escrowCode;
    private String buyerId;
    private String loadAddress;
    private String detailAddress;
    private String buyerPostcode;
}
