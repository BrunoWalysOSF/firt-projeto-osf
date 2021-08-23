package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoksRespository extends JpaRepository<Stocks,Long> {

    @Query(value = "select * from Stocks where stores_id = ?1", nativeQuery = true)
    List<Stocks> findStockByStores(Long storesId);
}
