package com.hermez.saferrot.clientserver.repository;

import com.hermez.saferrot.clientserver.entity.ClientServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientServerRepository extends JpaRepository<ClientServer, Integer> {
}
