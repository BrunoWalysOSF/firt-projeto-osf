package com.firtprojet.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firtprojet.demo.entity.Category;
import com.firtprojet.demo.entity.Product;
import com.firtprojet.demo.repository.CategoryRepository;
import com.firtprojet.demo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductController productController;

	@MockBean
	private ProductRepository productRepository;

	@MockBean
	private CategoryRepository categoryRepository;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

		Product produto = new Product(12L,"Microondas",200.0);
		Category categoria = new Category(5L,"Eletronic");
		List<Product> productList = new ArrayList<>();
		List<Category> categoryList = new ArrayList<>();
		produto.setCategory(categoria);
		categoryList.add(categoria);
		productList.add(produto);


		when(this.categoryRepository.findCategoryByName("Eletronic")).thenReturn(categoryList);
		when(this.productRepository.findProductByCategory(5L)).thenReturn(productList);

		when(this.productRepository.findById(12L)).thenReturn(java.util.Optional.of(produto));
	}
	
	@Test
	public void deveRetornaSucessol_QuandoBuscarProductPorId() throws Exception {
		Product produto = new Product(12L,"Microondas",200.0);
		Category categoria = new Category(5L,"Eletronic");
		produto.setCategory(categoria);
		mockMvc.perform(get("/product/{id}",12L)
				.contentType("application/jason"))
				.andExpect(status().isOk());

		ResponseEntity<Product> produto2 = productController.findProductById(12L);

		assertThat(produto2.getBody().getId()).isEqualTo(produto.getId());
		assertThat(produto2.getBody().getName()).isEqualTo(produto.getName());
		assertThat(produto2.getBody().getPrice()).isEqualTo(produto.getPrice());
		assertThat(produto2.getBody().getCategory()).isEqualTo(produto.getCategory());
		assertThat(produto2.getBody()).isEqualTo(produto);
	}
	@Test
	public void deveRtornaSucesso_QuandoBuscarProductPorCategoria() throws Exception {
		List<Product> productList = new ArrayList<>();
		Product produto = new Product(12L,"Microondas",200.0);
		Category categoria = new Category(5L,"Eletronic");
		produto.setCategory(categoria);
		productList.add(produto);
		mockMvc.perform(get("/product/findProductByCategoryName")
				.contentType("application/jason")
				.param("name","Eletronic"))
				.andExpect(status().isOk());

		ResponseEntity<List<Product>> productRetor = productController.findProductByCategoryName("Eletronic");
		assertThat(productList).isEqualTo(productRetor.getBody());
	}
	@Test
	public void deveRtornaNaoEncontrado_QuandoBuscarProductPorCategoria() throws Exception {
		when(this.productRepository.findProductByCategory(5L)).thenReturn(null);
		List<Product> productList = null;
		mockMvc.perform(get("/product/findProductByCategoryName")
						.contentType("application/jason")
						.param("name", "Eletronic"))
				.andExpect(status().isNotFound());

		ResponseEntity<List<Product>> productRetor = productController.findProductByCategoryName("Eletronic");
		assertThat(productList).isEqualTo(productRetor.getBody());
	}

}
