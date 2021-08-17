package com.firtprojet.demo.controller;

import java.util.List;
import java.util.Optional;

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

import com.firtprojet.demo.entity.Category;
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
	public ResponseEntity<Product> findProductById(@PathVariable("id") long productId) {
		Product produto = this.productRepository.findById(productId).
				orElseThrow(()-> new ResourceNotFound("Product not found: "+productId));
		return ResponseEntity.ok(produto);
	}
	
	@GetMapping(value = "findProductByName")
	@ResponseBody
	public ResponseEntity<List<Product>> getProductByNome(@RequestParam(name="name") String productName) {
		List<Product> productList = productRepository.findProductByName(productName.trim().toUpperCase());
		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
	}
	
	@GetMapping("/findProductByCategoryName")
	@ResponseBody
	public ResponseEntity<List<Product>> findProductByCategoryName(@RequestParam(name="name") String categoryName){
		Category categoryFirtPos =  null;
		List<Product> productList = null;
		List<Category> categoryList = categoryRepository.findCategoryByName(categoryName);
		if (categoryList.size()==0)
			return new ResponseEntity<List<Product>>(productList,HttpStatus.NOT_FOUND);
		categoryFirtPos = categoryList.get(0);
		productList = productRepository.findProductByCategory(categoryFirtPos.getId());
		if (productList==null)
			return new ResponseEntity<List<Product>>(productList,HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
	}	
	
	@GetMapping(value = "findByCategoryId")
	@ResponseBody
	public ResponseEntity<List<Product>> getProductByCategory(@RequestParam(name="id") Long ctegoryId) {
		List<Product> productList = productRepository.findProductByCategory(ctegoryId);
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
