package com.example.demo.backend.DB.repositories;

import com.example.demo.backend.DB.entities.ShopCustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopCustomerOrderRepository extends JpaRepository<ShopCustomerOrder, Integer> {
}
