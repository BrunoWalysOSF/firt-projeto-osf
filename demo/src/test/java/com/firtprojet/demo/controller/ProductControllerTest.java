package com.firtprojet.demo.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.firtprojet.demo.repository.CategoryRepository;
import com.firtprojet.demo.repository.ProductRepository;


@WebMvcTest
public class ProductControllerTest {

	@Autowired
	private ProductController productController;

	@MockBean
	private ProductRepository productRepository;

	@MockBean
	private CategoryRepository categoryRepository;


	@Test
	public void deveRtornaSucesso_QuandoBuscarProductPorId() {
	
	}

}
