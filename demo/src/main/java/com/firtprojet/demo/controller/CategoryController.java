package com.firtprojet.demo.controller;

import com.firtprojet.demo.entity.Category;
import com.firtprojet.demo.exception.ResourceNotFound;
import com.firtprojet.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	public ResponseEntity<?> getAllCategory(Pageable pageable){
		return new ResponseEntity<>(this.categoryRepository.findAll(pageable),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable("id") long categoryId) {
		Category category = this.categoryRepository.findById(categoryId).
				orElseThrow(() -> new ResourceNotFound("Category not found: " + categoryId));
		return new ResponseEntity<>(category,HttpStatus.OK);
	}
	
	@GetMapping(value = "findByName")
	@ResponseBody
	public ResponseEntity<List<Category>> findCategoryByName(@RequestParam("name") String categoryName ){
		List<Category> categoryList = categoryRepository.findCategoryByName(categoryName.trim().toUpperCase());
		return new ResponseEntity<List<Category>>(categoryList,HttpStatus.OK);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> createCategory(@RequestBody Category category) {
		return new ResponseEntity<>(this.categoryRepository.save(category),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@RequestBody Category category,@PathVariable("id") long categoryId) {
		Category existingCategory = this.categoryRepository.findById(categoryId)
				.orElseThrow(()->new ResourceNotFound("Category not found: "+categoryId));
		existingCategory.setName(category.getName());
		existingCategory.setListProduct(category.getListProduct());
		return new ResponseEntity<>(this.categoryRepository.save(existingCategory),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Category> deletCategory(@PathVariable("id") long categoryId){
		Category existingCategory = this.categoryRepository.findById(categoryId)
				.orElseThrow(()->new ResourceNotFound("Category not found: "+categoryId));
		this.categoryRepository.delete(existingCategory);
		return ResponseEntity.ok().build();
	}
}
