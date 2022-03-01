package com.hcl.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcl.entity.Product;

// specify type of Entity and its Id type for ProductRepo its Product and Long 
// For CORS(Cross origin resource sharing) error this is the port Angular is running on
// Origin = the protocol + hostname + port
@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product,Long> {
	
	// query method to match by categroy id and filter by request param value id
	// this is similar to SELECT * FROM product WHERE category_id=?
	Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);

}
