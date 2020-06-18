package com.domain.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.app.entity.OrderItemDetail;

@Repository
public interface OrderItemDetailsRepository extends JpaRepository<OrderItemDetail, String>{

}
