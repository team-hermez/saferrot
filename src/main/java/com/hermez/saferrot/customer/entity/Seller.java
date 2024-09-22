package com.hermez.saferrot.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SELLER_ID")
    private Integer id;

    @Column(name = "SELLER_CODE")
    private String sellerCode;

    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "EMAIL")
    private String email;
}
