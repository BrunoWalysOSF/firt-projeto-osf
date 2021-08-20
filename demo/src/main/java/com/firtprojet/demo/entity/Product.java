package com.firtprojet.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brands brands;

	@JsonBackReference
	@OneToMany(mappedBy = "product")
	private List<Stocks> stocks;

	@OneToMany(mappedBy = "product")
	private List<OrderItems> orderItems;

	public Product(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Product(Long id, String name, Double price,
				   Category category, Brands brands, List<Stocks> stocks, List<OrderItems> orderItems) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.brands = brands;
		this.stocks = stocks;
		this.orderItems = orderItems;
	}

	public Product(Long id , String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
			}

	public Product() {
	}
}
