package com.hermez.saferrot.escrow.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaferrotPaymentRequest {
    String serverName;
    String apiKey;
    Integer productId;
    String productName;
    String buyerCode;
    String buyerEmail;
    String sellerCode;
    String sellerEmail;
    Integer amount;
    String sellerAccount;
    int safeDay;
    String merchantUid;
    String callbackUrl;
    String resultUrl;
}
