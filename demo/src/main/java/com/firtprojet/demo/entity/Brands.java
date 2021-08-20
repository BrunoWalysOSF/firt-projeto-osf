package com.firtprojet.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Brands {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "brands")
    private List<Product> listProduct;

    public Brands() {
    }

    public Brands(Long id, String name, List<Product> listProduct) {
        this.id = id;
        this.name = name;
        this.listProduct = listProduct;
    }

}
