package com.caduaraujo.webservices.repositories;

import com.caduaraujo.webservices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
