package com.hermez.saferrot.shipping.repository;

import com.hermez.saferrot.escrow.entity.Escrow;
import com.hermez.saferrot.shipping.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShippingRepository extends JpaRepository<Shipping, Integer> {

    Optional<Shipping> findByEscrow(Escrow escrow);

}
