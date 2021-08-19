package com.firtprojet.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
