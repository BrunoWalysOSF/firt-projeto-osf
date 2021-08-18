package com.firtprojet.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Stores {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "strore_name")
    private String storeName;

    private String phone;
    private String email;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    @JsonBackReference
    @OneToMany(mappedBy = "stores")
    private List<Stocks> stocks;

    @JsonBackReference
    @OneToMany(mappedBy = "stores")
    private List<Staffs> staffs;

    @JsonBackReference
    @OneToMany(mappedBy = "stores")
    private List<Orders> orders;
}
