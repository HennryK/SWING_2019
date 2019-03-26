package com.example.demo.backend.repositories;

import com.example.demo.backend.entities.ShopCustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopCustomerOrderRepository extends JpaRepository<ShopCustomerOrder, Integer> {
}
