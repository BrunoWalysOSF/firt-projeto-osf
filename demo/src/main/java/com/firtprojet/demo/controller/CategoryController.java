package com.firtprojet.demo.controller;

import com.firtprojet.demo.entity.Category;
import com.firtprojet.demo.exception.ResourceNotFound;
import com.firtprojet.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@PostMapping(produces = "application/json")
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
