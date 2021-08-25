package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.OrderItems;
import com.firtprojet.demo.entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {

}
