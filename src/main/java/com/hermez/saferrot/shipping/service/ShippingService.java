package com.hermez.saferrot.shipping.service;

import com.hermez.saferrot.shipping.dto.request.TrackingRequest;
import com.hermez.saferrot.shipping.dto.response.ShippingViewResponse;
import com.hermez.saferrot.shipping.dto.response.TrackingInfoResponse;

public interface ShippingService {

    TrackingInfoResponse registerTrackingInfo(TrackingRequest trackingRequest);

    ShippingViewResponse showShippingView(String merchantUid);
}
