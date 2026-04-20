package com.caduaraujo.webservices.repositories;

import com.caduaraujo.webservices.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
