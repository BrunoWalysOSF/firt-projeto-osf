package com.firtprojet.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firtprojet.demo.entity.Product;
import com.firtprojet.demo.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductControler {

	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping
	public List<Product> getAllProduct(){
		return this.productRepository.findAll();
	}
	
}
