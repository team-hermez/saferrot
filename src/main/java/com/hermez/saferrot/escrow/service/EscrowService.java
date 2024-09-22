package com.hermez.saferrot.escrow.service;


import com.hermez.saferrot.escrow.dto.request.PaymentResultRequest;
import com.hermez.saferrot.escrow.dto.request.PurchaseConfirmRequest;
import com.hermez.saferrot.escrow.dto.response.PaymentCompleteResponse;

public interface EscrowService {

    PaymentResultRequest saveEscrow(PaymentCompleteResponse response);

    boolean confirmPayment(PurchaseConfirmRequest request);
}
