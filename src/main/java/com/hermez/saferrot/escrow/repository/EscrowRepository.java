package com.hermez.saferrot.escrow.repository;

import com.hermez.saferrot.escrow.entity.Escrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EscrowRepository extends JpaRepository< Escrow, Integer> {
    Optional<Escrow> findByMerchantUid(String merchantUid);
}
