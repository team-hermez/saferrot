package com.hermez.saferrot.escrow.entity;

import com.hermez.saferrot.clientserver.entity.ClientServer;
import com.hermez.saferrot.customer.entity.Buyer;
import com.hermez.saferrot.customer.entity.Seller;
import com.hermez.saferrot.shipping.entity.Shipping;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "escrow")
public class Escrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ESCROW_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CLIENT_SERVER_ID", nullable = false)
    private ClientServer clientServer;

    @ManyToOne
    @JoinColumn(name = "ESCROW_STATUS_ID", nullable = false)
    private EscrowStatus escrowStatus;

    @ManyToOne
    @JoinColumn(name = "SHIPPING_ID")
    private Shipping shipping;

    @ManyToOne
    @JoinColumn(name = "SELLER_ID")
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "BUYER_ID")
    private Buyer buyer;

    @Column(name = "PAYMENT_ID")
    private String paymentId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "AMOUNT")
    private Integer amount;

    @Column(name = "SAFE_DAY")
    private Integer safeDay;

    @Column(name = "MERCHANT_UID")
    private String merchantUid;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UDAPTED_AT")
    private LocalDateTime updatedAt;
}
