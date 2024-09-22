package com.hermez.saferrot.escrow.adapter;

import com.hermez.saferrot.escrow.dto.request.NHTransferRequest;
import com.hermez.saferrot.escrow.dto.response.NHTransferResponse;

public interface NhApiAdapter {

    NHTransferResponse executeTransfer(NHTransferRequest request);
}
