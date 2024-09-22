package com.hermez.saferrot.escrow.controller;

import com.hermez.saferrot.escrow.dto.response.PaymentCompleteResponse;
import com.hermez.saferrot.escrow.dto.response.PaymentResponse;
import com.hermez.saferrot.escrow.dto.request.SaferrotPaymentRequest;
import com.hermez.saferrot.escrow.service.EscrowService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/escrow")
public class EscrowController {

    @Value("${payment.url}")
    private String paymentUrl;

    private final RestTemplate restTemplate;
    private final EscrowService escrowService;

    public EscrowController(RestTemplate restTemplate, EscrowService escrowService) {
        this.restTemplate = restTemplate;
        this.escrowService = escrowService;
    }

    @PostMapping("/initiate-payment")
    public ResponseEntity<PaymentResponse> initiatePayment(@RequestBody String apiKey) {
        // todo 서버 인증 및 결제 요청을 수행
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentUrl(paymentUrl);
        return ResponseEntity.ok(paymentResponse);
    }

    @PostMapping("/payment-page")
    public String showPaymentPage(@ModelAttribute SaferrotPaymentRequest saferrotPaymentRequest,Model model) {
        model.addAttribute("request",saferrotPaymentRequest);
        return "safe-payment";
    }

    @PostMapping("/complete")
    @ResponseBody
    public void completePayment(@RequestBody PaymentCompleteResponse paymentCompleteResponse) {
        restTemplate.postForObject(paymentCompleteResponse.getResultUrl(), escrowService.saveEscrow(paymentCompleteResponse), Void.class);
    }
}