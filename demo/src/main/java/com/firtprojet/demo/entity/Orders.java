package com.firtprojet.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int orderStatus;
    private Date orderDate;
    private Date requieredDate;
    private Date shipedDate;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Stores stores;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staffs staffs;

    @ManyToOne
    @JoinColumn(name = "custumers_id")
    private Custumers custumers;

    @JsonBackReference
    @OneToMany(mappedBy = "orders")
    private List<OrderItems> orderItems;

}
