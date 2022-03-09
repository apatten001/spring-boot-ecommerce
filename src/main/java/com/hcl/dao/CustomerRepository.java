package com.hcl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
