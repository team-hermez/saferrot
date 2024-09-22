package com.hermez.saferrot.escrow.dto.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PaymentCompleteResponse {
    private int clientServerId;
    private int escrowStatusId;
    private String callBackUrl;
    private String resultUrl;
    private String paymentId;
    private String productName;
    private String amount;
    private int safeDay;
    private String buyerCode;
    private String buyerEmail;
    private String sellerCode;
    private String sellerEmail;
    private String sellerAccount;
    private String loadAddress;
    private String detailAddress;
    private String merchantUid;
    private String receiptUrl;
    private String productId;
    private String buyerPostcode;
}
