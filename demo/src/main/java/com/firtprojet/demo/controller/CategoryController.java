package com.firtprojet.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.firtprojet.demo.entity.Category;
import com.firtprojet.demo.exception.ResourceNotFound;
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
	
	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable("id") long categoryId) {
		return this.categoryRepository.findById(categoryId).
				orElseThrow(()->new ResourceNotFound("Category not found: "+categoryId));
	}
	
	@GetMapping(value = "findByName")
	@ResponseBody
	public ResponseEntity<List<Category>> findCategoryByName(@RequestParam("name") String categoryName ){
		List<Category> categoryList = categoryRepository.findCategoryByName(categoryName.trim().toUpperCase());
		return new ResponseEntity<List<Category>>(categoryList,HttpStatus.OK);
	}

	@PostMapping
	public Category createCategory(@RequestBody Category category) {
		return this.categoryRepository.save(category);
	}
	
	@PutMapping("/{id}")
	public Category updateCategory(@RequestBody Category category,@PathVariable("id") long categoryId) {
		Category existingCategory = this.categoryRepository.findById(categoryId)
				.orElseThrow(()->new ResourceNotFound("Category not found: "+categoryId));
		existingCategory.setName(category.getName());
		existingCategory.setListProduct(category.getListProduct());
		return this.categoryRepository.save(existingCategory);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Category> deletCategory(@PathVariable("id") long categoryId){
		Category existingCategory = this.categoryRepository.findById(categoryId)
				.orElseThrow(()->new ResourceNotFound("Category not found: "+categoryId));
		this.categoryRepository.delete(existingCategory);
		return ResponseEntity.ok().build();
	}
}
