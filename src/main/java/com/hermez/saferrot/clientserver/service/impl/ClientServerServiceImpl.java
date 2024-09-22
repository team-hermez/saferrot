package com.hermez.saferrot.clientserver.service.impl;

import com.hermez.saferrot.clientserver.entity.ClientServer;
import com.hermez.saferrot.clientserver.repository.ClientServerRepository;
import com.hermez.saferrot.clientserver.service.ClientServerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServerServiceImpl implements ClientServerService {

    private final ClientServerRepository clientServerRepository;

    public ClientServerServiceImpl(ClientServerRepository clientServerRepository) {
        this.clientServerRepository = clientServerRepository;
    }

    public ClientServer getClientServerById(int clientServerId) {
        Optional<ClientServer> clientServer = clientServerRepository.findById(clientServerId);
        return clientServer.orElseThrow(() -> new IllegalArgumentException("clientServerId를 찾을수 없습니다 : " + clientServerId));
    }
}
