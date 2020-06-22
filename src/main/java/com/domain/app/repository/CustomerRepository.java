package com.domain.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.app.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

	public Optional<Customer> findByEmailAndPhoneNumber(String email, String phoneNumber);

}
