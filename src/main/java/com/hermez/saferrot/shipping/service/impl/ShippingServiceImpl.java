package com.hermez.saferrot.shipping.service.impl;

import com.hermez.saferrot.escrow.entity.Escrow;
import com.hermez.saferrot.escrow.repository.EscrowRepository;
import com.hermez.saferrot.shipping.apdapter.SweetTrackerAdapter;
import com.hermez.saferrot.shipping.dto.request.TrackingInfoRequest;
import com.hermez.saferrot.shipping.dto.request.TrackingRequest;
import com.hermez.saferrot.shipping.dto.response.ShippingViewResponse;
import com.hermez.saferrot.shipping.dto.response.TrackingInfoResponse;
import com.hermez.saferrot.shipping.entity.Shipping;
import com.hermez.saferrot.shipping.repository.ShippingRepository;
import com.hermez.saferrot.shipping.service.ShippingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Value("${sweettracker.api.key}")
    private String sweettrackerApiKey;

    private final EscrowRepository escrowRepository;
    private final ShippingRepository shippingRepository;
    private final SweetTrackerAdapter sweetTrackerAdapter;

    public ShippingServiceImpl(EscrowRepository escrowRepository, ShippingRepository shippingRepository, SweetTrackerAdapter sweetTrackerAdapter) {
        this.escrowRepository = escrowRepository;
        this.shippingRepository = shippingRepository;
        this.sweetTrackerAdapter = sweetTrackerAdapter;
    }

    @Transactional
    @Override
    public TrackingInfoResponse registerTrackingInfo(TrackingRequest trackingRequest) {
        Escrow escrow = escrowRepository.findByMerchantUid(trackingRequest.getMerchantId())
                .orElseThrow(() -> new IllegalArgumentException("해당 결제 ID에 대한 에스크로가 없습니다."));
        TrackingInfoResponse trackingInfoResponse = sweetTrackerAdapter.getTrackingInfo(new TrackingInfoRequest(trackingRequest.getCourierCode(), trackingRequest.getTrackingNumber(), null));
        Shipping shipping = new Shipping();
        shipping.setEscrow(escrow);
        shipping.setCourierCode(trackingRequest.getCourierCode());
        shipping.setTrackingNumber(trackingRequest.getTrackingNumber());
        shipping.setShippedAt(Timestamp.from(Instant.now()));
        shipping.setCompleteYn("N");
        shippingRepository.save(shipping);
        return trackingInfoResponse;
    }

    @Override
    public ShippingViewResponse showShippingView(String merchantUid) {
        Escrow escrow = escrowRepository.findByMerchantUid(merchantUid)
                .orElseThrow(() -> new IllegalArgumentException("등록된 배송 정보가 없습니다."));
        Shipping shipping = shippingRepository.findByEscrow(escrow)
                .orElseThrow(() -> new IllegalArgumentException("등록된 배송 정보가 없습니다."));
        return ShippingViewResponse.builder().
                courierCode(shipping.getCourierCode()).
                trackingNumber(shipping.getTrackingNumber()).
                apiKey(sweettrackerApiKey).
                build();
    }
}
