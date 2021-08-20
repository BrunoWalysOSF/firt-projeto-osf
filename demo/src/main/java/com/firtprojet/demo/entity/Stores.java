package com.firtprojet.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
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

    public Stores() {
    }

    public Stores(Long id, String storeName, String phone, String email, String street, String city, String state,
                  String zipCode, List<Stocks> stocks, List<Staffs> staffs, List<Orders> orders) {
        this.id = id;
        this.storeName = storeName;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.stocks = stocks;
        this.staffs = staffs;
        this.orders = orders;
    }

}
