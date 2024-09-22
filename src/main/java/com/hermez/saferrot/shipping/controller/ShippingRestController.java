package com.hermez.saferrot.shipping.controller;

import com.hermez.saferrot.shipping.dto.request.TrackingRequest;
import com.hermez.saferrot.shipping.dto.response.TrackingInfoResponse;
import com.hermez.saferrot.shipping.service.ShippingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipping")
public class ShippingRestController {

    @Value("${sweettracker.api.key}")
    String sweettrackerApiKey;

    private final ShippingService shippingService;

    public ShippingRestController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping("/register-logistics")
    public TrackingInfoResponse registerTrackingInfo(@RequestBody TrackingRequest request) {
        return shippingService.registerTrackingInfo(request);
    }
}
