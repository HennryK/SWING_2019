package com.example.demo.backend.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(exclude = "shopCustomerOrders")
@Entity
public class ShopCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "shopCustomer", cascade = CascadeType.ALL)
    private Set<ShopCustomerOrder> shopCustomerOrders;

    public ShopCustomer(String name, ShopCustomerOrder... shopCustomerOrders) {
        this.name = name;
        this.shopCustomerOrders = Stream.of(shopCustomerOrders).collect(Collectors.toSet());
        this.shopCustomerOrders.forEach(x -> x.setShopCustomer(this));
    }

    public ShopCustomer() { }
}
