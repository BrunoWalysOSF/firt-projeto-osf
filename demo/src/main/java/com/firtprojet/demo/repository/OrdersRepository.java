package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    @Query(value = "select * from orders where custumers_id = ?1",nativeQuery = true)
    List<Orders> findOrdersByCustuers(Long custumerId);

}
