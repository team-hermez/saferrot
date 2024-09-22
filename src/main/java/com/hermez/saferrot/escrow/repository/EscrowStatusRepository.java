package com.hermez.saferrot.escrow.repository;

import com.hermez.saferrot.escrow.entity.EscrowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscrowStatusRepository extends JpaRepository<EscrowStatus, Integer> {
}
