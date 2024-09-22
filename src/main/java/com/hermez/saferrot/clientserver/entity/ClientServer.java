package com.hermez.saferrot.clientserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "client_server")
public class ClientServer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_SERVER_ID")
    private Integer clientServerId;

    @Column(name = "SERVER_NAME")
    private String serverName;

    @Column(name = "SEVER_KEY")
    private String serverKey;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;
}
