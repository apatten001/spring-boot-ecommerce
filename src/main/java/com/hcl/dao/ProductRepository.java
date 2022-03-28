package com.hcl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.hcl.entity.Product;

// specify type of Entity and its Id type for ProductRepo its Product and Long 
// For CORS(Cross origin resource sharing) error this is the port Angular is running on
// Origin = the protocol + hostname + port
@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product,Long> {

}
