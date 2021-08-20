package com.firtprojet.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
public class Stocks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "stores_id")
    private Stores stores;

    private int quantity;

    public Stocks() {
    }

    public Stocks(Long id, Product product, Stores stores, int quantity) {
        this.id = id;
        this.product = product;
        this.stores = stores;
        this.quantity = quantity;
    }
}
