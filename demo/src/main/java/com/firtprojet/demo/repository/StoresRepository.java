package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoresRepository extends JpaRepository<Stores,Long> {

}
