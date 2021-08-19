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
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@JsonBackReference
	@OneToMany(mappedBy = "category")
	private List<Product> listProduct;

	public Category(Long id ,String name){
		super();
		this.id = id;
		this.name = name;
	}
	public Category(String name) {
		super();
		this.name = name;
	}

}
