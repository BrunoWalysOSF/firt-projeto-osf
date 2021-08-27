package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.Product;
import com.firtprojet.demo.entity.Stocks;
import com.firtprojet.demo.entity.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoresRepository extends JpaRepository<Stores,Long> {

}
