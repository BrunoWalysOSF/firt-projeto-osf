package com.firtprojet.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.firtprojet.demo.entity.Category;
import com.firtprojet.demo.entity.Product;
import com.firtprojet.demo.repository.CategoryRepository;

@SpringBootApplication
public class FirtProjetOsfApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirtProjetOsfApplication.class, args);

	}

}
