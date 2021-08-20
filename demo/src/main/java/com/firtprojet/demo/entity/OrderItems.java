package com.firtprojet.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;
    private double listPrice;
    private double discount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderItems() {
    }

    public OrderItems(Long id, int quantity, double listPrice, double discount, Orders orders, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.listPrice = listPrice;
        this.discount = discount;
        this.orders = orders;
        this.product = product;
    }

}
