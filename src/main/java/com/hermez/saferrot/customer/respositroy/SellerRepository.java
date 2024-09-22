package com.hermez.saferrot.customer.respositroy;

import com.hermez.saferrot.customer.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
