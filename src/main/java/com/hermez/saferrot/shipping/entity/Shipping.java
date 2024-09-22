package com.hermez.saferrot.shipping.entity;

import com.hermez.saferrot.escrow.entity.Escrow;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "SHIPPING")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHIPMENT_ID")
    private Integer shipmentId;

    @ManyToOne
    @JoinColumn(name = "ESCROW_ID", referencedColumnName = "ESCROW_ID")
    private Escrow escrow;

    @Column(name = "COURIER_CODE")
    private String courierCode;

    @Column(name = "TRACKING_NUMBER")
    private String trackingNumber;

    @Column(name = "SHIPPED_AT")
    private Timestamp shippedAt;

    @Column(name = "DELIVERED_AT")
    private Timestamp deliveredAt;

    @Column(name = "COMPLEATE_YN")
    private String completeYn;

}
