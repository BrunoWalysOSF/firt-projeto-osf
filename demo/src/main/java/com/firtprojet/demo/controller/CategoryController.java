package com.firtprojet.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firtprojet.demo.entity.Category;
import com.firtprojet.demo.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping
	public List<Category> getAllCategory(){
		return this.categoryRepository.findAll();
	}

	@PostMapping
	public Category createCategory(@RequestBody Category category) {
		return this.categoryRepository.save(category);
	}
}
