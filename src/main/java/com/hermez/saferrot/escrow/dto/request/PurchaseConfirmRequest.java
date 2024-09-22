package com.hermez.saferrot.escrow.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PurchaseConfirmRequest {
    private String merchantUid;
    private String paymentId;
    private String buyerId;

}
