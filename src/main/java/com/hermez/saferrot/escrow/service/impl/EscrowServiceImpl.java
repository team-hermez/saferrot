package com.hermez.saferrot.escrow.service.impl;

import com.hermez.saferrot.clientserver.service.ClientServerService;
import com.hermez.saferrot.customer.entity.Buyer;
import com.hermez.saferrot.customer.entity.Seller;
import com.hermez.saferrot.customer.respositroy.BuyerRepository;
import com.hermez.saferrot.customer.respositroy.SellerRepository;
import com.hermez.saferrot.escrow.adapter.NhApiAdapter;
import com.hermez.saferrot.escrow.dto.request.NHTransferRequest;
import com.hermez.saferrot.escrow.dto.request.PaymentResultRequest;
import com.hermez.saferrot.escrow.dto.request.PurchaseConfirmRequest;
import com.hermez.saferrot.escrow.dto.response.NHTransferResponse;
import com.hermez.saferrot.escrow.dto.response.PaymentCompleteResponse;
import com.hermez.saferrot.escrow.entity.Escrow;
import com.hermez.saferrot.escrow.entity.EscrowStatus;
import com.hermez.saferrot.escrow.repository.EscrowRepository;
import com.hermez.saferrot.escrow.repository.EscrowStatusRepository;
import com.hermez.saferrot.escrow.service.EscrowService;
import com.hermez.saferrot.shipping.repository.ShippingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

@Service
public class EscrowServiceImpl implements EscrowService {

    private final ClientServerService clientServerService;
    private final EscrowRepository escrowRepository;
    private final EscrowStatusRepository escrowStatusRepository;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    private final ShippingRepository shippingRepository;
    private final NhApiAdapter nhApiAdapter;

    public EscrowServiceImpl(ClientServerService clientServerService, EscrowRepository escrowRepository,
                             EscrowStatusRepository escrowStatusRepository, BuyerRepository buyerRepository,
                             SellerRepository sellerRepository, ShippingRepository shippingRepository,
                             NhApiAdapter nhApiAdapter) {
        this.clientServerService = clientServerService;
        this.escrowRepository = escrowRepository;
        this.escrowStatusRepository = escrowStatusRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.shippingRepository = shippingRepository;
        this.nhApiAdapter = nhApiAdapter;
    }

    @Transactional
    @Override
    public PaymentResultRequest saveEscrow(PaymentCompleteResponse response) {
        Escrow escrow = createEscrow(response);
        createAndSaveBuyer(response, escrow);
        createAndSaveSeller(response, escrow);
        escrowRepository.save(escrow);

        return PaymentResultRequest.builder()
                .merchantUid(response.getMerchantUid())
                .productId(response.getProductId())
                .escrowCode(response.getPaymentId())
                .buyerId(response.getBuyerCode())
                .buyerPostcode(response.getBuyerPostcode())
                .detailAddress(response.getDetailAddress())
                .loadAddress(response.getLoadAddress())
                .build();
    }

    private Escrow createEscrow(PaymentCompleteResponse response) {
        Escrow escrow = new Escrow();
        escrow.setClientServer(clientServerService.getClientServerById(1));
        escrow.setEscrowStatus(escrowStatusRepository.findById(1)
                .orElseThrow(() -> new EntityNotFoundException("거래 상태값 없음")));
        escrow.setPaymentId(response.getPaymentId());
        escrow.setProductName(response.getProductName());
        escrow.setAmount(Integer.parseInt(response.getAmount()));
        escrow.setSafeDay(response.getSafeDay());
        escrow.setMerchantUid(response.getMerchantUid());
        escrow.setCreatedAt(LocalDateTime.now());
        return escrow;
    }

    private void createAndSaveBuyer(PaymentCompleteResponse response, Escrow escrow) {
        Buyer buyer = new Buyer();
        buyer.setBuyerCode(response.getBuyerCode());
        buyer.setEmail(response.getBuyerEmail());
        buyer.setRoadAddress(response.getLoadAddress());
        buyer.setDetailAddress(response.getDetailAddress());
        buyer.setPostCode(response.getBuyerPostcode());
        buyerRepository.save(buyer);
        escrow.setBuyer(buyer);
    }

    private void createAndSaveSeller(PaymentCompleteResponse response, Escrow escrow) {
        Seller seller = new Seller();
        seller.setSellerCode(response.getSellerCode());
        seller.setAccount(response.getSellerAccount());
        seller.setEmail(response.getSellerEmail());
        sellerRepository.save(seller);
        escrow.setSeller(seller);
    }

//    private void createAndSaveShipping(PaymentCompleteResponse response, Escrow escrow) {
//        Shipping shipping = new Shipping();
//        shipping.setCourierCode(response.getCourierCode());
//        shipping.setTrackingNumber(response.getTrackingNumber());
//        shipping.setShippedAt(response.getShippedAt());
//        shipping.setDeliveredAt(response.getDeliveredAt());
//        shipping.setCompleteYn(response.getCompleteYn());
//        shippingRepository.save(shipping);
//        escrow.setShipping(shipping);
//    }

    @Override
    public boolean confirmPayment(PurchaseConfirmRequest request) {
        Optional<Escrow> escrowOpt = escrowRepository.findByMerchantUid(request.getMerchantUid());
        EscrowStatus escrowStatus = escrowStatusRepository.findById(2)
                .orElseThrow(() -> new EntityNotFoundException("거래 상태값 없음"));
        if (escrowOpt.isPresent()) {
            Escrow escrow = escrowOpt.get();
            if (escrowStatus.equals(escrow.getEscrowStatus())) {
                System.out.println("이미 결제됨");
                return false;
            }
            processTransfer(escrow.getSeller().getAccount(), String.valueOf((int)(escrow.getAmount() * 0.9)));
            escrow.setEscrowStatus(escrowStatus);
            escrowRepository.save(escrow);
            return true;
        }

        throw new EntityNotFoundException("결제 정보를 찾을 수 없습니다.");
    }

    public NHTransferResponse processTransfer(String accountNumber, String amount) {
        NHTransferRequest.Header header = new NHTransferRequest.Header();
        header.setApiNm("ReceivedTransferAccountNumber");
        header.setTsymd(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        header.setTrtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss")));
        header.setIscd("002643");
        header.setFintechApsno("001");
        header.setApiSvcCd("ReceivedTransferA");
        header.setIsTuno(String.valueOf(100000000 + new Random().nextLong(900000000L)));

        NHTransferRequest request = new NHTransferRequest();
        request.setHeader(header);
        request.setBncd("011");
        request.setAcno(accountNumber);
        request.setTram(amount);
        request.setDractOtlt("출금 인자");
        request.setMractOtlt("입금 인자");
        return nhApiAdapter.executeTransfer(request);
    }
}
