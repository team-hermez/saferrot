package com.hermez.saferrot.customer.respositroy;

import com.hermez.saferrot.customer.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository <Buyer, Integer> {
}
