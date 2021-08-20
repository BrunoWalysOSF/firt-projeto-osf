package com.firtprojet.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
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

    public Orders() {
    }

    public Orders(Long id, int orderStatus, Date orderDate, Date requieredDate,
                  Date shipedDate, Stores stores, Staffs staffs, Custumers custumers,
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
