package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.Category;
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
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void save_PersistCategory_WhenSucessful(){
        Category categoria = new Category("Automotivos");
        Category categorySave = this.categoryRepository.save(categoria);
        assertThat(categorySave).isNotNull();
        assertThat(categorySave.getId()).isNotNull();
        assertThat(categorySave.getName()).isEqualTo(categoria.getName());
    }

    @Test
    public void save_UpdateCategory_WhenSucessful(){
        Category categoria = new Category("Automotivos");
        Category categorySave = this.categoryRepository.save(categoria);
        categorySave.setName("Veiculos");
        Category categoryUpdate = this.categoryRepository.save(categorySave);

        assertThat(categoryUpdate).isNotNull();
        assertThat(categoryUpdate.getId()).isNotNull();
        assertThat(categoryUpdate.getName()).isEqualTo(categorySave.getName());
        assertThat(categoryUpdate.getId()).isEqualTo(categorySave.getId());
    }
    @Test
    public void delete_RemoveCategory_WhenSucessful(){
        Category categoria = new Category("Automotivos");
        Category categorySave = this.categoryRepository.save(categoria);
        this.categoryRepository.delete(categorySave);
        Optional<Category> categoryRepositoryById = this.categoryRepository.findById(categorySave.getId());
        assertThat(categoryRepositoryById).isEmpty();
    }

    @Test
    public void findCategoryByName_ReturnListCategory_WhenSucessful(){
        Category categoria = new Category("Automotivos");
        Category categorySave = this.categoryRepository.save(categoria);
        List<Category> categoryByName = this.categoryRepository.findCategoryByName(categorySave.getName());
        assertThat(categoryByName).isNotEmpty();
        assertThat(categoryByName).contains(categorySave);
    }
    @Test
    public void findCategoryByName_ReturnListEmptyCategory_WhenNotFoundCategory(){
        List<Category> categoryByName = this.categoryRepository.findCategoryByName("mmmmmmm");
        assertThat(categoryByName).isEmpty();
    }


}
