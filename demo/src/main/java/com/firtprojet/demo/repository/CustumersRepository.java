package com.firtprojet.demo.repository;

import com.firtprojet.demo.entity.Custumers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustumersRepository extends JpaRepository<Custumers,Long> {
}
