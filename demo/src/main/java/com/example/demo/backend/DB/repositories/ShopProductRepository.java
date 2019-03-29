package com.example.demo.backend.repositories;

import com.example.demo.backend.entities.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopProductRepository extends JpaRepository<ShopProduct, Integer> {
}
