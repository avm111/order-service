package com.domain.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.app.entity.PizzaToppingsMaster;

@Repository
public interface PizzaToppingsMasterRepository extends JpaRepository<PizzaToppingsMaster, String> {

}
