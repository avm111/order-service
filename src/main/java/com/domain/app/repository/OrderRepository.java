package com.domain.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.app.entity.OrderDetail;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetail, String>{

}
