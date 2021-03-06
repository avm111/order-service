package com.domain.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.domain.app.entity.PaymentDetail;

@Repository
public interface PaymentDetailRepository  extends JpaRepository<PaymentDetail, String>{

}
