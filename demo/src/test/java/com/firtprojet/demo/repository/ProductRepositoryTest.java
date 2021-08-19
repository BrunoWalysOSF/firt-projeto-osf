package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.Category;
import com.firtprojet.demo.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void save_ProductPersist_WhenSucessful(){
        Product product = new Product("Liquidificador",200.0);
        Product productSave = this.productRepository.save(product);
        assertThat(productSave).isNotNull();
        assertThat(productSave.getId()).isNotNull();
        assertThat(productSave.getName()).isEqualTo(product.getName());
    }
    @Test
    public void save_UpdateProduct_WhenSucessful(){
        Product product = new Product("Liquidificador",200.0);
        Product productSave = this.productRepository.save(product);
        productSave.setName("Geladeira");
        Product productUpdate = this.productRepository.save(productSave);
        assertThat(productUpdate).isNotNull();
        assertThat(productUpdate.getId()).isNotNull();
        assertThat(productUpdate.getName()).isEqualTo(productSave.getName());
    }

    @Test
    public void delete_RemoveProduct_WhenSucessful(){
        Product product = new Product("Liquidificador",200.0);
        Product productSave = this.productRepository.save(product);
        this.productRepository.delete(productSave);
        Optional<Product> productO = this.productRepository.findById(productSave.getId());
        assertThat(productO).isEmpty();
    }
    @Test
    public void findByName_ReturnProductList_WhenSucessful(){
        Product product = new Product("Liquidificador",200.0);
        Product productSave = this.productRepository.save(product);
        List<Product> productByName = this.productRepository.findProductByName(productSave.getName());
        assertThat(productByName).isNotEmpty();
        assertThat(productByName).contains(productSave);
    }

    @Test
    public void findByName_ReturnProductListEmpty_WhenProductisNotFound(){
         List<Product> productByName = this.productRepository.findProductByName("sass");
         assertThat(productByName).isEmpty();
    }
    @Test
    public void findBynameCategory_ReturnProductListByNameCategory_WhenSucessuful(){
        Product produto = new Product(12L,"Microondas",200.0);
        Category categoria = new Category(5L,"Eletronic");
        produto.setCategory(categoria);
        Product productSave = this.productRepository.save(produto);
        List<Product> productByCategory = this.productRepository.findProductByCategory(5L);
        assertThat(productByCategory).isNotEmpty();
        assertThat(productByCategory).contains(productSave);
    }
    @Test
    public void findBynameCategory_ReturnProductListEmptyByNameCategory_WhenNotFoundCategory(){
        List<Product> productByCategory = this.productRepository.findProductByCategory(1L);
        assertThat(productByCategory).isEmpty();
    }
}
