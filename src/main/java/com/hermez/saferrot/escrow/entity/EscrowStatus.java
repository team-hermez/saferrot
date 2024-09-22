package com.hermez.saferrot.escrow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ESCROW_STATUS")
public class EscrowStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ESCROW_STATUS_ID")
    private Integer escrowStatusId;

    @Column(name = "CODE")
    private String code;

}
