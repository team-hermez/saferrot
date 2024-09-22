package com.hermez.saferrot.escrow.controller;

import com.hermez.saferrot.escrow.dto.request.PurchaseConfirmRequest;
import com.hermez.saferrot.escrow.service.EscrowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/escrow")
public class EscrowRestController {

    private final EscrowService escrowService;

    public EscrowRestController(EscrowService escrowService) {
        this.escrowService = escrowService;
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmPayment(@RequestBody PurchaseConfirmRequest request) {
        try {
            boolean isConfirmed = escrowService.confirmPayment(request);
            if (isConfirmed) {
                return new ResponseEntity<>("결제가 성공적으로 확정되었습니다.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("결제 확정 실패: 이미 확정된 결제입니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("서버 오류: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
