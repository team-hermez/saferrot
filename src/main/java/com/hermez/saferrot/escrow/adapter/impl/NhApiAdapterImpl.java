package com.hermez.saferrot.escrow.adapter.impl;

import com.hermez.saferrot.escrow.adapter.NhApiAdapter;
import com.hermez.saferrot.escrow.dto.request.NHTransferRequest;
import com.hermez.saferrot.escrow.dto.response.NHTransferResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


import java.util.Collections;

@Component
public class NhApiAdapterImpl implements NhApiAdapter {

    @Value("${nh.api.url}")
    protected String nhApiUrl;

    @Value("${nh.api.key}")
    protected String accessToken;

    private final RestTemplate restTemplate;

    public NhApiAdapterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public NHTransferResponse executeTransfer(NHTransferRequest request) {
        request.getHeader().setAccessToken(accessToken);
        try {
            ResponseEntity<NHTransferResponse> response = restTemplate.postForEntity(
                    nhApiUrl,
                    request,
                    NHTransferResponse.class
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "NH API 통신 중 오류발생: " + e.getMessage());
        }
    }
}
