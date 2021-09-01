package com.firtprojet.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int orderStatus;
    private LocalDateTime orderDate;
    private LocalDateTime requieredDate;
    private LocalDateTime shipedDate;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonManagedReference
    private Stores stores;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    @JsonManagedReference
    private Staffs staffs;

    @ManyToOne
    @JoinColumn(name = "custumers_id")
    @JsonManagedReference
    private Custumers custumers;

    @JsonBackReference
    @OneToMany(mappedBy = "orders")
    private List<OrderItems> orderItems;

    public Orders() {
    }

    public Orders(Long id, int orderStatus, LocalDateTime orderDate, LocalDateTime requieredDate,
                  LocalDateTime shipedDate, Stores stores, Staffs staffs, Custumers custumers,
                  List<OrderItems> orderItems) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.requieredDate = requieredDate;
        this.shipedDate = shipedDate;
        this.stores = stores;
        this.staffs = staffs;
        this.custumers = custumers;
        this.orderItems = orderItems;
    }

}
