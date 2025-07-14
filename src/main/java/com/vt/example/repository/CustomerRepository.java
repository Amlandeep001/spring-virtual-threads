package com.vt.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vt.example.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
	List<Customer> findByRegion(String region);
}
