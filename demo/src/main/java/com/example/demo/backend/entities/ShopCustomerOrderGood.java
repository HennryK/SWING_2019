package com.example.demo.backend.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ShopCustomerOrderGood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int quantity;

    @ManyToOne
    @JoinColumn
    private ShopCustomerOrder shopCustomerOrder;

    public ShopCustomerOrderGood(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public ShopCustomerOrderGood() {
    }

    public void setShopCustomerOrder(ShopCustomerOrder shopCustomerOrder) {
        this.shopCustomerOrder = shopCustomerOrder;
    }
}
