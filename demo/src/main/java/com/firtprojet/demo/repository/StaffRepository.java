package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.Staffs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staffs,Long> {
}
