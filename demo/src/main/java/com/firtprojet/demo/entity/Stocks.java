package com.firtprojet.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "stores_id")
    @JsonManagedReference
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
