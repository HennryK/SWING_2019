package com.example.demo.backend.DB.repositories;

import com.example.demo.backend.DB.entities.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopProductRepository extends JpaRepository<ShopProduct, Integer> {
}
