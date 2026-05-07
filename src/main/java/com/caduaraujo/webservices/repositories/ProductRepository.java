package com.caduaraujo.webservices.repositories;

import com.caduaraujo.webservices.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
