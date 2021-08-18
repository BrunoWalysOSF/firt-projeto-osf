package com.firtprojet.demo.entity;

import javax.persistence.*;
import java.util.List;

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
}
