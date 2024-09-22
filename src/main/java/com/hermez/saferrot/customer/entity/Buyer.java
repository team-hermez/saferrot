package com.hermez.saferrot.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "buyer")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BUYER_ID")
    private Integer id;

    @Column(name = "BUYER_CODE")
    private String buyerCode;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ROAD_ADDRESS")
    private String roadAddress;

    @Column(name = "DETAIL_ADDRESS")
    private String detailAddress;

    @Column(name = "POST_CODE")
    private String postCode;

}