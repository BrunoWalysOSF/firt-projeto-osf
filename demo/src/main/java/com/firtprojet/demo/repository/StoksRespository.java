package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.Stocks;
import com.firtprojet.demo.entity.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoksRespository extends JpaRepository<Stocks,Long> {

    @Query(value = "select * from Stocks where stores_id = ?1", nativeQuery = true)
    List<Stocks> findStockByStores(Long storesId);

    @Query(value = "select * from Stocks where product_id = ?1 and quantity >= ?2",nativeQuery = true)
    List<Stocks> findStoreByProductDisponible(Long productId,int quantity);

    @Query(value = "select * from Stocks where product_id = ?1 and stores_id = ?2", nativeQuery = true)
    Stocks findStockByProduct(Long productId,Long storeId);
}
