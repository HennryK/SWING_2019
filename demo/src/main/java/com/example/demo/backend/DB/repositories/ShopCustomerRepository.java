package com.example.demo.backend.DB.repositories;

import com.example.demo.backend.DB.entities.ShopCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopCustomerRepository extends JpaRepository<ShopCustomer, Integer> {
}
