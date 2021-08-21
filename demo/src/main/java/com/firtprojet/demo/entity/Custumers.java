package com.firtprojet.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Custumers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firtName;
    private String lastName;
    private String phone;
    private String email;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    @JsonBackReference
    @OneToMany(mappedBy = "custumers")
    private List<Orders> orders;


}
