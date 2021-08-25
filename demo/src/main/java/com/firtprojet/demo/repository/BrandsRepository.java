package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.Brands;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandsRepository extends JpaRepository<Brands,Long> {
}
