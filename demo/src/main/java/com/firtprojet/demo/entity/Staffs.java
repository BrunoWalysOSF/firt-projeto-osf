package com.firtprojet.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Staffs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firtName;
    private String lastName;
    private String  email;
    private String phone;
    private int active;

    @JsonBackReference
    @OneToMany(mappedBy = "staffManagers")
    private List<Staffs> staffsList;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Stores stores;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Staffs staffManagers;

    @JsonBackReference
    @OneToMany(mappedBy = "staffs")
    private List<Orders> ordersList;

    public Staffs(String firtName, String lastName, String email, String phone) {
        this.firtName = firtName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public Staffs(Long id, String firtName, String lastName, String email, String phone, int active,
                  List<Staffs> staffsList, Stores stores, Staffs staffManagers, List<Orders> ordersList) {
        this.id = id;
        this.firtName = firtName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.active = active;
        this.staffsList = staffsList;
        this.stores = stores;
        this.staffManagers = staffManagers;
        this.ordersList = ordersList;
    }

    public Staffs() {
    }

}
