package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.Staffs;
import com.firtprojet.demo.entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staffs,Long> {
    @Query(value = "select * from Staffs where store_id = ?1 and id = ?2",nativeQuery = true)
    List<Staffs> findStaffByStore(Long storeId,Long staffId);
}
