package com.example.demo.backend.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(exclude = "shopCustomerOrderGoods")
@Entity
public class ShopCustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private int cost;

    @ManyToOne
    @JoinColumn
    private ShopCustomer shopCustomer;


    @OneToMany(mappedBy = "shopCustomerOrder", cascade = CascadeType.ALL)
    private Set<ShopCustomerOrderGood> shopCustomerOrderGoods;

    public ShopCustomerOrder(Date date, int cost, ShopCustomerOrderGood... shopCustomerOrderGoods) {
        this.date = date;
        this.cost = cost;
        this.shopCustomerOrderGoods = Stream.of(shopCustomerOrderGoods).collect(Collectors.toSet());
        this.shopCustomerOrderGoods.forEach(x -> x.setShopCustomerOrder(this));
    }

    public ShopCustomerOrder() { }

    public ShopCustomer getShopCustomer() {
        return shopCustomer;
    }

    public void setShopCustomer(ShopCustomer shopCustomer) {
        this.shopCustomer = shopCustomer;
    }
}
