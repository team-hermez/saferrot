package com.hermez.saferrot.shipping.apdapter;

import com.hermez.saferrot.shipping.dto.request.TrackingInfoRequest;
import com.hermez.saferrot.shipping.dto.response.TrackingInfoResponse;

public interface SweetTrackerAdapter {

    TrackingInfoResponse getTrackingInfo(TrackingInfoRequest request);

}
