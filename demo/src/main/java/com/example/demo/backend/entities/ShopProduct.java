package com.example.demo.backend.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ShopProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String cost;

    public ShopProduct(String name, String cost) {
        this.name = name;
        this.cost = cost;
    }

    public ShopProduct() { }
}
