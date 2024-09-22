package com.hermez.saferrot.shipping.apdapter.impl;

import com.hermez.saferrot.shipping.apdapter.SweetTrackerAdapter;
import com.hermez.saferrot.shipping.dto.request.TrackingInfoRequest;
import com.hermez.saferrot.shipping.dto.response.TrackingInfoResponse;
import com.hermez.saferrot.shipping.exception.TrackingInfoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SweetTrackerAdapterImpl implements SweetTrackerAdapter {

    @Value("${sweettracker.api.key}")
    private String sweettrackerApiKey;

    @Value("${sweettracker.base.url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public SweetTrackerAdapterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public TrackingInfoResponse getTrackingInfo(TrackingInfoRequest request) {
        String url = baseUrl + "/api/v1/trackingInfo";
        try {
            String uri = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("t_code", request.getCourierCode())
                    .queryParam("t_invoice", request.getTrackingNumber())
                    .queryParam("t_key", sweettrackerApiKey)
                    .toUriString();
            ResponseEntity<TrackingInfoResponse> responseEntity = restTemplate.getForEntity(uri, TrackingInfoResponse.class);
            if (responseEntity.getBody().getCode()==null) {
                return responseEntity.getBody();
            } else {
                throw new TrackingInfoException("운송장 번호가 확인 불가능합니다.");
            }
        } catch (HttpClientErrorException e) {
            throw new TrackingInfoException("에러 : " + e.getResponseBodyAsString());
        }
    }

}
