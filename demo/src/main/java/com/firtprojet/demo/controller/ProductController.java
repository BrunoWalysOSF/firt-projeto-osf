package com.firtprojet.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.firtprojet.demo.entity.Product;
import com.firtprojet.demo.exception.ResourceNotFound;
import com.firtprojet.demo.repository.CategoryRepository;
import com.firtprojet.demo.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	public List<Product> getAllProduct() {
		return this.productRepository.findAll();
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") long productId) {
		return this.productRepository.findById(productId).
				orElseThrow(()-> new ResourceNotFound("Product not found: "+productId));
	}
	
	@GetMapping(value = "findByName")
	@ResponseBody
	public ResponseEntity<List<Product>> getProductByNome(@RequestParam(name="name") String productName) {
		List<Product> productList = productRepository.findProductByName(productName.trim().toUpperCase());
		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
	}
		
		
	@PostMapping("/{id}")
	public Product createProduct(@RequestBody Product product, @PathVariable("id") long categoryId) {
		product.setCategory(this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFound("Category not found: "+categoryId)));
		return this.productRepository.save(product);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@RequestBody Product product , @PathVariable("id") long productId) {
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(()-> new ResourceNotFound("Product not found: "+productId));
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setCategory(product.getCategory());
		
		return this.productRepository.save(existingProduct);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deletProduct(@PathVariable("id") long productId){
		Product existingProduct = this.productRepository.findById(productId)
				.orElseThrow(()-> new ResourceNotFound("Product not found: "+productId));
		this.productRepository.delete(existingProduct);
				return ResponseEntity.ok().build();
		
	}
}
