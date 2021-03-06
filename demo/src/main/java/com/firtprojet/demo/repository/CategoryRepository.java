package com.firtprojet.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.firtprojet.demo.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query(value = "select cat from Category cat where upper(trim(cat.name)) like %?1%")
	List<Category> findCategoryByName(String categoryName);
}
