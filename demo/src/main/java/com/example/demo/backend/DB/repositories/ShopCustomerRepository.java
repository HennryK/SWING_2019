package com.example.demo.backend.repositories;

import com.example.demo.backend.entities.ShopCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopCustomerRepository extends JpaRepository<ShopCustomer, Integer> {
}
